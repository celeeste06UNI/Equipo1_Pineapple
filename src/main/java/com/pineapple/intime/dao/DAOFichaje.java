package com.pineapple.intime.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.max;
import static com.mongodb.client.model.Filters.in;

public class DAOFichaje {
	private static MongoCollection<Document> dbEstadoFichaje = MongoBroker.get().getCollection("EstadoFichaje");
	private static MongoCollection<Document> dbTest = MongoBroker.get().getCollection("Test");
	private static MongoCollection<Document> dbEmpleado = MongoBroker.get().getCollection("Empleado");

	public static boolean abrirFichaje(String email) {
		Boolean fichado = false;
		Bson fichaje = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		hourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

		Bson filtroEmail = null;
		filtroEmail = eq("email", email);
		String horaInicio = (String) hourFormat.format(new Date());
		String fechaInicio = (String) dateFormat.format(new Date());
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroEmail);
		FindIterable<Document> estadoFichaje = dbEstadoFichaje.find(filtroEmail);
		if (datosPersonales.iterator().hasNext() && estadoFichaje.iterator().hasNext()
				&& estadoFichaje.iterator().next().get("fechaInicio").equals("")) {
			fichaje = combine(set("email", email), set("estado", "abierto"), set("horaInicio", horaInicio),
					set("fechaInicio", fechaInicio), set("horaFin", ""), set("fechaFin", ""), set("tiempo", ""));
			UpdateResult urFichaje = dbEstadoFichaje.updateOne(filtroEmail, fichaje);

			if (urFichaje.wasAcknowledged()) {
				fichado = true;
			}

		} else if (datosPersonales.iterator().hasNext() && !estadoFichaje.iterator().hasNext()) {
			Document fichajeNuevo = new Document();
			fichajeNuevo.put("email", email);
			fichajeNuevo.put("estado", "abierto");
			fichajeNuevo.put("fechaInicio", fechaInicio);
			fichajeNuevo.put("horaInicio", horaInicio);
			fichajeNuevo.put("horaFin", "");
			fichajeNuevo.put("fechaFin", "");
			fichajeNuevo.put("tiempo", "");
			dbEstadoFichaje.insertOne(fichajeNuevo);
			fichado = true;
		}
		return fichado;

	}

	public static boolean cerrarFichaje(String email) throws ParseException {
		Boolean fichado = false;
		Bson fichaje = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		hourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		String horaFin = (String) hourFormat.format(new Date());
		String fechaFin = (String) dateFormat.format(new Date());
		

		Bson filtroEmail = null;
		filtroEmail = eq("email", email);
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroEmail);
		FindIterable<Document> estadoFichaje = dbEstadoFichaje.find(filtroEmail);
		if (datosPersonales.iterator().hasNext() && estadoFichaje.iterator().hasNext()
				&& estadoFichaje.iterator().next().get("horaFin").equals("")
				&& estadoFichaje.iterator().next().get("fechaFin").equals("")) {

			fichaje = combine(set("email", email), set("estado", "cerrado"), set("horaFin", horaFin),
					set("fechaFin", fechaFin));
			UpdateResult urFichaje = dbEstadoFichaje.updateOne(filtroEmail, fichaje);
			if (urFichaje.wasAcknowledged()) {
				fichado = true;
				Document cierreFichaje = estadoFichaje.iterator().next();
				Document fichajeNuevo = new Document();
				fichajeNuevo.put("email", cierreFichaje.get("email"));
				fichajeNuevo.put("fechaInicio", cierreFichaje.get("fechaInicio"));
				fichajeNuevo.put("horaInicio", cierreFichaje.get("horaInicio"));

				fichajeNuevo.put("fechaFin", cierreFichaje.get("fechaFin"));
				fichajeNuevo.put("horaFin", cierreFichaje.get("horaFin"));
				String horaInicio = cierreFichaje.getString("horaInicio");
				//String tiempo = CalculoTiempo(horaInicio, horaFin);
				fichajeNuevo.put("tiempo", cierreFichaje.get(CalculoTiempo(horaInicio, horaFin)));
				dbTest.insertOne(fichajeNuevo);
				Bson fichajeCerrado = null;
				fichajeCerrado = combine(set("email", email), set("estado", "cerrado"), set("fechaInicio", ""),
						set("horaInicio", ""), set("fechaFin", ""), set("horaFin", ""), set("tiempo", ""));
				dbEstadoFichaje.updateOne(filtroEmail, fichajeCerrado);
			}
		} else if (datosPersonales.iterator().hasNext() && !estadoFichaje.iterator().hasNext()) {
			fichado = false;
		}
		return fichado;
	}

	public static Document consultarFichajes(String email, String fecha) {

		Bson filtroFichaje = and(eq("email", email), eq("fechaFin", fecha), eq("fechaInicio", fecha));
		FindIterable<Document> cursor = dbTest.find(filtroFichaje);
		Document resultado = new Document();

		resultado.put("email", email);
		Integer cont = 0;
		for (Document doc : cursor) {
			cont = cont + 1;
			String fichajeInicio = doc.get("fechaInicio") + " " + doc.getString("horaInicio");
			String fichajeFin = doc.get("fechaFin") + " " + doc.getString("horaFin");
			String fichaje = fichajeInicio + " - " + fichajeFin;
			resultado.put(cont.toString(), fichaje);

		}
		return resultado;
	}

	public static Document consultarFichajesEmpleado(String email) {
		Bson filtroEmail = null;
		filtroEmail = in("email", email);
		FindIterable<Document> cursor = dbTest.find(filtroEmail);
		Document resultado = new Document();

		resultado.put("email", email);
		Integer cont = 0;
		for (Document doc : cursor) {
			cont = cont + 1;
			String fichajeInicio = doc.get("fechaInicio") + " " + doc.getString("horaInicio");
			String fichajeFin = doc.get("fechaFin") + " " + doc.getString("horaFin");
			String fichaje = fichajeInicio + " " + fichajeFin;
			resultado.put(cont.toString(), fichaje);

		}
		return resultado;
	}
	
	public String toString() {
		return null;
	}
	
	public static String CalculoTiempo (String apertura, String cierre) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		Date apertura2 = dateFormat.parse(apertura);
		Date cierre2 = dateFormat.parse(cierre);
		
		int tiempo = (int) (((apertura2).getTime() - (cierre2).getTime())/1000);
		
		String tiempo2 = Integer.toString(tiempo);
		
		return tiempo2;
	}

}

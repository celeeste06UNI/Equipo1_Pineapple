package com.pineapple.intime.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import com.pineapple.intime.dominio.EmpleadoHelper;
import com.steadystate.css.parser.ParseException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Filters.lte;

public class DAOFichaje {
	private static MongoCollection<Document> dbEstadoFichaje = MongoBroker.get().getCollection("EstadoFichaje");
	private static MongoCollection<Document> dbFichaje = MongoBroker.get().getCollection("Fichaje");
	private static MongoCollection<Document> dbEmpleado = MongoBroker.get().getCollection("Empleado");

	public static boolean abrirFichaje(String email) {
		Boolean fichado = false;
		Bson fichaje = null;
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		
		//dateFormat.setTimeZone(TimeZone.getTimeZone("Europa/Madrid"));
		//hourFormat.setTimeZone(TimeZone.getTimeZone("Europa/Madrid"));

		Bson filtroEmail = null;
		filtroEmail = or(eq("email", email),eq("dni",email));
		String horaInicio = getCurrentTimeUsingCalendar();
		String fechaInicio = (java.time.LocalDate.now()).toString();
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroEmail);
		FindIterable<Document> estadoFichaje = dbEstadoFichaje.find(filtroEmail);
		if (datosPersonales.iterator().hasNext() && estadoFichaje.iterator().hasNext()
				&& estadoFichaje.iterator().next().get("fechaInicio").equals("")) {
			Document empleado=DAOEmpleado.cargarEmpleado(email);
			
			fichaje = combine(set("email", email),set("dni",empleado.get("dni")), set("estado", "abierto"), set("horaInicio", horaInicio),
					set("fechaInicio", fechaInicio), set("horaFin", ""), set("fechaFin", ""));
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
			dbEstadoFichaje.insertOne(fichajeNuevo);
			fichado = true;
		}
		return fichado;

	}
	public static String getCurrentTimeUsingCalendar() {
		Calendar cal = Calendar.getInstance();
		Date date=cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		String formattedTime=dateFormat.format(date);
		return formattedTime;

	}

	public static boolean cerrarFichaje(String email) throws ParseException, java.text.ParseException {
		Boolean fichado = false;
		Bson fichaje = null;
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		//dateFormat.setTimeZone(TimeZone.getTimeZone("Europa/Madrid"));
		//hourFormat.setTimeZone(TimeZone.getTimeZone("Europa/Madrid"));
		
		String horaFin = getCurrentTimeUsingCalendar();
		String fechaFin = (java.time.LocalDate.now()).toString();

		Bson filtroEmail = null;
		filtroEmail = or(eq("email", email),eq("dni",email));
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
				fichajeNuevo.put("dni",cierreFichaje.get("dni"));
				fichajeNuevo.put("fechaInicio", cierreFichaje.get("fechaInicio"));
				fichajeNuevo.put("horaInicio", cierreFichaje.get("horaInicio"));
				fichajeNuevo.put("fechaFin", cierreFichaje.get("fechaFin"));
				fichajeNuevo.put("horaFin", cierreFichaje.get("horaFin"));
				
				fichajeNuevo.put("tiempo",EmpleadoHelper.CalculoTiempo(cierreFichaje.get("horaFin"), cierreFichaje.get("horaInicio")));
				
				dbFichaje.insertOne(fichajeNuevo);
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


    public static ArrayList<String> consultarFichajes(String email,String fechaInicio,String fechaFin, String tiempo) {
    	Bson filtroFechaInicio=null;

    	Bson filtroFechaFin=null;
    	Bson filtroIncidencia=null;
    	Bson filtroEmail=null;
    	Bson filtroTiempo=null;
    	ArrayList<String> result=new ArrayList<String>();
    	filtroFechaInicio=and(gte("fechaInicio",fechaInicio),gte("horaInicio","00:00:00"));
    	filtroFechaFin=and(lte("fechaFin",fechaFin),lte("horaFin","23:59:59"));
    	filtroTiempo=and(lte("tiempo", tiempo));
    	filtroEmail=or(eq("email",email),eq("dni",email));
    	filtroIncidencia=and(filtroFechaInicio,filtroFechaFin,filtroEmail);
    	FindIterable<Document> fichajes=dbFichaje.find(filtroIncidencia);

    	for (Document doc : fichajes) {
    		String fichaje="";
    		for (String keys : doc.keySet()) {
    			if(!keys.equals("_id")) {
    				fichaje=fichaje+doc.get(""+keys)+" ";
					
				}
    		}
    		result.add((String) fichaje);
    	}
    	return result;
    }

//	public static Document consultarFichajes(String email, String fecha) {
//
//		Bson filtroFichaje = and(eq("email", email), eq("fechaFin", fecha), eq("fechaInicio", fecha));
//		FindIterable<Document> cursor = dbFichaje.find(filtroFichaje);
//		Document resultado = new Document();
//
//		resultado.put("email", email);
//		Integer cont = 0;
//		for (Document doc : cursor) {
//			cont = cont + 1;
//			String fichajeInicio = doc.get("fechaInicio") + " " + doc.getString("horaInicio");
//			String fichajeFin = doc.get("fechaFin") + " " + doc.getString("horaFin");
//			String fichaje = fichajeInicio + " - " + fichajeFin;
//			resultado.put(cont.toString(), fichaje);
//
//		}
//		return resultado;
//	}

	public static Document consultarFichajesEmpleado(String email) {
		Bson filtroEmail = null;
		filtroEmail = or(eq("email", email),eq("dni",email));
		FindIterable<Document> cursor = dbFichaje.find(filtroEmail);
		Document resultado = new Document();

		resultado.put("email", email);
		Integer cont = 0;
		for (Document doc : cursor) {
			cont = cont + 1;
			String fichajeInicio = doc.get("fechaInicio") + " " + doc.getString("horaInicio");
			String fichajeFin = doc.get("fechaFin") + " " + doc.getString("horaFin");
			String tiempo = doc.getString("tiempo");
			String fichaje = fichajeInicio + " - " + fichajeFin + " - " + tiempo;
			resultado.put(cont.toString(), fichaje);

		}
		return resultado;
	}
	
	public String toString() {
		return null;
	}

}
package com.pineapple.intime.dao;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.pineapple.intime.model.Incidencia;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

public class DAOIncidencia {

	private static MongoCollection<Document> dbIncidencia = MongoBroker.get().getCollection("Incidencia");

	public static boolean insert(Document incidencia) {
		boolean insertado = false;
		// if(DAOEmpleado.buscarEmpleado((String) incidencia.get("email"))) {
		dbIncidencia.insertOne(incidencia);
		// insertado=true;
		// }
		return insertado;
	}

	public static ArrayList<Incidencia> consultar(String email, String tipo) {
		ArrayList<Incidencia> i = new ArrayList<Incidencia>();
		Bson filtro = null;
		filtro = and(eq("email", email), eq("tipo", tipo));
		int cont = -1;
		FindIterable<Document> incidencias = dbIncidencia.find(filtro);
		for (Document inc : incidencias) {
			cont++;
			Incidencia incidencia = new Incidencia(inc.getString("email"), inc.getString("estado"),
					inc.getString("asunto"), inc.getString("descripcion"), inc.getString("tipo"),
					inc.getString("fecha"));
			i.add(incidencia);
		}
		return i;
	}

	public static void eliminar(String email, String estado, String asunto, String descripcion, String tipo,
			String fecha) {
		// TODO Auto-generated method stub
		
	}

}

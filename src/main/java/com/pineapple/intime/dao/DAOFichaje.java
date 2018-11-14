package com.pineapple.intime.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
public class DAOFichaje {
	private static MongoCollection<Document> dbFichaje=MongoBroker.get().getCollection("Fichaje");
	private static MongoCollection<Document> dbEmpleado=MongoBroker.get().getCollection("Empleado");
	public static void inicioFichaje(String email) {
		Document fichaje=new Document();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date fecha=new Date();
		Bson filtroEmail=null;
		filtroEmail=eq("email",email);
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroEmail);
    	if(datosPersonales.iterator().hasNext()) {
    		fichaje.put("email", email);
    		fichaje.put("horaInicio", (String)dateFormat.format(fecha));
    		fichaje.put("horaFin", "");
    		dbFichaje.insertOne(fichaje);
    	}
		
	}
	public static void finFichaje() {
		/*
		 * 
		 */
	}

}

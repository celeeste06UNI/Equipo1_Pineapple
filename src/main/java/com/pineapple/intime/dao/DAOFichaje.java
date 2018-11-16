package com.pineapple.intime.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private static MongoCollection<Document> dbEstadoFichaje=MongoBroker.get().getCollection("EstadoFichaje");
	private static MongoCollection<Document> dbFichaje=MongoBroker.get().getCollection("Fichaje");
	private static MongoCollection<Document> dbEmpleado=MongoBroker.get().getCollection("Empleado");
	
	public static boolean abrirFichaje(String email) {
		Boolean fichado=false;
		Bson fichaje=null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date fecha=new Date();
		Bson filtroEmail=null;
		filtroEmail=eq("email",email);
		String horaInicio=(String)dateFormat.format(new Date());
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroEmail);
		FindIterable<Document> estadoFichaje = dbEstadoFichaje.find(filtroEmail);
    	if(datosPersonales.iterator().hasNext() && estadoFichaje.iterator().hasNext() ) {
    		fichaje=combine(set("email",email),set("estado","abierto"),set("horaInicio",horaInicio),set("horaFin",""));
    		UpdateResult urFichaje=dbEstadoFichaje.updateOne(filtroEmail,fichaje);
    		if(urFichaje.wasAcknowledged()) {
    			fichado=true;
    		}
    	}else if(datosPersonales.iterator().hasNext() && !estadoFichaje.iterator().hasNext()){
    		Document fichajeNuevo=new Document();
    		fichajeNuevo.put("email", email);
    		fichajeNuevo.put("estado", "abierto");
    		fichajeNuevo.put("horaInicio", horaInicio);
    		fichajeNuevo.put("horaFin", "");
    		dbEstadoFichaje.insertOne(fichajeNuevo);
    		fichado=true;
    	}
    	return fichado;
		
	}
	public static boolean cerrarFichaje(String email) {
		Boolean fichado=false;
    	Bson fichaje=null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date fecha=new Date();
		Bson filtroEmail=null;
		filtroEmail=eq("email",email);
		String horaFin=(String)dateFormat.format(new Date());
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroEmail);
		FindIterable<Document> estadoFichaje = dbEstadoFichaje.find(filtroEmail);
    	if(datosPersonales.iterator().hasNext() && estadoFichaje.iterator().hasNext() && estadoFichaje.iterator().next().get("horaFin").equals("") ) {
    		
    		fichaje=combine(set("email",email),set("estado","cerrado"),set("horaFin",horaFin));
    		UpdateResult urFichaje=dbEstadoFichaje.updateOne(filtroEmail,fichaje);
    		if(urFichaje.wasAcknowledged()) {
    			fichado=true;
    			Document cierreFichaje=estadoFichaje.iterator().next();
    			Document fichajeNuevo=new Document();
	    		fichajeNuevo.put("email",cierreFichaje.get("email") );
	    		fichajeNuevo.put("horaInicio",cierreFichaje.get("horaInicio") );
	    		fichajeNuevo.put("horaFin",cierreFichaje.get("horaFin") );
	    		dbFichaje.insertOne(fichajeNuevo);
    		}
    	}else if(datosPersonales.iterator().hasNext() && !estadoFichaje.iterator().hasNext()){
    		fichado=false;
    	}
    	return fichado;
	}


}

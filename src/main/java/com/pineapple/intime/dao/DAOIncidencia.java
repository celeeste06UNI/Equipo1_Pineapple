package com.pineapple.intime.dao;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.pineapple.intime.model.Incidencia;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.combine;
public class DAOIncidencia {

	private static MongoCollection<Document> dbIncidencia=MongoBroker.get().getCollection("Incidencia");
	
	public static boolean updateEstado(String email,String estado,String asunto,String descripcion,String tipo,String fecha,String rol) {
		Bson filtro=null;
		Bson update=null;
		filtro=and(or(eq("email",email),eq("dni",email)),eq("fecha",fecha));
	
		update=combine(set("estado",estado));
		
		UpdateResult urIncidencia = dbIncidencia.updateOne(filtro, update);
		if(urIncidencia.wasAcknowledged()) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean updateIncidencia(String email,String estado,String asunto,String descripcion,String tipo,String fecha,String rol) {
		Bson filtro=null;
		Bson update=null;
		filtro=and(or(eq("email",email),eq("dni",email)),eq("fecha",fecha));
		update=combine(set("asunto",asunto),set("descripcion",descripcion),set("tipo",tipo));
		UpdateResult urIncidencia = dbIncidencia.updateOne(filtro, update);
		if(urIncidencia.wasAcknowledged()) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean delete(String email,String estado,String asunto,String descripcion,String tipo,String fecha) {
		Bson filtro=null;
		filtro=and(or(eq("email",email),eq("dni",email)),eq("fecha",fecha));
		DeleteResult urIncidencia = dbIncidencia.deleteOne(filtro);
		if(urIncidencia.wasAcknowledged()) {
			return true;
		}else {
			return false;
		}
	}
	

	public static boolean insert(Document incidencia) {
		boolean insertado = false;
		dbIncidencia.insertOne(incidencia);
		return insertado;
	}


	
	public static ArrayList<Incidencia> consultar(String email,String tipo,String rol){
		ArrayList<Incidencia> result=new ArrayList<Incidencia> ();
		Bson filtro=null;
		filtro=and(or(eq("email",email),eq("dni",email)),eq("tipo",tipo));

		FindIterable<Document> incidencias = dbIncidencia.find(filtro);
		for (Document inc: incidencias) {
			Incidencia incidencia=new Incidencia(inc.getString("email"),inc.getString("estado"),inc.getString("asunto"),inc.getString("descripcion"),
					inc.getString("tipo"),inc.getString("fecha"));
			result.add(incidencia);
		}
		return result;

	}
	public static ArrayList<Incidencia> consultarTodo(String email,String tipo,String rol){
		ArrayList<Incidencia> result=new ArrayList<Incidencia> ();
		Bson filtro=null;
		if(rol.equals("incid")) {
			filtro=eq("tipo",tipo);
		}else {
			filtro=and(or(eq("email",email),eq("dni",email)),eq("tipo",tipo));
		}

		FindIterable<Document> incidencias = dbIncidencia.find(filtro);
		for (Document inc: incidencias) {
			Incidencia incidencia=new Incidencia(inc.getString("email"),inc.getString("estado"),inc.getString("asunto"),inc.getString("descripcion"),
					inc.getString("tipo"),inc.getString("fecha"));
			result.add(incidencia);
		}
		return result;
	}
}

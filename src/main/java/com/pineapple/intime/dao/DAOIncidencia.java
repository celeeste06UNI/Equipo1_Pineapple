package com.pineapple.intime.dao;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.pineapple.intime.model.Incidencia;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.combine;
public class DAOIncidencia {

	private static MongoCollection<Document> dbIncidencia=MongoBroker.get().getCollection("Incidencia");
	
	public static MongoCursor<Document> buscarIncidencias(String email,String tipo){
		MongoCursor<Document> m = null;
		if(DAOEmpleado.buscarEmpleado(email)) {
			Bson filtro=null;
			filtro=and(eq("email",email),eq("tipo",tipo));
			m=dbIncidencia.find(filtro).iterator();
			return m;
		}else {
			return m;
		}
		
	}
	public static boolean update(String email,String estado,String asunto,String descripcion,String tipo,String fecha) {
		Bson filtro=null;
		Bson update=null;
				update=combine(set("email",email),set("estado",estado),set("asunto",asunto),set("descripcion",descripcion),set("tipo",tipo),set("fecha",fecha));
		filtro=and(eq("email",email),eq("fecha",fecha));
		UpdateResult urIncidencia = dbIncidencia.updateOne(filtro, update);
		if(urIncidencia.wasAcknowledged()) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean delete(String email,String estado,String asunto,String descripcion,String tipo,String fecha) {
		Bson filtro=null;
		filtro=and(eq("email",email),eq("fecha",fecha));
		DeleteResult urIncidencia = dbIncidencia.deleteOne(filtro);
		if(urIncidencia.wasAcknowledged()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean insert(Document incidencia) {
		boolean insertado=false;
		//if(DAOEmpleado.buscarEmpleado((String) incidencia.get("email"))) {
			dbIncidencia.insertOne(incidencia);
			//insertado=true;
		//}
		return insertado;
	}
	
	public static ArrayList<Incidencia> consultar(String email,String tipo){
		ArrayList<Incidencia> i=new ArrayList<Incidencia> ();
		Bson filtro=null;
		
		//if(DAOEmpleado.buscarEmpleado(email)) {
			filtro=and(or(eq("email",email),eq("dni",email)),eq("tipo",tipo));
			int cont=-1;
			FindIterable<Document> incidencias = dbIncidencia.find(filtro);
			for (Document inc: incidencias) {
				cont++;
				Incidencia incidencia=new Incidencia(inc.getString("email"),inc.getString("estado"),inc.getString("asunto"),inc.getString("descripcion"),
						inc.getString("tipo"),inc.getString("fecha"));
				
				i.add(incidencia);
			
			}
			
		//}else {
		//	result.put(0, new Document("email","error"));
		//}
		return i;
	}

}

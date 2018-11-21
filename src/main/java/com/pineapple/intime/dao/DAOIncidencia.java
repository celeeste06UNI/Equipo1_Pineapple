package com.pineapple.intime.dao;

import java.util.concurrent.ConcurrentHashMap;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

public class DAOIncidencia {

	private static MongoCollection<Document> dbIncidencia=MongoBroker.get().getCollection("Empleado");
	
	
	
	public static boolean insert(Document incidencia) {
		boolean insertado=false;
		/*if(DAOEmpleado.buscarEmpleado((String) incidencia.get("email"))) {*/
			dbIncidencia.insertOne(incidencia);
			//insertado=true;
		/*}*/
		return insertado;
	}
	
	public static ConcurrentHashMap<Integer,Document> consultar(String email,String tipo){
		ConcurrentHashMap<Integer,Document> result=new ConcurrentHashMap<Integer,Document>();
		Bson filtro=null;
		
		if(DAOEmpleado.buscarEmpleado(email)) {
			filtro=and(eq("email",email),eq("tipo",tipo));
			int cont=-1;
			FindIterable<Document> incidencias = dbIncidencia.find(filtro);
			while(incidencias.iterator().hasNext()) {
				cont++;
				result.put(cont, incidencias.iterator().next());
			}
		}else {
			result.put(0, new Document("email","error"));
		}
		return result;
	}

}

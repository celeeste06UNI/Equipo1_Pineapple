package com.pineapple.intime.dao;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DAOEmpleado {

	public static void insert(Document empleado) {
		MongoCollection<Document> collection= MongoBroker.get().getCollection("Empleado");
		
		collection.insertOne(empleado);
	}
	public static ConcurrentHashMap<String, Document> load() {
		MongoCollection<Document> collection = MongoBroker.get().getCollection("Empleado");
		ConcurrentHashMap<String, Document> result=new ConcurrentHashMap<String, Document>();
		MongoCursor<Document> cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			Document empleadodoc = cursor.next();
			result.put(empleadodoc.getString("email"), empleadodoc);
		}
		return result;
	}
	public static void delete(Document empleado) {
		MongoCollection<Document> collection= MongoBroker.get().getCollection("Empleado");
		collection.deleteOne(empleado);
		
	}
	public static Document autenticar(String email,String password) {
		/*Breve explicación: recoge la colección empleado y crea un iterador
		 * Por cada next() genera un documento empleado 
		 * si coinciden las credenciales lo mete en un nuevo documento
		 * y el rol llama al metodo comprobarRol() que devuelve el String con el
		 * rol asignado a ese email
		 * 
		 */
		
		Boolean autenticado=false;
		Document empleadoAut=new Document();
		MongoCollection<Document> collection= MongoBroker.get().getCollection("Empleado");
		Iterator<Document>empleados=collection.find().iterator();
		while(empleados.hasNext()) {
			Document doc_empleado=empleados.next();
			if(doc_empleado.get("email").equals(email) && doc_empleado.get("password").equals(password)) {
				empleadoAut.append("email", email);
				empleadoAut.append("password", password);
				empleadoAut.append("rol", comprobarRol(email));
			}
		}
		return empleadoAut;
	}
	public static String comprobarRol(String email) {
		String rol="";
		MongoCollection<Document> emprol= MongoBroker.get().getCollection("EmpleadoRol");
		Iterator<Document>roles=emprol.find().iterator();
		while(roles.hasNext()) {
			Document doc_rol=roles.next();
			if(doc_rol.get("email").equals(email)) {
				rol=(String) doc_rol.get("rol");
			}
		}
		return rol;
		
		
	}
	
}
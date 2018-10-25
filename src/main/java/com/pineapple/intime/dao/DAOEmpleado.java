package com.pineapple.intime.dao;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DAOEmpleado {
	/*INSERTAR EMPLEADO*/
	public static void insertEmpleado(Document empleado) {
		MongoCollection<Document> collection= MongoBroker.get().getCollection("Empleado");
		Document empleadoRol=new Document();
		empleadoRol.put("email", empleado.get("email"));
		empleadoRol.put("rol", empleado.get("rol"));
		
		collection.insertOne(empleado);
		insertRolEmpleado(empleadoRol);
	}
	public static void insertRolEmpleado(Document empleadoRol) {
		MongoCollection<Document> collection= MongoBroker.get().getCollection("EmpleadoRol");
		collection.insertOne(empleadoRol);
		
	}
	/*ELIMINAR USUARIO*/
	public static void delete(Document empleado) {
		MongoCollection<Document> collection= MongoBroker.get().getCollection("Empleado");
		Document empleadoRol=new Document();
		empleadoRol.put("email", empleado.get("email"));
		empleadoRol.put("rol", empleado.get("rol"));
		collection.deleteOne(empleado);
		deleteRolEmpleado(empleadoRol);
		
	}
	public static void deleteRolEmpleado(Document empleadoRol) {
		MongoCollection<Document> collection= MongoBroker.get().getCollection("EmpleadoRol");
		collection.insertOne(empleadoRol);
		
	}
	/*MODIFICAR USUARIO*/
//	public static void update(Document empleado) {
//		MongoCollection<Document> collection= MongoBroker.get().getCollection("Empleado");
//		Document empleadoRol=new Document();
//		empleadoRol.put("email", empleado.get("email"));
//		empleadoRol.put("rol", empleado.get("rol"));
//		collection.updateOne("email",empleado.get);
//		updateRolEmpleado(empleadoRol);
//		
//	}
//	public static void updateRolEmpleado(Document empleadoRol) {
//		MongoCollection<Document> collection= MongoBroker.get().getCollection("EmpleadoRol");
//		collection.insertOne(empleadoRol);
//		
//	}
	
	public static ConcurrentHashMap<String, Document> cargarEmpleados() {
		MongoCollection<Document> collection = MongoBroker.get().getCollection("Empleado");
		ConcurrentHashMap<String, Document> result=new ConcurrentHashMap<String, Document>();
		MongoCursor<Document> cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			Document empleadodoc = cursor.next();
			result.put(empleadodoc.getString("email"), empleadodoc);
		}
		return result;
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
				empleadoAut.append("rol", cargarRol(email));
			}
		}
		return empleadoAut;
	}
	public static String cargarRol(String email) {
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
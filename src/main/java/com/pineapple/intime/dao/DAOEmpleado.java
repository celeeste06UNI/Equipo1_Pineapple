package com.pineapple.intime.dao;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DAOEmpleado {
	
	/*INSERTAR EMPLEADO*/
	public static void insert(Document empleado) {
		MongoCollection<Document> dbEmpleado=MongoBroker.get().getCollection("Empleado");
		MongoCollection<Document> dbRol=MongoBroker.get().getCollection("EmpleadoRol");
		insertDatosPersonales(empleado, dbEmpleado);
		insertRol(empleado,dbRol);
	}
	private static void insertDatosPersonales(Document empleado, MongoCollection<Document> dbEmpleado) {
		Document datosPersonales=new Document();
		datosPersonales.put("email", empleado.get("email"));
		datosPersonales.put("password",empleado.get("password"));
		datosPersonales.put("nombre",empleado.get("nombre"));
		datosPersonales.put("apellidos",empleado.get("apellidos"));
		dbEmpleado.insertOne(datosPersonales);
	}
	public static void insertRol(Document empleado, MongoCollection<Document> dbRol) {
		Document rol=new Document();
		rol.put("email", empleado.get("email"));
		rol.put("rol", empleado.get("rol"));
		dbRol.insertOne(rol);
		
	}
	
	/*ELIMINAR USUARIO*/
	/*INSERTAR EMPLEADO*/
	public static void delete(Document empleado) {
		MongoCollection<Document> dbEmpleado=MongoBroker.get().getCollection("Empleado");
		MongoCollection<Document> dbRol=MongoBroker.get().getCollection("EmpleadoRol");
		deleteDatosPersonales(empleado, dbEmpleado);
		deleteRol(empleado,dbRol);
	}
	private static void deleteDatosPersonales(Document empleado, MongoCollection<Document> dbEmpleado) {
		Document datosPersonales=new Document();
		datosPersonales.put("email", empleado.get("email"));
		datosPersonales.put("password",empleado.get("password"));
		datosPersonales.put("nombre",empleado.get("nombre"));
		datosPersonales.put("apellidos",empleado.get("apellidos"));
		dbEmpleado.deleteOne(datosPersonales);
		
	}
	public static void deleteRol(Document empleado, MongoCollection<Document> dbRol) {
		Document rol=new Document();
		rol.put("email", empleado.get("email"));
		rol.put("rol", empleado.get("rol"));
		dbRol.deleteOne(rol);
		
	}
	/*MODIFICAR USUARIO*/
	public static void update(String email,Document empleado) {
		MongoCollection<Document> dbEmpleado= MongoBroker.get().getCollection("Empleado");
		MongoCollection<Document> dbRol= MongoBroker.get().getCollection("Empleado");
		BsonDocument filter=new BsonDocument();
		filter.put("email",new BsonString((String) email));
		updateDatosPersonales(filter,email,empleado,dbEmpleado);
		updateRol(filter,email,empleado,dbRol);
		
	}
	private static void updateDatosPersonales(BsonDocument filtro,String email,Document empleado, MongoCollection<Document> dbEmpleado) {
		Document datosPersonales=new Document();
		datosPersonales.put("email", email);
		datosPersonales.put("password",empleado.get("password"));
		datosPersonales.put("nombre",empleado.get("nombre"));
		datosPersonales.put("apellidos",empleado.get("apellido"));
		dbEmpleado.replaceOne(filtro,datosPersonales);
		
	}
	
	public static void updateRol(BsonDocument filtro,String email,Document empleado,MongoCollection<Document> dbRol) {
		Document rol=new Document();
		rol.put("email", email);
		rol.put("rol", empleado.get("rol"));
		dbRol.replaceOne(filtro,rol);
		
	}
	
	public static ConcurrentHashMap<String, Document> cargarEmpleados() {
		MongoCollection<Document> dbEmpleado = MongoBroker.get().getCollection("Empleado");
		ConcurrentHashMap<String, Document> result=new ConcurrentHashMap<String, Document>();
		MongoCursor<Document> cursor = dbEmpleado.find().iterator();
		while (cursor.hasNext()) {
			Document empleadodoc = cursor.next();
			result.put(empleadodoc.getString("email"), empleadodoc);
		}
		return result;
	}
	public static Document cargarEmpleado(String email) {
		MongoCollection<Document> dbEmpleado = MongoBroker.get().getCollection("Empleado");
		BsonDocument filter=new BsonDocument();
		filter.put("email",new BsonString((String) email));
		MongoCursor<Document> result=dbEmpleado.find(filter).iterator();
		Document empleado=result.next();
		empleado.put("rol", cargarRol(email));
		return empleado;
	}

	public static Document autenticar(String email,String password) {		
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
		MongoCollection<Document> dbRol= MongoBroker.get().getCollection("EmpleadoRol");
		Iterator<Document>roles=dbRol.find().iterator();
		while(roles.hasNext()) {
			Document doc_rol=roles.next();
			if(doc_rol.get("email").equals(email)) {
				rol=(String) doc_rol.get("rol");
			}
		}
		return rol;
		
		
	}
	
}
package com.pineapple.intime.dao;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.pineapple.intime.dominio.EmpleadoHelper;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class DAOEmpleado {

	private static MongoCollection<Document> dbEmpleado=MongoBroker.get().getCollection("Empleado");
	private static MongoCollection<Document> dbRol=MongoBroker.get().getCollection("EmpleadoRol");


	/*INSERTAR EMPLEADO*/
	public static boolean insert(Document empleado) {
		Bson filtroEmail = null;
		filtroEmail = or(eq("email", empleado.get("email")),eq("dni",empleado.get("dni")));
		Document empleadoRol = new Document();
		empleadoRol.append("email", empleado.get("email"));
		empleadoRol.append("dni", empleado.get("dni"));
		empleadoRol.append("rol", empleado.get("rol"));
		empleado.remove("rol");
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroEmail);
		FindIterable<Document> rol = dbRol.find(filtroEmail);
		if (!(datosPersonales.iterator().hasNext() && rol.iterator().hasNext())) {
			dbEmpleado.insertOne(empleado);
			dbRol.insertOne(empleadoRol);
			return true;
		} else {
			return false;
		}
	}

	/* ELIMINAR EMPLEADO */
	public static boolean delete(String empleado) {
		Document bso = new Document();
		bso.append("dni", new BsonString("6"));
		
		dbEmpleado.deleteOne(bso);
		dbRol.deleteOne(bso);
		
		return true;
	}

	/* MODIFICAR USUARIO */
	public static boolean update(String email, Document empleado) {
		Bson filtroEmail = null;
		Bson updateRol = combine(set("email", empleado.get("email")), set("rol", empleado.get("rol")));
		Bson updateDatos = null;
		updateDatos = combine(set("email", empleado.get("email")), set("nombre", empleado.get("nombre")),
				set("apellidos", empleado.get("apellidos")));
		
		filtroEmail = or(eq("email", email),eq("dni",email));
		
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroEmail);
		FindIterable<Document> rol = dbRol.find(filtroEmail);
		boolean actualizado = false;
		if (datosPersonales.iterator().hasNext() && rol.iterator().hasNext()) {
			UpdateResult urDatos = dbEmpleado.updateMany(filtroEmail, updateDatos);
			UpdateResult urRol = dbRol.updateMany(filtroEmail, updateRol);
			if (urDatos.wasAcknowledged() && urRol.wasAcknowledged()) {
				actualizado = true;
			}
		} else {
			actualizado = false;
		}
		return actualizado;
	}

	/* ACTUALIZAR CONTRASENNA */
	public static boolean updatePassword(String email, String contrasennaAntigua, String contrasennaNueva)
			throws Exception {
		Bson filtroEmail = null;
		filtroEmail = and(eq("email", email), eq("contrasenna", contrasennaAntigua));
		Bson updatePassword = null;
		updatePassword = set("contrasenna", contrasennaNueva);
		FindIterable<Document> datosContrasenna = dbEmpleado.find(filtroEmail);
		boolean actualizado = false;
		if (datosContrasenna.iterator().hasNext()) {
			UpdateResult urContrasenna = dbEmpleado.updateMany(filtroEmail, updatePassword);
			if (urContrasenna.wasAcknowledged()) {
				actualizado = true;
			}
		} else {
			actualizado = false;
		}
		return actualizado;

	}

	/* CARGAR TODOS LOS EMPLEADOS */
	public static ConcurrentHashMap<String, Document> cargarEmpleados() {
		MongoCollection<Document> dbEmpleado = MongoBroker.get().getCollection("Empleado");
		ConcurrentHashMap<String, Document> result = new ConcurrentHashMap<String, Document>();
		MongoCursor<Document> cursor = dbEmpleado.find().iterator();
		while (cursor.hasNext()) {
			Document empleadodoc = cursor.next();
			result.put(empleadodoc.getString("email"), empleadodoc);
		}
		return result;
	}

	/* CARGAR DATOS DE UN EMPLEADO */
	public static Document cargarEmpleado(String emp) {
		Bson filtroEmail = null;
		Bson filtroDNI = null;
		Bson filtro = null;
		filtroEmail=eq("email",emp);
		filtroDNI=eq("dni",emp);
		filtro=or(filtroEmail,filtroDNI);
		
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtro);
		FindIterable<Document> rol = dbRol.find(filtro);
		Document empleado=new Document();
		Document doc = new Document();
		if (datosPersonales.iterator().hasNext() && rol.iterator().hasNext()) {
			
			doc = datosPersonales.iterator().next();
			for(String keys: doc.keySet()) {
				empleado.put(keys, doc.get(keys));
			}
			empleado.put("rol", rol.iterator().next().get("rol"));
		} 
		return empleado;
	}

	/* AUTENTICACION */
	public static Document autenticar(String email, String contrasenna) throws Exception {
		String passCifrada = EmpleadoHelper.cifra(contrasenna);
		String passHex = EmpleadoHelper.ConvertirHexadecimal(passCifrada);
		Bson filtroAutenticar = null;
		Bson filtroRol = null;
		filtroAutenticar = and(eq("email", email), eq("contrasenna", passHex));
		filtroRol = eq("email", email);
		FindIterable<Document> datosPersonales = dbEmpleado.find(filtroAutenticar);
		FindIterable<Document> rol = dbRol.find(filtroRol);
		Document result = new Document();
		if (datosPersonales.iterator().hasNext() && rol.iterator().hasNext()) {
			result = datosPersonales.iterator().next();
			result.put("rol", rol.iterator().next().get("rol"));

		} else {
			result.put("email", "error");

		}
		return result;
	}
	
	public String toString() {
		return "";
	}

}
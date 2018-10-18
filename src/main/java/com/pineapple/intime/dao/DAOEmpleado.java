package com.pineapple.intime.dao;

import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DAOEmpleado {

	public static void insert(String idEmpleado, String empleado) {
		MongoCollection<Document> collection= MongoBroker.get().getCollection("Empleado");
		Document doc=new Document("idEmpleado","nombre").append(idEmpleado,empleado);    
		collection.insertOne(doc);
	}
	public static ConcurrentHashMap<Integer, String> load() {
		MongoCollection<Document> collection = MongoBroker.get().getCollection("Empleado");
		ConcurrentHashMap<Integer, String> result=new ConcurrentHashMap<Integer, String>();
		MongoCursor<Document> cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			Document empleadodoc = cursor.next();
			int _id=(int) empleadodoc.getDouble("_id").intValue();
			String empleado=empleadodoc.getString("nombre");
			result.put(_id, empleado);
		}
		return result;
	}
	public static Boolean autenticar(String email,String password) {
		MongoCollection<Document> collection = MongoBroker.get().getCollection("Empleado");
		collection.find("{email:{$exists:true}},{'email':'celeste@mail'");
	}
	
}

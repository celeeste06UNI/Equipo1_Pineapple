package com.pineapple.intime.dao;

import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public class DAOEmpleado {

	public static void insert(String idEmpleado, String empleado) {
		MongoCollection<Document> collection= MongoBroker.get().getCollection("Empleado");
		Document doc=new Document("idEmpleado","nombre").append(idEmpleado,empleado);    
		collection.insertOne(doc);
	}
}

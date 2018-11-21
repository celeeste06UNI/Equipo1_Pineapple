package com.pineapple.intime.dao;
import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
public class MongoBroker {
	private final String databaseName;
	private MongoClientURI uri;
	private MongoClient client;
	private MongoDatabase database;
	
	private MongoBroker() {
		this.databaseName = "mongodb://celeste:database1234@ds233323.mlab.com:33323/db_intime";
		this.uri=new MongoClientURI(databaseName);
		this.client=new MongoClient(uri);
		this.database=this.client.getDatabase("db_intime");
	}
	
	private boolean exists() {
		MongoCursor<String> dbsCursor = client.listDatabaseNames().iterator();
		while(dbsCursor.hasNext()) {
		    if (dbsCursor.next().equals(this.databaseName))
		    	return true;
		}
		return false;
	}
	private static class MongoBrokerHolder {
	 static MongoBroker singleton=new MongoBroker();
	}
	
	public static MongoBroker get() {
		return MongoBrokerHolder.singleton;
	}
	public MongoCollection<Document> getCollection(String name) {
		return this.database.getCollection(name);
	}
}

package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBManager {
    private static final MongoClient mongoClient;
    private static final String URI_CONNECTION = "mongodb://localhost:27017";

    static {
        mongoClient= MongoClients.create(URI_CONNECTION);
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void exit(){
        //close all the connection created by new MongoClient()
        //MongoClient.close(); //TODO FIX IT!!!!!!!!!
    }
}

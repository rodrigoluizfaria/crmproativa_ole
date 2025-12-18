package com.proativaservicos.dao.implemets;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientProvider {

    private static final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

    private MongoClientProvider() {
        // Evita instância externa
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }


}

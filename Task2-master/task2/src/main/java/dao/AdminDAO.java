package dao;

import bean.Admin;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AdminDAO {
    private static final MongoClient mongoClient;
    private static final MongoDatabase database;
    private static final MongoCollection<Document> adminCollection;

    static{
        mongoClient =  MongoDBManager.getMongoClient();
        database =  mongoClient.getDatabase("YOUTUBE_DB");
        adminCollection = database.getCollection("ADMIN_COLLECTION");
    }

    public static boolean addAdmin(Admin admin) {
        Document doc = getAdminDocument(admin);

        adminCollection.insertOne(doc); //TODO check if it can return false
        return true;
    }

    private static Document getAdminDocument(Admin admin) {
        Document doc = new Document("name", admin.getName())
                .append("surname", admin.getSurname())
                .append("username", admin.getUsername())
                .append("password", admin.getPassword())
                .append("birthday", admin.getBirthday())
                .append("phone", admin.getPhone())
                .append("email", admin.getEmail());//TODO check for timestamp
        return doc;
    }
}

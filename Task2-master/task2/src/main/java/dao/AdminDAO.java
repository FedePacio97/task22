package dao;

import bean.Admin;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;

import static com.mongodb.client.model.Filters.eq;

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
        //Check if the username is already used
        if(getAdmin(admin.getUsername()) != null)
            return false;

        Document doc = getAdminDocument(admin);
        adminCollection.insertOne(doc); //TODO check if it can return false
        return true;
    }

    public static Admin getAdmin(String username) {

        MongoCursor<Document> cursor = adminCollection.find(eq("username",username)).iterator();

        Admin adm = null;

        try{
            while(cursor.hasNext()){
                adm = createAdminFromDocument(cursor.next());
            }
        }finally{
            cursor.close();
        }
        return adm;
    }

    public static boolean removeAdmin(Admin adm) {
        //Since the username is unique-> check for the doc that has that username and delete it
        adminCollection.deleteOne(eq("username",adm.getUsername()));
        return true;
    }

    public static boolean editAdmin(Admin preAdm,Admin newAdm) {
        Document newDoc = createAdminDocument(newAdm);

        //If nothing is found-> insert as new so always returns true
        adminCollection.replaceOne(eq("username", preAdm.getUsername()), newDoc);
        return true;
    }

    private static Document createAdminDocument(Admin adm){
        Document doc = new Document("name", adm.getName())
                .append("surname", adm.getSurname())
                .append("username", adm.getUsername())
                .append("password", adm.getPassword())
                .append("birthday", adm.getBirthday())//TODO check for timestamp
                .append("phone", adm.getPhone())
                .append("email", adm.getEmail());
        return doc;
    }

    private static Admin createAdminFromDocument(Document doc) {
        Admin adm = new Admin();
        adm.setName((String) doc.get("name"));
        adm.setSurname((String) doc.get("surname"));
        adm.setUsername((String) doc.get("username"));
        adm.setPassword((String) doc.get("password"));
        adm.setBirthday((String) doc.get("birthday"));
        adm.setPhone((String) doc.get("phone"));
        adm.setEmail((String) doc.get("email"));

        return adm;
    }

    private static Document getAdminDocument(Admin admin) {
        Document doc = new Document("name", admin.getName())
                .append("surname", admin.getSurname())
                .append("username", admin.getUsername())
                .append("password", admin.getPassword())
                .append("birthday", admin.getBirthday())//TODO change date
                .append("phone", admin.getPhone())
                .append("email", admin.getEmail());//TODO check for timestamp
        return doc;
    }
}

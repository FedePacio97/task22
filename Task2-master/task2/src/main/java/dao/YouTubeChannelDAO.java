package dao;

import bean.YouTubeChannel;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;

import static com.mongodb.client.model.Filters.eq;

public class YouTubeChannelDAO {

    private static final MongoClient mongoClient;
    private static final MongoDatabase database;
    private static final MongoCollection<Document> youtubeChannelCollection;

    static{
        mongoClient =  MongoDBManager.getMongoClient();
        database =  mongoClient.getDatabase("YOUTUBE_DB");
        youtubeChannelCollection = database.getCollection("YOUTUBE_CHANNEL_COLLECTION");
    }

    public static boolean addYouTubeChannel(YouTubeChannel ytc) {
        //Check if the username is already used
        if(getYouTubeChannel(ytc.getUsername()) != null)
            return false;

        Document doc = createYouTubeChannelDocument(ytc);
        youtubeChannelCollection.insertOne(doc);
        return true;
    }

    public static boolean removeYouTubeChannel(YouTubeChannel ytc) {
        //Since the username is unique-> check for the doc that has that username and delete it
        youtubeChannelCollection.deleteOne(eq("username",ytc.getUsername()));
        return true;
    }

    public static boolean editYouTubeChannel(YouTubeChannel preYtc,YouTubeChannel newYtc) {
        Document newDoc = createYouTubeChannelDocument(newYtc);

        //If nothing is found-> insert as new so always returns true
        youtubeChannelCollection.replaceOne(eq("username", preYtc.getUsername()), newDoc);
        return true;
    }

    public static YouTubeChannel getYouTubeChannel(String username) {

        MongoCursor<Document> cursor = youtubeChannelCollection.find(eq("username",username)).iterator();

        YouTubeChannel ytc = null;

        try{
            while(cursor.hasNext()){
                ytc = createYouTubeChannelFromDocument(cursor.next());
            }
        }finally{
                cursor.close();
            }
        return ytc;
    }

    private static YouTubeChannel createYouTubeChannelFromDocument(Document doc) {
        YouTubeChannel yt = new YouTubeChannel();
        yt.setName((String) doc.get("name"));
        yt.setSurname((String) doc.get("surname"));
        yt.setUsername((String) doc.get("username"));
        yt.setPassword((String) doc.get("password"));
        yt.setBirthday((LocalDate) doc.get("birthday"));
        yt.setPhone((String) doc.get("phone"));
        yt.setEmail((String) doc.get("email"));

        return yt;
    }


    private static Document createYouTubeChannelDocument(YouTubeChannel ytc){
        Document doc = new Document("name", ytc.getName())
                .append("surname", ytc.getSurname())
                .append("username", ytc.getUsername())
                .append("password", ytc.getPassword())
                .append("birthday", ytc.getBirthday())
                .append("phone", ytc.getPhone())
                .append("email", ytc.getEmail());//TODO check for timestamp
        return doc;
    }
}
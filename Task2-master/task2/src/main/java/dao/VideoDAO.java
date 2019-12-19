package dao;

import bean.Video;

import bean.YouTubeChannel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.print.Doc;
import java.util.HashSet;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static dao.YouTubeChannelDAO.*;


public class VideoDAO {
    private static final MongoClient mongoClient;
    private static final MongoDatabase database;
    private static final MongoCollection<Document> youtubeChannelCollection;

    static{
        mongoClient =  MongoDBManager.getMongoClient();
        database =  mongoClient.getDatabase("YOUTUBE_DB");
        youtubeChannelCollection = database.getCollection("YOUTUBE_CHANNEL_COLLECTION");
    }

    public static boolean addVideo(YouTubeChannel ytc, Video video) {
        //Check if the Title already exists
        if(getVideo(ytc,video.getTitle()) != null)
            return false;

        Document docVideo = createVideoDocument(video);
        youtubeChannelCollection.updateOne(eq("username", ytc.getUsername()), Updates.addToSet("videos", docVideo));
        return true;
    }


    private static Video createVideoFromDocument(Document doc) {
        Video vid = new Video();
        //Here only one video so arrayVideos.size() == 1
        //List<Document> arrayVideos = (List<Document>)doc.get("videos");

        vid.setVideoId((Integer) doc.get("videoId"));
        //vid.setTrendingDate((String) doc.get("trendingDate"));
        vid.setTitle((String) doc.get("title"));
        vid.setChannelTitle((String) doc.get("channelTitle"));
        vid.setCategoryId((Integer) doc.get("categoryId"));
        //vid.setPublishTime((String) doc.get("publishTime"));
        vid.setTags((String) doc.get("tags"));
        vid.setViews((Integer) doc.get("views"));
        vid.setLikes((Integer) doc.get("likes"));
        vid.setDislikes((Integer) doc.get("dislikes"));
        vid.setCommentCount((Integer) doc.get("commentCount"));
        vid.setThumbnailLink((String) doc.get("thumbnailLink"));
        vid.setCommentsDisabled((Boolean) doc.get("commentsDisabled"));
        vid.setRatingsDisabled((Boolean) doc.get("ratingsDisabled"));
        vid.setVideoErrorOrRemoved((Boolean) doc.get("videoErrorOrRemoved"));
        vid.setDescription((String) doc.get("description"));

        return vid;
    }

    public static boolean removeVideo(YouTubeChannel ytc, Video video) {
        //Since the Title is unique-> check for the doc inside the array "Videos" of that YouTubeChannel and delete it
        BasicDBObject query = new BasicDBObject("username", ytc.getUsername());
        BasicDBObject fields = new BasicDBObject("videos",
                new BasicDBObject( "title", video.getTitle()));
        BasicDBObject update = new BasicDBObject("$pull",fields);

        youtubeChannelCollection.updateOne( query, update );
        //Mongo db.coll.update({'username': ytc.getUsername()}, {$pull: {'videos': {'title': video.getTitle()}}})
        return true;
    }

    public static Video getVideo(YouTubeChannel ytc,String title) {

        MongoCursor<Document> cursor =
                youtubeChannelCollection.find(and(eq("username", ytc.getUsername()), eq("videos.title",title)))
                        .projection(new Document("videos.$",1)).iterator();

        Video vid = null;

        try{
            while(cursor.hasNext()){
                Document ytcDoc = cursor.next();
                //Get the first because it's only one video that is retrieved
                Document video = ((List<Document>)ytcDoc.get("videos")).get(0);
                vid = createVideoFromDocument(video);//JSON
                //System.out.println(cursor.next().toJson());
            }
        }finally{
            cursor.close();
        }
        return vid;
    }

    public static HashSet<Video> getVideos(YouTubeChannel ytc) {

        MongoCursor<Document> cursor =
                youtubeChannelCollection.find(eq("username", ytc.getUsername()))
                        .projection(Projections.include("videos")).iterator();

        HashSet<Video> setOfVideos = new HashSet<Video>();
        if(!cursor.hasNext())
            setOfVideos = null;

        try{
            while(cursor.hasNext()){
                Document ytcDoc = cursor.next();
                List<Document> videos = (List<Document>)ytcDoc.get("videos");
                for(Document video : videos){
                    setOfVideos.add(createVideoFromDocument(video));
                }
                //System.out.println(cursor.next().toJson());
            }
        }finally{
            cursor.close();
        }
        return setOfVideos;//vid
    }

    private static Document createVideoDocument(Video vid){
        Document doc = new Document("videoId", vid.getVideoId())
                .append("trendingDate", vid.getTrendingDate())
                .append("title", vid.getTitle())
                .append("channelTitle", vid.getChannelTitle())
                .append("categoryId", vid.getCategoryId())
                .append("publishTime", vid.getPublishTime())
                .append("tags", vid.getTags())
                .append("views", vid.getViews())
                .append("likes", vid.getLikes())
                .append("dislikes", vid.getDislikes())
                .append("commentCount", vid.getCommentCount())
                .append("thumbnailLink", vid.getThumbnailLink())
                .append("commentsDisabled", vid.isCommentsDisabled())
                .append("ratingsDisabled", vid.isRatingsDisabled())
                .append("videoErrorOrRemoved", vid.isVideoErrorOrRemoved())
                .append("description", vid.getDescription());

        return doc;
    }
}

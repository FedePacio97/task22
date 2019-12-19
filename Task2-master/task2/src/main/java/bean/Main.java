package bean;

import bean.YouTubeChannel;
import dao.AdminDAO;
import dao.VideoDAO;
import dao.YouTubeChannelDAO;

import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main (String args[])
    {
        //YouTubeChannel youTubeChannel = new YouTubeChannel();
        //youTubeChannel.setEmail("Graziano.frosini@gmail.com");
        //youTubeChannel.setPassword("littleAngel");
        //youTubeChannel.setUsername("frosini37");

        //if(!YouTubeChannelDAO.addYouTubeChannel(youTubeChannel)){
            //System.out.println("Username already used");
        //}
        //else
            //System.out.println("Utente inserito");

        //YouTubeChannel youTubeChannelExtracted = YouTubeChannelDAO.getYouTubeChannel("frosini37");
        //System.out.println("Password dell'utente trovato -> " + youTubeChannelExtracted.getPassword());

        //System.out.println("The system is going to remove him..");
        //if(YouTubeChannelDAO.removeYouTubeChannel(youTubeChannelExtracted))
            //System.out.println("YouTubeChannel removed");                     WORKS
        //else
            //System.out.println("Problem in removing the YouTubeChannel");

        //YouTubeChannel newYouTubeChannel = new YouTubeChannel();
        //newYouTubeChannel.setUsername("frosini37");
        //newYouTubeChannel.setPassword("immortal");

        //YouTubeChannelDAO.editYouTubeChannel(youTubeChannelExtracted,newYouTubeChannel);      WORKS
        //System.out.println("YoutubeChannel modified");

        //----------------------------------------//
        //Admin
        //Admin admin = new Admin();
        //admin.setUsername("grazianoSuper");
        //admin.setBirthday("1930-01-28");//YYYY-MM-DD

        //if(!AdminDAO.addAdmin(admin))
            //System.out.println("Username already used");
        //else
            //System.out.println("Admin correctly inserted");

        //AdminDAO.removeAdmin(admin); WORKS
        /*Admin newAdmin = new Admin();
        newAdmin.setUsername("grazianoSuper");
        newAdmin.setEmail("frosiniNeverGiveUp@alice.it"); //WORKS

        AdminDAO.editAdmin(admin,newAdmin);
        */
        /*-------------Videos----------*/
        YouTubeChannel youTubeChannelExtracted = YouTubeChannelDAO.getYouTubeChannel("frosini37");

        Video videoToInsert = new Video();
        videoToInsert.setVideoId(5);
        videoToInsert.setTitle("Graziano teaches PIANO");

        if(!VideoDAO.addVideo(youTubeChannelExtracted,videoToInsert))
            System.out.println("Title already present");
        else
            System.out.println("Video successfully inserted");

        //VideoDAO.removeVideo(youTubeChannelExtracted,videoToInsert);

        Set<Video> allVideosOfYouTubeChannel = VideoDAO.getVideos(youTubeChannelExtracted);
        if(allVideosOfYouTubeChannel == null)
            System.out.println("No video in this YouTubeChannel");
        else
            System.out.println("Number of videos -> "+ allVideosOfYouTubeChannel.size());
        for (Video video : allVideosOfYouTubeChannel)
            System.out.println("Title -> "+ video.getTitle());

        //VideoDAO.removeALLVideos();TODO!!!!
    }
}

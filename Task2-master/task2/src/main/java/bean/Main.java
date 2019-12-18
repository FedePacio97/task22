package bean;

import bean.YouTubeChannel;
import dao.YouTubeChannelDAO;

public class Main {
    public static void main (String args[])
    {
        YouTubeChannel youTubeChannel = new YouTubeChannel();
        youTubeChannel.setEmail("Graziano.frosini@gmail.com");
        youTubeChannel.setPassword("littleAngel");
        youTubeChannel.setUsername("frosini37");

        if(!YouTubeChannelDAO.addYouTubeChannel(youTubeChannel)){
            System.out.println("Username already used");
        }
        else
            System.out.println("Utente inserito");

        YouTubeChannel youTubeChannelExtracted = YouTubeChannelDAO.getYouTubeChannel("frosini37");
        System.out.println("Password dell'utente trovato -> " + youTubeChannelExtracted.getPassword());

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
    }
}

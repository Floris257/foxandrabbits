import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import sun.audio.*;
   
// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class Sound {
   
	/**
	 * Plays a sound when there is a certain amount of Rabbits
	 * @throws IOException
	 */
   public static void rabbitSound() throws IOException{	
	   String rabbitFile = "C:/var/www/html/rabbit1.wav";
	   InputStream in = new FileInputStream(rabbitFile);
	   
	   AudioStream audiostream = new AudioStream(in);
	   AudioPlayer.player.start(audiostream);   
   }
}
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import sun.audio.*;
   
// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClipTest extends JFrame {
   
   public static void main(String[] args) throws Exception{
	   Sound sound = new Sound();
	   sound.rabbitSound();
	   
   }
}
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MainMenu {
	
	private static AudioStream as;
	public static void launchMainMenu() throws IOException {
		
		InputStream in = null;
		try {
			in = new FileInputStream("sounds\\upbeat_and_happy.wav");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create an AudioStream object from the input stream.
		try {
			as = new AudioStream(in); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
		
		// Use the static class member "player" from class AudioPlayer to play clip.
		AudioPlayer.player.start(as);
		
		
		new WelcomeScreen();
	}
	
	public static void stopAudioStream() {
		System.out.println("STOP!");
		AudioPlayer.player.stop(as);
	}

}

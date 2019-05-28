import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MainMenu {

	public static void launchMainMenu() throws IOException {
		
		InputStream in = null;
		try {
			in = new FileInputStream("sounds\\sound1.wav");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create an AudioStream object from the input stream.
		AudioStream as = null;
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

}

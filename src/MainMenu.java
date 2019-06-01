import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MainMenu {
	
	private static Clip clip;
	
	public static void launchMainMenu() {
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try {
			clip = (Clip)AudioSystem.getLine(dataInfo); 
		} catch (LineUnavailableException lue) 
			{lue.printStackTrace(); }
		try {
			File soundFile = new File("sounds/upbeat_and_happy.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			clip.open(audioStream);
		} catch (LineUnavailableException lue)
			{lue.printStackTrace();
		} catch (UnsupportedAudioFileException uafe) 
			{uafe.printStackTrace();
		} catch (IOException ioe) 
			{ioe.printStackTrace(); 
		}
		clip.start();
		try {
			new WelcomeScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void stopAudioStream() {
		clip.close();
	}

}

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private static Clip mainClip;
	private static Clip shortClip;
	
	public static void startPlaying(String soundName) {
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try {
			mainClip = (Clip)AudioSystem.getLine(dataInfo); 
		} catch (LineUnavailableException lue) 
			{lue.printStackTrace(); }
		try {
			File soundFile = new File(soundName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			mainClip.open(audioStream);
		} catch (LineUnavailableException lue)
			{lue.printStackTrace();
		} catch (UnsupportedAudioFileException uafe) 
			{uafe.printStackTrace();
		} catch (IOException ioe) 
			{ioe.printStackTrace(); 
		}
		mainClip.start();
		mainClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void stopPlaying() {
		mainClip.close();
	}
	
	public static void playShortSound(String soundName) {
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try {
			shortClip = (Clip)AudioSystem.getLine(dataInfo); 
		} catch (LineUnavailableException lue) 
			{lue.printStackTrace(); }
		try {
			File soundFile = new File(soundName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			shortClip.open(audioStream);
		} catch (LineUnavailableException lue)
			{lue.printStackTrace();
		} catch (UnsupportedAudioFileException uafe) 
			{uafe.printStackTrace();
		} catch (IOException ioe) 
			{ioe.printStackTrace(); 
		}
		shortClip.start();
	}
}

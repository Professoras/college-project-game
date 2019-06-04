import java.io.IOException;

public class MainMenu {

	public static void launchMainMenu() {
		Sound.startPlaying("sounds/Intro.wav");
		try {
			new WelcomeScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

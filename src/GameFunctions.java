import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameFunctions {
	
	private static int newRoundTime;
	private static int gifTime=0;
	private static int wasRight=0;
	private static int wasWrong=0;
	private static int gameOver=0;
	private static int skip=0;
	private static int win=0;
	
	public static void launchTheGame(int charid) {
		Sound.startBackgroundMusic("sounds/Main.wav");
		Player.setPhoto(charid);
		Player.startTheTimer();
		openNewGamePanel();
	}
	
	public static void openNewGamePanel() {
		Story.findQuestion(Player.getCurrentRoom());
		GameFunctions.setNewRoundTimeMark(Player.getRemainingTime());
		new GamePanel();
	}
	
	public static int coinflip() {
		//returns integer in the range: [0,2)
		return (int)(Math.random()*2);
	}
	
	public static void playerWon() {
		Sound.stopBackgroundMusic();
		Sound.playSoundEffect("sounds/Win.wav");
		Player.stopTheTimer();
		
		int totalTime=Player.getTotalTime()-Player.getRemainingTime();
		gifTime=1;
		win=1;
		GameFunctions.showMessage("CONGRATULATIONS!\nYOU WON!\nYour new highscore: "+Player.getScore()+"\nTime: "+totalTime+"s\n"+"Lives left: "+Player.getLives(),5000);
		System.exit(1);
	}
	
	public static void rightAnswer() {
		Player.stopTheTimer();
		Sound.playSoundEffect("sounds/CorrectAnswer.wav");
		Player.updateScore();
		
		if (Player.getCurrentRoom()==Story.getNumberOfLevels())
			playerWon();
		
		if (Player.getLives() < 2)
			Player.addALife();
		
		wasRight=1;
		gifTime=1;
		if (Player.gotBonus())
			showMessage("Fantastic job!\n2x POINTS!\nOn to the next round!\nLives left: "+Player.getLives(),2200);
		else
			showMessage("Good job!\nOn to the next round!\nLives left: "+Player.getLives(),2200);
		
		
		Player.updateCurrentRoom();
		
		if (Player.getCurrentRoom()<Story.getNumberOfLevels() && coinflip()==1) {
			int reducedTime=Enemy.reduceTime();
			Sound.playSoundEffect("sounds/DamageFromEnemy.wav");
			showEnemy(GamePanel.getFrame(),reducedTime);
			Player.reduceTime(reducedTime);	
			
		}
		Player.startTheTimer();
		openNewGamePanel();
	}
	
	public static void wrongAnswer() {
		Player.stopTheTimer();
		Sound.playSoundEffect("sounds/WrongAnswer.wav");
		Player.removeALife();
		gifTime=1;
		if (Player.getLives() == 0) {
			Sound.stopBackgroundMusic();
			Sound.playSoundEffect("sounds/Lose.wav");
			gameOver=1;
			showMessage("GAME OVER!!!\n0 lives left!",3500);
			System.exit(1);
		}
		
		wasWrong=1;
		showMessage("Wrong answer!\nLives left: "+Player.getLives(),2400);
		Player.startTheTimer();
	}
	
	public static void timeIsUp() {
		Sound.stopBackgroundMusic();
		Sound.playSoundEffect("sounds/Lose.wav");
		gifTime=1;
		showMessage("Time is up! You lost!",3000);
		System.exit(1);
	}
	
	public static String TimeConversion(int x) {
		int p1 = x % 60;
        int p2 = x / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        return p3 + ":" + p1;
	}
	
	public static void skipBtn() {
		gifTime=1;
		skip=1;
		if (Player.isSkipAvailable()) {
			if(Player.getLives() > 1 && Player.getCurrentRoom()<3) {
				showMessage("You skipped the question!" + System.lineSeparator() + "The correct answers were:\n" +"1.)"+Story.getFirstRightAnswer()+"\n2.)"+Story.getSecondRightAnswer()+ System.lineSeparator() + "You lost 1 life and 5 seconds!",4000);
				Player.removeALife();
				Player.setSkipNotAvailable();
				if (Player.getCurrentRoom()==Story.getNumberOfLevels())
					playerWon();
				Player.updateCurrentRoom();
				Player.reduceTime(5);
				openNewGamePanel(); 
			}
			else if (Player.getCurrentRoom()==3)
				showMessage("You can't use the skip option in the final level!",2000);
			else
				showMessage("You don't have enough lives to skip the question!",2000);
		}
		else
			showMessage("You already used the skip option once!",2000);
		
		Player.startTheTimer();
	}
	
	public static void showEnemy(JFrame aframe,int reducedTime) {
		String message= "The enemy has cut "+reducedTime+" second(s) off of your time!";
		
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		optionPane.add(new JLabel(new ImageIcon("Images/enemy.jpg"),JLabel.LEADING));
		
		JDialog dialog = new JDialog();
		dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setTitle("DANGER!");
        dialog.setBackground(Color.LIGHT_GRAY);
        dialog.setContentPane(optionPane);
        
        dialog.pack();
        
        dialog.setLocationRelativeTo(aframe);
        
		//create timer to dispose of dialog after 4 seconds
		Timer timer = new Timer(2500, new AbstractAction() {

		    public void actionPerformed(ActionEvent ae) {
		        dialog.dispose();
		    }
		});
		//the timer should only go off once
		timer.setRepeats(false);

		//start timer to close JDialog as dialog modal we must start the timer before its visible
		timer.start();
		dialog.setVisible(true);
	}
	
	public static void setNewRoundTimeMark(int time) {
		newRoundTime=time;
	}
	
	public static int getNewRoundTimeMark() {
		return newRoundTime;
	}
	
	public static void showMessage(String info, int timeinms) {
		JOptionPane optionPane = new JOptionPane(info, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		if (gifTime==1)
			optionPane.add(new JLabel(new ImageIcon(getGIF()),JLabel.LEADING));
		final JDialog dialog = new JDialog();
		dialog.setTitle("Message");
		dialog.setModal(true);

		dialog.setContentPane(optionPane);

		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(GamePanel.getFrame());
		//create timer to dispose of dialog after x seconds
		Timer timer = new Timer(timeinms, new AbstractAction() {

		    public void actionPerformed(ActionEvent ae) {
		        dialog.dispose();
		    }
		});
		//the timer should only go off once
		timer.setRepeats(false);

		//start timer to close JDialog as dialog modal we must start the timer before its visible
		timer.start();
		dialog.setVisible(true);
	}
	
	private static String getGIF() {
		gifTime=0;
		
		if (wasRight==1) {
			wasRight=0;
			if (Player.gotBonus())
				return "Images/double_points.gif";
			else
				return "Images/right.gif";
		}
		
		if (wasWrong==1) {
			wasWrong=0;
			return "Images/wrong.gif";
		}
		
		if (gameOver==1)
			return "Images/game_over.gif";
		
		if (win==1)
			return "Images/win.gif";
		
		if (skip==1) {
			skip=0;
			if (Player.isSkipAvailable())
				if (Player.getLives()>1 && Player.getCurrentRoom()<3)
					return "Images/skip.gif";
				else
					return "Images/skip_2.gif";
			else
				return "Images/skip_3.gif";
		
		}
		
		if (Player.getRemainingTime()==0)
			return "Images/time_is_up.gif";
		
		return "1";
	}
}

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.Timer;

public class Player {
	
	private static String name;
	private static int lives = 3;
	private static int current_room = 1;
	private static int score = 0;
	private static int time=60;
	private static final int totalTime=60;
	private static Timer gameTimer;
	private static boolean skipAvailable = true;
	private static boolean bonus;
	
	
	public static int getScore() {
		return score;
	}
	
	public static void updateScore() {
		if (GameFunctions.getNewRoundTimeMark()-Player.getRemainingTime()<=5) { 
			//Player gets bonus if he or she answers correctly in the first 5 seconds of the new round
			score+=1000;
			bonus=true;
		}
		else
			bonus=false;
		score +=1000;
	}
	
	public static boolean gotBonus() {
		return bonus;
	}
	
	public static int getLives() {
		return lives;
	}
	
	public static void addALife() {
		lives += 1;
	}
	
	public static void removeALife() {
		lives -= 1;
	}
	

	public static void startTheTimer() {
		//System.out.println("Start the timer!");
		gameTimer = new Timer(1000, new AbstractAction() {

			public void actionPerformed(ActionEvent ae) {
		        	if (time<=0) {
		        		gameTimer.stop();
		        		GameFunctions.timeIsUp();
		        		
		        	}
		        	else {
		        		//System.out.println(time);
		        		reduceTime(1);
		        	}
		    	}
			});
		gameTimer.setRepeats(true);

		gameTimer.start();
	}
	
	
	public static int getRemainingTime() {
		return time;
	}
	
	public static void reduceTime(int reduceTimeSeconds) {
		time-=reduceTimeSeconds;
	}
	
	public static void stopTheTimer() {
		gameTimer.stop();
	}
	
	public static int getTotalTime() {
		return totalTime;
	}
	public static int getCurrentRoom() {
		return current_room;
	}
	
	public static void updateCurrentRoom() {
		current_room++;
	}
	
	public static boolean isSkipAvailable() {
		return skipAvailable;
	}
	
	public static void setSkipNotAvailable() {
		skipAvailable = false;
	}
	

}

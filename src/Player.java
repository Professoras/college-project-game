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
	private static int time=61;
	private static Timer gameTimer;
	private static boolean skipAvailable = true;
	
	
	public static int getScore() {
		return score;
	}
	
	public static void updateScore(/*int points*/) {	//Need Implementation!
		int x = 1000;
		
		score += x;
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
		System.out.println("Start the timer!");
		gameTimer = new Timer(1000, new AbstractAction() {

			public void actionPerformed(ActionEvent ae) {
		        	if (time-1==0 || time<=0) {
		        		gameTimer.stop();
		        		GameFunctions.timeIsUp();
		        		
		        	}
		        	else {
		        		//System.out.println(time);
		        		reduceTime(1);
		        	}
		    	}
			});
		gameTimer.setRepeats(true);//the timer should only go off once

		gameTimer.start();
	}
	
	
	public static int getRemainingTime() {
		return time;
	}
	
	public static void reduceTime(int reduceTimeSeconds) {
		time-=reduceTimeSeconds;
	}
	public static String printScore() {
		return "Hey"+name+"\nYour score is"+score;
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
	
//	public static void setTimeCounterStart() {
//		timeCounterStart = time;
//	}
//	
//	public static void updateScore() {
//		timeCounterEnd = time;
//		int bonus = 20 - (timeCounterStart - timeCounterEnd);
//		if (bonus < 0)
//			bonus = 0;
//		//System.out.println(bonus);
//		score = score + 1000 + (bonus * 100);
//	}

}

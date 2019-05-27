import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GameFunctions {
	//Dialog boxes need to be implemented
	
	
	public static void showMessage(String info, JFrame aframe, int timeinms) {
		JOptionPane optionPane = new JOptionPane(info, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		
		final JDialog dialog = new JDialog();
		dialog.setTitle("Message");
		dialog.setModal(true);

		dialog.setContentPane(optionPane);

		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(aframe);
		//create timer to dispose of dialog after x seconds
		Timer timer = new Timer(timeinms, new AbstractAction() {

		    public void actionPerformed(ActionEvent ae) {
		        dialog.dispose();
		    }
		});
		timer.setRepeats(false);//the timer should only go off once

		//start timer to close JDialog as dialog modal we must start the timer before its visible
		timer.start();
		dialog.setVisible(true);
	}
	
	public static int coinflip() {
		return (int)(Math.random()*2); //returns integer in the range: [0,2)
	}
	
	public static void rightAnswer(JFrame aframe) {
		if (Player.getLives() < 5)
			Player.addALife();
		Player.updateCurrentRoom();
		Player.updateScore();
		//Player.setTimeCounterStart();
		showMessage("Good job!\nOn to the next round!\nLives left: "+Player.getLives(),aframe,2200);

		if (coinflip()==1) {
			int reducedTime=Enemy.reduceTime();
			showEnemy(aframe,reducedTime);
			if (Player.getRemainingTime()-reducedTime<=0) {
				showMessage("GAME OVER!!!",aframe,2500);
				System.exit(1);
			}
			else
				Player.reduceTime(reducedTime);
			
		}
		
		aframe.dispose();
		Main.openNewGamePanel();
	}
	
	public static void wrongAnswer(JFrame aframe) {
		Player.removeALife();
		if (Player.getLives() == 0) {
			showMessage("GAME OVER!!!",aframe,2500);
			System.exit(1);
		}
		//Player.updateCurrentRoom();  Should it pass the room with wrong answer?
		showMessage("Wrong answer!\nLives left: "+Player.getLives(),aframe,2200);
		
		if (coinflip()==1) {	//SRS states that Enemy should not KILL the player!
			int reducedTime=Enemy.reduceTime();
			showEnemy(aframe,reducedTime);
			if (Player.getRemainingTime()-reducedTime<=0) {
				showMessage("GAME OVER!!!",aframe,2500);
				System.exit(1);
			}
			else
				Player.reduceTime(reducedTime);
			
		}
		
		aframe.dispose();
		Main.openNewGamePanel();
	}
	
	public static void timeIsUp() {
		showMessage("Time is up! You lost!",GamePanel.getFrame(),3000);
		System.exit(1);
	}
	
	public static String TimeConversion(int x) {
		int p1 = x % 60;
        int p2 = x / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        return p3 + ":" + p1;
	}
	
	public static void skipBtn(JFrame aframe) {
		if (Player.isSkipAvailable()) {
			if(Player.getLives() > 1) {
				showMessage("You skipped the question!" + System.lineSeparator() + "The correct answer was: " + Story.getRightAnswer() + System.lineSeparator() + "You lost 1 life!", aframe,3000);
				Player.removeALife();
				Player.setSkipNotAvailable();
				Player.updateCurrentRoom();
				aframe.dispose();
				Main.openNewGamePanel();
			}
			else
				showMessage("You don't have enough lives to skip the question!", aframe,3000);
		}
		else
			showMessage("You already used the skip option once!", aframe,3000);
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
		Timer timer = new Timer(2700, new AbstractAction() {

		    public void actionPerformed(ActionEvent ae) {
		        dialog.dispose();
		    }
		});
		timer.setRepeats(false);//the timer should only go off once

		//start timer to close JDialog as dialog modal we must start the timer before its visible
		timer.start();
		dialog.setVisible(true);
	}
}

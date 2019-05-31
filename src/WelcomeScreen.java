import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Paths;




public class WelcomeScreen extends JFrame{

	private String instructions;
	private JButton startButton,howToPlayButton,exitButton,backButton,char1,char2;
	private JLabel label;
	private JTextArea text;
	private JRadioButton choice1,choice2;
	private static int howToPlayFrameFlag = 0, locationXOfFrame = 0 , locationYOfFrame = 0, characterSelected = 0;
	private static JFrame welcomeScreenFrame;
	
	public WelcomeScreen() throws IOException {		

		getContentPane().setLayout(null);
		if (welcomeScreenFrame==null) {
			welcomeScreenFrame = new JFrame("Escape Room");
		}
		else {
			characterSelected=0;
			welcomeScreenFrame.getContentPane().removeAll();
			welcomeScreenFrame.repaint();
		}
		startButton = new JButton("");
		startButton.setIcon(new ImageIcon("images\\StartButtonV2.png"));
		startButton.setBackground(new Color(0, 128, 128));
		startButton.setForeground(new Color(0, 128, 128));
		startButton.setBounds(150, 150, 200, 60);
		ButtonListener listener = new ButtonListener();
		startButton.addActionListener(listener);
		welcomeScreenFrame.getContentPane().add(startButton);
		
		howToPlayButton = new JButton("");
		howToPlayButton.setForeground(new Color(0, 128, 128));
		howToPlayButton.setBackground(new Color(0, 128, 128));
		howToPlayButton.setIcon(new ImageIcon("images\\HowToButton.png"));
		howToPlayButton.setBounds(150, 220, 200, 60);		
		ButtonListener2 listener2 = new ButtonListener2();
		howToPlayButton.addActionListener(listener2);	
		welcomeScreenFrame.getContentPane().add(howToPlayButton);
		
		exitButton = new JButton("");
		exitButton.setForeground(new Color(0, 128, 128));
		exitButton.setBackground(new Color(0, 128, 128));
		exitButton.setIcon(new ImageIcon("images\\ExitButton.png"));
		exitButton.setBounds(150, 290, 200, 60);		
		ButtonListener3 listener3 = new ButtonListener3();
		exitButton.addActionListener(listener3);	
		welcomeScreenFrame.getContentPane().add(exitButton);
				
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 484, 482);
		label.setIcon(new ImageIcon("images\\Labyrinth_03.jpg"));
		welcomeScreenFrame.getContentPane().add(label);
		
		//fullscreen
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true);
		welcomeScreenFrame.pack();
		welcomeScreenFrame.setLocationRelativeTo(null);
		welcomeScreenFrame.setVisible(true);
		welcomeScreenFrame.setTitle("                                                       Escape Room 007\r\n");
		welcomeScreenFrame.setSize(490, 510);
		welcomeScreenFrame.setResizable(false);
		welcomeScreenFrame.setForeground(new Color(255, 255, 255));
		welcomeScreenFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\stickman.png"));
		welcomeScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//start button
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			
			welcomeScreenFrame.getContentPane().removeAll();
			welcomeScreenFrame.repaint();
			
			JButton backButton = new JButton("");
			backButton.setForeground(new Color(0, 128, 128));
			backButton.setBackground(new Color(0, 128, 128));
			backButton.setIcon(new ImageIcon("images\\BackButton.png"));
			backButton.setBounds(140, 20, 200, 60);		
			ButtonListener4 listener4 = new ButtonListener4();
			backButton.addActionListener(listener4);	
			
			getContentPane().setLayout(null);
			welcomeScreenFrame.getContentPane().add(backButton);
			
			
			char1 = new JButton("");
			char1.setIcon(new ImageIcon("images\\char1.jpg"));
			char1.setBackground(Color.WHITE);
			char1.setBounds(28, 90, 200, 300);
			ButtonListener5 listener1 = new ButtonListener5();
			char1.addActionListener(listener1);
			
			welcomeScreenFrame.getContentPane().add(char1);	
			
			char2 = new JButton("");
			char2.setIcon(new ImageIcon("images\\char2.jpg"));
			char2.setBackground(Color.WHITE);
			char2.setBounds(259, 90, 200, 300);
			ButtonListener6 listener2 = new ButtonListener6();
			char2.addActionListener(listener2);			
			
			welcomeScreenFrame.getContentPane().add(char2);
					
			JButton nextButton = new JButton("NEXT");
			nextButton.setIcon(new ImageIcon("images\\OkButton.png"));
			nextButton.setForeground(new Color(0, 128, 128));
			nextButton.setBackground(new Color(0, 128, 128));
			nextButton.setBounds(140, 401, 200, 60);
			ButtonListener8 listener3 = new ButtonListener8();
			nextButton.addActionListener(listener3);		
			
			welcomeScreenFrame.getContentPane().add(nextButton);
			
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 484, 482);
			label.setIcon(new ImageIcon("images\\Labyrinth_03.jpg"));
			
			welcomeScreenFrame.getContentPane().add(label);
		}
	}
	
	//how to play button
	class ButtonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent b) {
				
			//x axis location of the frame 
			locationXOfFrame=welcomeScreenFrame.getX();
			//y axis location of the frame 
			locationYOfFrame=welcomeScreenFrame.getY();
			
			if(howToPlayFrameFlag == 1) { 
				return;
            }		
			HowToPlayScreen screen = new HowToPlayScreen();
			howToPlayFrameFlag = 1;
		}
	}
	
	//exit button
	class ButtonListener3 implements ActionListener {
		public void actionPerformed(ActionEvent c) {
			Frame[] frames = Frame.getFrames();
			for (final Window w : frames) {
			    w.dispose();
			}
			System.exit(0);
		}
	}
	
	//back button from character selection screen
	class ButtonListener4 implements ActionListener {
		public void actionPerformed(ActionEvent c) {
			
			char1.setSelected(false);
			char2.setSelected(false);
			try {
				new WelcomeScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//character 1 selected
	class ButtonListener5 implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			characterSelected = 1;
			char1.setIcon(new ImageIcon("images\\char1selected.jpg"));
			char2.setIcon(new ImageIcon("images\\char2.jpg"));
		}
	}
	
	//character 2 selected
	class ButtonListener6 implements ActionListener {
		public void actionPerformed(ActionEvent b) {	
			characterSelected = 2;
			char2.setIcon(new ImageIcon("images\\char2selected.jpg"));
			char1.setIcon(new ImageIcon("images\\char1.jpg"));
		}
	}
	
	//after character selection
	
	public void ShowStory() {	
			
			welcomeScreenFrame.getContentPane().removeAll();
			welcomeScreenFrame.repaint();
			getContentPane().setLayout(null);
			
			//read story file
			String story = "";
			try {
				story = new String(Files.readAllBytes(Paths.get("docs\\story.txt")), "ISO-8859-7");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			text = new JTextArea();
			text.setToolTipText("");
			text.setText(story);
			text.setBackground(Color.DARK_GRAY);
			text.setForeground(Color.WHITE);
			text.setBounds(10, 40, 484, 400);
			text.setAlignmentX(CENTER_ALIGNMENT);
			text.setAlignmentY(CENTER_ALIGNMENT);			
			
			JButton okButton = new JButton("");
			okButton.setIcon(new ImageIcon("images\\OkButton.png"));
			okButton.setForeground(new Color(0, 128, 128));
			okButton.setBackground(new Color(0, 128, 128));
			okButton.setBounds(140, 330, 200, 60);
			ButtonListener9 listener = new ButtonListener9();
			okButton.addActionListener(listener);	
			
			welcomeScreenFrame.getContentPane().setBackground(Color.DARK_GRAY);
			welcomeScreenFrame.getContentPane().add(okButton);
			welcomeScreenFrame.getContentPane().add(text);
		
		}
	
	class ButtonListener9 implements ActionListener {
		public void actionPerformed(ActionEvent b) {	
			welcomeScreenFrame.dispose();
			GameFunctions.launchTheGame(characterSelected);

		}
	}
	//ok button
	class ButtonListener8 implements ActionListener {
		public void actionPerformed(ActionEvent b) {	
			
			if (characterSelected >= 1 ) {
				ShowStory();
			}
			else {
				GameFunctions.showMessage("Select character first!", 1500);;
			}

		}
	}
	
	//number of how to play screens max = 1 
	public static void setHowToPlayFrameFlag(int x){
		howToPlayFrameFlag = x;
    }
	
	
	//get location of jframe
	public static int getWelcomeScreenFrameX(){
		return 	locationXOfFrame;
    }
	
	public static int getWelcomeScreenFrameY(){
		return 	locationYOfFrame;
    }
	
}

	




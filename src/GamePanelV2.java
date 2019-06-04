import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class GamePanel {
	private static JFrame frame;
	private JTextArea timeArea,livesArea;
	private int doorSelected = 0;

	
	public GamePanel() {
		
		
		if (frame==null)
			frame = new JFrame("Escape Room");
		else {
			frame.getContentPane().removeAll();
			frame.repaint();
		}
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\stickman.png"));
		frame.setSize(1200, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		timeArea = new JTextArea();
		timeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		timeArea.setEditable(false);
		timeArea.setBounds(43, 455, 169, 30);
		timeArea.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(timeArea);	
		updateTime();
		
		livesArea = new JTextArea();
		livesArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		livesArea.setText("Lives: " + Player.getLives());
		livesArea.setEditable(false);
		livesArea.setBounds(43, 485, 169, 30);
		livesArea.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(livesArea);
		
		JTextArea scoreArea = new JTextArea();
		scoreArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scoreArea.setText("Score: " + Player.getScore());
		scoreArea.setBounds(43, 515, 169, 30);
		scoreArea.setEditable(false);
		scoreArea.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(scoreArea);
		
		JTextArea roomArea = new JTextArea();
		roomArea.setText("Room " +  Player.getCurrentRoom());
		roomArea.setFont(new Font("Monospaced", Font.PLAIN, 50));
		roomArea.setBounds(608, 20, 184, 75);
		roomArea.setEditable(false);
		roomArea.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(roomArea);
		
		JTextArea questionArea = new JTextArea();
		questionArea.setText(Story.getQuestion());
		questionArea.setBounds(410, 144, 591, 40);
		questionArea.setEditable(false);
		questionArea.setBackground(Color.LIGHT_GRAY);
		questionArea.setHighlighter(null);
		questionArea.setLineWrap(true);
		questionArea.setFont(new Font(null, Font.BOLD, 15));
		frame.getContentPane().add(questionArea);
		
		JLabel playerImage = new JLabel();
		Image playerIcon = Player.getPhoto();
		playerImage.setIcon(new ImageIcon(playerIcon));
		playerImage.setForeground(Color.GRAY);
		playerImage.setBounds(43, 144, 200, 300);
		frame.getContentPane().add(playerImage);
		
		JButton door1 = new JButton("");
		door1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doorSelected=1;
			}
		});
		door1.setBounds(428, 195, 150, 300);
		
		JButton door2 = new JButton("");
		door2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doorSelected=2;
			}
		});
		door2.setBounds(628, 195, 150, 300);
		
		JButton door3 = new JButton("");
		door3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doorSelected=3;
			}
		});
		door3.setBounds(836, 195, 150, 300);

		Image doorIcon = new ImageIcon("Images/LogOut.png").getImage();
		door1.setIcon(new ImageIcon(doorIcon));
		door2.setIcon(new ImageIcon(doorIcon));
		door3.setIcon(new ImageIcon(doorIcon));
		
		frame.getContentPane().add(door1);	
		frame.getContentPane().add(door2);
		frame.getContentPane().add(door3);	

		
		JButton skipBtn = new JButton("Skip question");	//Fill 5th Panel
		skipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					GameFunctions.skipBtn(frame);
			}
		});
		skipBtn.setBounds(1034, 570, 150, 40);
		frame.getContentPane().add(skipBtn);
		
		
		
		JButton restartBtn = new JButton("Restart");
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showOptionDialog(null, "Are you sure you want to Restart?", "Restart", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (response == JOptionPane.YES_NO_OPTION) {
					MainMenu.stopAudioStream();
					frame.dispose();
					Player.reset();
					MainMenu.launchMainMenu();
				}
			}
		});
		restartBtn.setBounds(1059, 519, 125, 40);
		frame.getContentPane().add(restartBtn);
		
		
		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showOptionDialog(null, "Are you sure you want to Exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (response == 0)
					System.exit(0);
			}	
		});
		exitBtn.setBounds(1084, 621, 100, 40);
		frame.getContentPane().add(exitBtn);
		
		ArrayList<String> shuffledAnswers = new ArrayList<>(Story.getAnswers());	//Fill 6th Panel
		Collections.shuffle(shuffledAnswers);	//shuffle the answers
		
		JTextArea answer1Area = new JTextArea(shuffledAnswers.get(0));
		answer1Area.setEditable(false);
		answer1Area.setBackground(Color.LIGHT_GRAY);
		answer1Area.setHighlighter(null);
		answer1Area.setLineWrap(true);
		answer1Area.setFont(new Font(null, Font.BOLD, 13));
		answer1Area.setBounds(428, 506, 150, 40);
		frame.getContentPane().add(answer1Area);

		JTextArea answer2Area = new JTextArea(shuffledAnswers.get(1));
		answer2Area.setEditable(false);
		answer2Area.setBackground(Color.LIGHT_GRAY);
		answer2Area.setHighlighter(null);
		answer2Area.setLineWrap(true);
		answer2Area.setFont(new Font(null, Font.BOLD, 13));
		answer2Area.setBounds(628, 506, 150, 40);
		frame.getContentPane().add(answer2Area);

		JTextArea answer3Area = new JTextArea(shuffledAnswers.get(2));
		answer3Area.setEditable(false);
		answer3Area.setBackground(Color.LIGHT_GRAY);
		answer3Area.setHighlighter(null);
		answer3Area.setLineWrap(true);
		answer3Area.setFont(new Font(null, Font.BOLD, 13));
		answer3Area.setBounds(836, 506, 150, 40);
		frame.getContentPane().add(answer3Area);
		
		
		JButton confirmBtn = new JButton("Confirm");	
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doorSelected == 1) {	//1st Btn selected
					if (answer1Area.getText().equals(Story.getFirstRightAnswer()) || answer1Area.getText().equals(Story.getSecondRightAnswer())) {	//correct answer
						GameFunctions.rightAnswer();
						
					}
					else {	//wrong answer
						GameFunctions.wrongAnswer();		
						livesArea.setText("Lives: " + Player.getLives());
					}
				}
				else if (doorSelected == 2) {	//2nd Btn selected
					if (answer2Area.getText().equals(Story.getFirstRightAnswer()) || answer2Area.getText().equals(Story.getSecondRightAnswer())) {
						GameFunctions.rightAnswer();
					}
					else {
						GameFunctions.wrongAnswer();
						livesArea.setText("Lives: " + Player.getLives());
					}
				}
				else if (doorSelected == 3) {	//3rd Btn selected
					if (answer3Area.getText().equals(Story.getFirstRightAnswer()) || answer3Area.getText().equals(Story.getSecondRightAnswer())) {
						GameFunctions.rightAnswer();
					}
					else {
						GameFunctions.wrongAnswer();
						livesArea.setText("Lives: " + Player.getLives());
					}		
				}
				else {
					JOptionPane.showMessageDialog(frame, "Need to select an answer first!", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		);	
		confirmBtn.setBounds(640, 580, 118, 40);
		frame.getContentPane().add(confirmBtn);



	
	}
	
	public void updateTime() {
		
		Thread clock= new Thread() {
			public void run() {
				while (true){
					timeArea.setText("Time Left: " + GameFunctions.TimeConversion(Player.getRemainingTime()));
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		clock.start();
	}
	
	public static JFrame getFrame() {
		return frame;
	}
}

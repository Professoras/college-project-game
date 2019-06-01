import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
	private JLabel timerLabel;
	private static JFrame frame;
	
	public GamePanel() {
		
		if (frame==null)
			frame = new JFrame("Escape Room");
		else {
			frame.getContentPane().removeAll();
			frame.repaint();
		}
		
		JPanel mainPanel = new JPanel(new GridLayout(3,2));	//mainPanel
		
		JPanel livesTimeScorePanel = new JPanel(new GridLayout(2,1));	//1st Panel
		JPanel roomQuestionPanel = new JPanel(new GridLayout(2,1));	//2nd Panel
		
		JPanel playerPanel = new JPanel();	//3rd Panel
		JPanel doorsImagePanel = new JPanel();	//4th Panel
		
		JPanel restartSkipExitPanel = new JPanel();	//5th Panel
		restartSkipExitPanel.setLayout(new BoxLayout(restartSkipExitPanel, BoxLayout.Y_AXIS));
		JPanel answersDoorsRBtnPanel = new JPanel(new GridLayout(3,3));	//6th Panel
		
		JLabel livesLabel = new JLabel("Lives: " + Player.getLives());	//Fill 1st Panel

		timerLabel = new JLabel("Time Left: " + GameFunctions.TimeConversion(Player.getRemainingTime()));
		updateTime();

		JLabel scoreLabel = new JLabel("Score: " + Player.getScore());
		livesTimeScorePanel.add(livesLabel);
		livesTimeScorePanel.add(timerLabel);
		livesTimeScorePanel.add(scoreLabel);
		
		JLabel roomPanel = new JLabel("Room " +  Player.getCurrentRoom());	//Fill 2nd Panel
		JTextArea questionArea = new JTextArea(Story.getQuestion());
		questionArea.setEditable(false);
		questionArea.setBackground(Color.LIGHT_GRAY);
		questionArea.setHighlighter(null);
		questionArea.setFont(new Font(null, Font.BOLD, 15));
		
		roomQuestionPanel.add(roomPanel);
		roomQuestionPanel.add(questionArea);
		
		JLabel playerImage = new JLabel("");	//Fill 3rd Panel
		Image playerIcon = Player.getPhoto();
		playerImage.setIcon(new ImageIcon(playerIcon));
		playerPanel.add(playerImage);
		
		JLabel door1Image = new JLabel("");	//Fill 4th Panel
		JLabel door2Image = new JLabel("");
		JLabel door3Image = new JLabel("");
		Image doorIcon = new ImageIcon("Images/LogOut.png").getImage();
		door1Image.setIcon(new ImageIcon(doorIcon));
		door2Image.setIcon(new ImageIcon(doorIcon));
		door3Image.setIcon(new ImageIcon(doorIcon));
		doorsImagePanel.add(door1Image);
		doorsImagePanel.add(door2Image);
		doorsImagePanel.add(door3Image);
		
		JButton skipBtn = new JButton("Skip question");	//Fill 5th Panel
		skipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					GameFunctions.skipBtn(frame);
			}
		});
		JButton restartBtn = new JButton("      Restart      ");
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
		JButton exitBtn = new JButton("         Exit         ");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showOptionDialog(null, "Are you sure you want to Exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (response == 0)
					System.exit(0);
			}	
		});
		restartSkipExitPanel.add(Box.createVerticalGlue());
		restartSkipExitPanel.add(skipBtn);
		restartSkipExitPanel.add(Box.createVerticalGlue());
		restartSkipExitPanel.add(restartBtn);
		restartSkipExitPanel.add(Box.createVerticalGlue());
		restartSkipExitPanel.add(exitBtn);
		restartSkipExitPanel.add(Box.createVerticalGlue());
		
		ArrayList<String> shuffledAnswers = new ArrayList<>(Story.getAnswers());	//Fill 6th Panel
		Collections.shuffle(shuffledAnswers);	//shuffle the answers
		
		JTextArea answer1Area = new JTextArea(shuffledAnswers.get(0));
		answer1Area.setEditable(false);
		answer1Area.setBackground(Color.LIGHT_GRAY);
		answer1Area.setHighlighter(null);
		answer1Area.setFont(new Font(null, Font.BOLD, 15));

		JTextArea answer2Area = new JTextArea(shuffledAnswers.get(1));
		answer2Area.setEditable(false);
		answer2Area.setBackground(Color.LIGHT_GRAY);
		answer2Area.setHighlighter(null);
		answer2Area.setFont(new Font(null, Font.BOLD, 15));

		JTextArea answer3Area = new JTextArea(shuffledAnswers.get(2));
		answer3Area.setEditable(false);
		answer3Area.setBackground(Color.LIGHT_GRAY);
		answer3Area.setHighlighter(null);
		answer3Area.setFont(new Font(null, Font.BOLD, 15));
		
		JRadioButton door1RBtn = new JRadioButton("Door 1");
		JRadioButton door2RBtn = new JRadioButton("Door 2");
		JRadioButton door3RBtn = new JRadioButton("Door 3");
		door1RBtn.setBackground(Color.LIGHT_GRAY);
		door2RBtn.setBackground(Color.LIGHT_GRAY);
		door3RBtn.setBackground(Color.LIGHT_GRAY);
		ButtonGroup doorsGroup = new ButtonGroup();
		doorsGroup.add(door1RBtn);
		doorsGroup.add(door2RBtn);
		doorsGroup.add(door3RBtn);
		
		JLabel emptyLabel = new JLabel();
		JButton confirmBtn = new JButton("Confirm");
		
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (door1RBtn.isSelected()) {	//1st Btn selected
					if (answer1Area.getText().equals(Story.getFirstRightAnswer()) || answer1Area.getText().equals(Story.getSecondRightAnswer())) {	//correct answer
						GameFunctions.rightAnswer();
						
					}
					else {	//wrong answer
						GameFunctions.wrongAnswer();
						livesLabel.setText("Lives: " + Player.getLives());
						
					}
				}
				else if (door2RBtn.isSelected()) {	//2nd Btn selected
					if (answer2Area.getText().equals(Story.getFirstRightAnswer()) || answer2Area.getText().equals(Story.getSecondRightAnswer())) {
						GameFunctions.rightAnswer();
					}
					else {
						GameFunctions.wrongAnswer();
						livesLabel.setText("Lives: " + Player.getLives());	
					}
				}
				else if (door3RBtn.isSelected()) {	//3rd Btn selected
					if (answer3Area.getText().equals(Story.getFirstRightAnswer()) || answer3Area.getText().equals(Story.getSecondRightAnswer())) {
						GameFunctions.rightAnswer();
					}
					else {
						GameFunctions.wrongAnswer();
						livesLabel.setText("Lives: " + Player.getLives());
					}		
				}
				else {
					JOptionPane.showMessageDialog(frame, "Need to select an answer first!", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		);
		answersDoorsRBtnPanel.add(answer1Area);
		answersDoorsRBtnPanel.add(answer2Area);
		answersDoorsRBtnPanel.add(answer3Area);
		answersDoorsRBtnPanel.add(door1RBtn);
		answersDoorsRBtnPanel.add(door2RBtn);
		answersDoorsRBtnPanel.add(door3RBtn);
		answersDoorsRBtnPanel.add(emptyLabel);
		answersDoorsRBtnPanel.add(confirmBtn);
		
		mainPanel.add(livesTimeScorePanel);	//Fill mainPanel
		mainPanel.add(roomQuestionPanel);
		mainPanel.add(playerPanel);
		mainPanel.add(doorsImagePanel);
		mainPanel.add(restartSkipExitPanel);
		mainPanel.add(answersDoorsRBtnPanel);
		
		livesTimeScorePanel.setBackground(Color.LIGHT_GRAY);	//BackgroundColors
		roomQuestionPanel.setBackground(Color.LIGHT_GRAY);
		playerPanel.setBackground(Color.LIGHT_GRAY);
		doorsImagePanel.setBackground(Color.LIGHT_GRAY);
		restartSkipExitPanel.setBackground(Color.LIGHT_GRAY);
		answersDoorsRBtnPanel.setBackground(Color.LIGHT_GRAY);
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\stickman.png"));
		frame.setContentPane(mainPanel);	//Frame settings - size settings need to be adjusted!
		frame.setSize(1200, 700);
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void updateTime() {
		
		Thread clock= new Thread() {
			public void run() {
				while (true){
					timerLabel.setText("Time Left: " + GameFunctions.TimeConversion(Player.getRemainingTime()));
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
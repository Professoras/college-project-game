import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WelcomeScreen extends JFrame{


	private JButton playButton,rulesButton,exitButton,char1,char2;
	private JLabel label;
	private JTextArea text;
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
		playButton = new JButton("");
		playButton.setIcon(new ImageIcon("images\\PlayButton.png"));
		playButton.setBackground(new Color(0, 128, 128));
		playButton.setForeground(new Color(0, 128, 128));
		playButton.setBounds(150, 150, 200, 60);
		ButtonListener listener = new ButtonListener();
		playButton.addActionListener(listener);
		welcomeScreenFrame.getContentPane().add(playButton);
		
		rulesButton = new JButton("");
		rulesButton.setForeground(new Color(0, 128, 128));
		rulesButton.setBackground(new Color(0, 128, 128));
		rulesButton.setIcon(new ImageIcon("images\\RulesButton.png"));
		rulesButton.setBounds(150, 220, 200, 60);		
		ButtonListener2 listener2 = new ButtonListener2();
		rulesButton.addActionListener(listener2);	
		welcomeScreenFrame.getContentPane().add(rulesButton);
		
		exitButton = new JButton("");
		exitButton.setForeground(new Color(0, 128, 128));
		exitButton.setBackground(new Color(0, 128, 128));
		exitButton.setIcon(new ImageIcon("images\\ExitButton.png"));
		exitButton.setBounds(150, 290, 200, 60);		
		ButtonListener3 listener3 = new ButtonListener3();
		exitButton.addActionListener(listener3);	
		welcomeScreenFrame.getContentPane().add(exitButton);
				
		label = new JLabel("");
		label.setBounds(0, 0, 484, 482);
		label.setIcon(new ImageIcon("images\\keys.jpg"));
		welcomeScreenFrame.getContentPane().add(label);
		
		//transparency for buttons
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		rulesButton.setOpaque(false);
		rulesButton.setContentAreaFilled(false);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		

		welcomeScreenFrame.pack();
		welcomeScreenFrame.setLocationRelativeTo(null);
		welcomeScreenFrame.setVisible(true);
		welcomeScreenFrame.setTitle("Escape Room 007\r\n");
		welcomeScreenFrame.setSize(490, 510);
		welcomeScreenFrame.setResizable(false);
		welcomeScreenFrame.setForeground(new Color(255, 255, 255));
		welcomeScreenFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\icon.png"));
		welcomeScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//play button
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
			char1.setIcon(new ImageIcon("images\\char1.png"));
			char1.setBackground(Color.WHITE);
			char1.setBounds(28, 90, 200, 300);
			ButtonListener5 listener1 = new ButtonListener5();
			char1.addActionListener(listener1);
			
			welcomeScreenFrame.getContentPane().add(char1);	
			
			char2 = new JButton("");
			char2.setIcon(new ImageIcon("images\\char2.png"));
			char2.setBackground(Color.WHITE);
			char2.setBounds(259, 90, 200, 300);
			ButtonListener6 listener2 = new ButtonListener6();
			char2.addActionListener(listener2);			
			
			welcomeScreenFrame.getContentPane().add(char2);
					
			JButton nextButton = new JButton();
			nextButton.setIcon(new ImageIcon("images\\NextButton.png"));
			nextButton.setForeground(new Color(0, 128, 128));
			nextButton.setBackground(new Color(0, 128, 128));
			nextButton.setBounds(140, 401, 200, 60);
			ButtonListener8 listener3 = new ButtonListener8();
			nextButton.addActionListener(listener3);		
			
			welcomeScreenFrame.getContentPane().add(nextButton);
			
			//button transparency 
			nextButton.setOpaque(false);
			nextButton.setContentAreaFilled(false);
			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 484, 482);
			label.setIcon(new ImageIcon("images\\keys.jpg"));
			
			welcomeScreenFrame.getContentPane().add(label);
		}
	}
	
	//rules button
	class ButtonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent b) {
				
			//x axis location of the frame 
			locationXOfFrame=welcomeScreenFrame.getX();
			//y axis location of the frame 
			locationYOfFrame=welcomeScreenFrame.getY();
			
			if(howToPlayFrameFlag == 1) { 
				return;
            }		
			new HowToPlayScreen();
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
			try {
				new WelcomeScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//character 1 selected
	class ButtonListener5 implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			characterSelected = 1;
			char1.setIcon(new ImageIcon("images\\char1selected.png"));
			char2.setIcon(new ImageIcon("images\\char2.png"));
		}
	}
	
	//character 2 selected
	class ButtonListener6 implements ActionListener {
		public void actionPerformed(ActionEvent b) {	
			characterSelected = 2;
			char2.setIcon(new ImageIcon("images\\char2selected.png"));
			char1.setIcon(new ImageIcon("images\\char1.png"));
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
				e.printStackTrace();
			}
				
			text = new JTextArea();
			text.setToolTipText("");
			text.setText(story);
			text.setBackground(Color.DARK_GRAY);
			text.setForeground(Color.WHITE);
			text.setBounds(10, 10, 484, 400);
			text.setFont(new Font("Courier New", Font.BOLD, 12));
			text.setEditable(false);
			text.setOpaque(false);			
			
			JButton okButton = new JButton("");
			okButton.setIcon(new ImageIcon("images\\OkButton.png"));
			okButton.setForeground(new Color(0, 128, 128));
			okButton.setBackground(new Color(0, 128, 128));
			okButton.setBounds(140, 390, 200, 60);
			okButton.setOpaque(false);
			okButton.setContentAreaFilled(false);
			ButtonListener9 listener = new ButtonListener9();
			okButton.addActionListener(listener);	
					
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 484, 482);
			label.setIcon(new ImageIcon("images\\lake.jpg"));
		
			welcomeScreenFrame.getContentPane().add(okButton);
			welcomeScreenFrame.getContentPane().add(text);
			welcomeScreenFrame.getContentPane().add(label);
		
		}
	
	//game START
	class ButtonListener9 implements ActionListener {
		public void actionPerformed(ActionEvent b) {
			Frame[] frames = Frame.getFrames();
			for (final Window w : frames) {
			    w.dispose();
			}
			Sound.stopBackgroundMusic();
			GameFunctions.launchTheGame(characterSelected);
		}
	}
	
	//ok button
	class ButtonListener8 implements ActionListener {
		public void actionPerformed(ActionEvent b) {	
			if (characterSelected >= 1 )
				ShowStory();
			else
				GameFunctions.showMessage("Select character first!", 1500);;
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

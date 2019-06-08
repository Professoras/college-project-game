import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;


public class HowToPlayScreen extends JFrame {

	private JTextArea text;

	public HowToPlayScreen() {

		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);
		this.setTitle("How to play : ");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\Question Mark.png"));
		this.setBounds(100, 100, 530, 330);
		this.getContentPane().setLayout(null);

		//read how to play file
		String howToPlayText = "";
		try {
			howToPlayText = new String(Files.readAllBytes(Paths.get("Docs\\howtoplay.txt")), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		text = new JTextArea();
		text.setEditable(false);
		text.setToolTipText("");
		text.setAlignmentX(CENTER_ALIGNMENT);
		text.setAlignmentY(CENTER_ALIGNMENT);
		text.setText(howToPlayText);
		text.setBackground(Color.DARK_GRAY);
		text.setForeground(Color.WHITE);
		text.setBounds(5, 10, 520, 300);
		this.getContentPane().add(text);
		text.setColumns(10);

		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setAlwaysOnTop (true);
		this.setLocation(WelcomeScreen.getWelcomeScreenFrameX() - 420, WelcomeScreen.getWelcomeScreenFrameY());
		
		//to avoid multiple HowToPlayScreens
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (true){
		        	WelcomeScreen.setHowToPlayFrameFlag(0);
		        }
		    }
		});
	}	
}


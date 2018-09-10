package game1;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainFrame extends JFrame{
	public static void main(String[] args) {
		new MainFrame();
	}
	
	public MainFrame() {
		super("game1");
		MyPanel myPanel=new MyPanel();
		this.add(myPanel);
		new Timer(1,myPanel).start();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
}

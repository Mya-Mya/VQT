package game1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements ActionListener {
	public static final int W=1000;
	public static final int H=700;
	
	GameActivity game=null;
	public MyPanel() {
		this.setPreferredSize(new Dimension(W,H));
		game=new GameActivity();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.run(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}
}

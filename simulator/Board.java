import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class Board extends JPanel implements Runnable{
	
	private Thread animator;
	private EHBO ehbo;
	
	public Board()
	{
		initSimulator();
		setDoubleBuffered(true);
	}
	
	public void initSimulator()
	{
		ehbo = new EHBO(100, 100);
		animator = new Thread();
		animator.start();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(ehbo.getImageIcon().getImage(), 100, 100, this);
		
		g2.dispose();
	}
	
	public void run() {		
		repaint();	
	}

}

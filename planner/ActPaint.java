import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;


public class ActPaint extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TimePanel t;
	public ActPaint(TimePanel t)
	{
		this.t = t;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform tr = new AffineTransform();
		tr.scale(1, -1);
		tr.translate(0, getHeight());
		Shape s = new Rectangle2D.Double(t.getHeight()/4, 10 , 10, t.getHeight()/10);
		g2.draw(s);
		g2.fill(s);
	}
}

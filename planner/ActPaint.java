import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.GregorianCalendar;

import javax.swing.JPanel;


public class ActPaint
{
	private static final long serialVersionUID = 1L;
	private int act;
	private int stage;
	private Interface iface;
	private TimePanel t;
	private Shape s;
	public ActPaint(int act, Interface iface, TimePanel t)
	{
		this.t = t;
		this.iface = iface;
		this.stage = t.getID();
		this.act = act;
	}
	
	public void paintComponent(Graphics2D g2)
	{
		System.out.println((t.getWidth()/7.9)/iface.getDuration(stage, act)+ "duration");
		System.out.println(100-((t.getWidth()/7.9)/((iface.getStartTime(stage, act).getMaximum(GregorianCalendar.HOUR_OF_DAY)*60)+(iface.getStartTime(stage, act).getMaximum(GregorianCalendar.MINUTE)))*100)+ " startTime");
	s = new RoundRectangle2D.Double((t.getWidth()+650)-((iface.getStartTime(stage, act).getMaximum(GregorianCalendar.HOUR_OF_DAY)*60)+(iface.getStartTime(stage, act).getMaximum(GregorianCalendar.MINUTE))), t.getHeight()/20*1.5,100-((t.getWidth()/+650)-iface.getDuration(stage, act)), t.getHeight()/20 * 19 , 10 ,10);
	g2.setColor(iface.getColor(stage, act));
	g2.draw(s);
	g2.fill(s);
	}
}

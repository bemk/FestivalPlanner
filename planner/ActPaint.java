import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;
import java.util.*;

public class ActPaint implements TimeLinePanel, Serializable
{
	private static final long serialVersionUID = 1L;
	private int act;
	private int stage;
	private Interface iface;
	private TimePanel t;
	private Shape s;
	
	private double height;
	private double width;
	private double startX;
	private double topY;
	public ActPaint(TimePanel t)
	{
		this.t = t;
	}
	
	public TimePanel getTimePanel()
	{
		return t;
	}
	
	public double getStartX()
	{
		return startX;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getTopY()
	{
		return topY;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public int getActID()
	{
		return act;
	}
	
	public ActPaint(int act, Interface iface, TimePanel t)
	{

		this.t = t;
		this.iface = iface;
		this.stage = t.getID();
		this.act = act;
		
		this.height = t.height()/4;
		this.topY = -this.height/2;
		Calendar tmpStart = iface.getStartTime(stage, act);
		int tmpHr = tmpStart.get(Calendar.HOUR_OF_DAY);
		int tmpMin = tmpStart.get(Calendar.MINUTE);
		if (Interface.dbg)
		{
			System.out.printf("Time\t%d:%d\n\n", tmpHr, tmpMin);
		}
		tmpMin += (tmpHr*60);
		startX = (t.width()/1440)*tmpMin;
		width = t.width()/1400*iface.getDuration(stage, act);
	}
	
	public double height()
	{
		return this.height;
	}
	public double width()
	{
		return t.width();
	}
	
	public void paintComponent(Graphics2D g2)
	{
		if (Interface.dbg)
		{
//			System.out.println((t.width()/7.9)/iface.getDuration(stage, act)*100+ " duration");
//			System.out.println(100-((t.width()/7.9)/((iface.getStartTime(stage, act).getMaximum(GregorianCalendar.HOUR_OF_DAY)*60)+(iface.getStartTime(stage, act).getMaximum(GregorianCalendar.MINUTE)))*100)+ " startTime");
			System.out.printf("%f\t%f\n", startX, topY);
			System.out.printf("%f\t%f\n", width, height);
		}
//		s = new RoundRectangle2D.Double((t.width()+650)/((iface.getStartTime(stage, act).getMaximum(GregorianCalendar.HOUR_OF_DAY)*60)+(iface.getStartTime(stage, act).getMaximum(GregorianCalendar.MINUTE))), t.height()/20*1.5,100-((t.width()/+650)/iface.getDuration(stage, act)), t.height()/20 * 19 , 10 ,10);
		s = new RoundRectangle2D.Double(startX, topY, width, height, 10, 10);
		t.shapes.add(s);
		t.actPaints.add(this);
		g2.setColor(iface.getColor(stage, act));
		g2.draw(s);
		g2.fill(s);
	}
	
	public void paintComponent(Shape s, Graphics2D g2)
	{
		g2.setColor(Color.BLACK);
		t.shapes.add(s);
		g2.draw(s);
		g2.fill(s);
	}


}

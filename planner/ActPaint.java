import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.JPanel;


public class ActPaint implements TimeLinePanel
{
	private static final long serialVersionUID = 1L;
	private int act;
	private int stage;
	private Interface iface;
	private TimeLinePanel t;
	private Shape s;
	
	private double height;
	private double width;
	private double startX;
	public ActPaint(int act, Interface iface, TimePanel t)
	{
		this.t = t;
		this.iface = iface;
		this.stage = t.getID();
		this.act = act;
		
		this.height = t.getHeight()/10*9;
		Calendar tmpStart = iface.getStartTime(stage, act);
		int tmpHr = tmpStart.get(Calendar.HOUR);
		int tmpMin = tmpStart.get(Calendar.MINUTE);
		tmpMin += (tmpHr*60);
		startX = t.getWidth()/1440*tmpMin;
		width = t.getWidth()/1400*iface.getDuration(stage, act);
		
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
			System.out.println((t.width()/7.9)/iface.getDuration(stage, act)*100+ "duration");
			System.out.println(100-((t.width()/7.9)/((iface.getStartTime(stage, act).getMaximum(GregorianCalendar.HOUR_OF_DAY)*60)+(iface.getStartTime(stage, act).getMaximum(GregorianCalendar.MINUTE)))*100)+ " startTime");
		}
		s = new RoundRectangle2D.Double((t.width()+650)/((iface.getStartTime(stage, act).getMaximum(GregorianCalendar.HOUR_OF_DAY)*60)+(iface.getStartTime(stage, act).getMaximum(GregorianCalendar.MINUTE))), t.height()/20*1.5,100-((t.width()/+650)/iface.getDuration(stage, act)), t.height()/20 * 19 , 10 ,10);
		g2.setColor(iface.getColor(stage, act));
		g2.draw(s);
		g2.fill(s);
	}
}

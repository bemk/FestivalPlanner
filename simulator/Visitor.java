import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Visitor extends Person {

	private int timesTried;
	
	public Visitor(int x, int y)
	{
		super.coordinates.setLocation(x, y);
	}
	
	public void increaseTimesTried()
	{
		timesTried++;
	}
	
	public void resetTimesTried()
	{
		timesTried = 0;
	}
	
	public void setTimesTried(int time)
	{
		this.timesTried = time;
	}
	
	public int getTimesTried()
	{
		return this.timesTried;
	}
	
}

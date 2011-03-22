import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class Visitor extends Person {

	public Visitor(int x, int y)
	{
		super.coordinates.setLocation(x, y);
		setStatus(0);
	}
	
}

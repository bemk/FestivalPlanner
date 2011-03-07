
public class River extends Obstacle {

private String url = "FP/.png";
	
	public River(int x, int y)
	{
		super.coordinates.setLocation(x, y);
		setImageIcon(url);
		setBorder();
	}
}


public class Forest extends Obstacle{

private String url = "FP/boom.png";
	
	public Forest(int x, int y)
	{
		super.coordinates.setLocation(x, y);
		setImageIcon(url);
		setBorder();
		
	}
}

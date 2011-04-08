
public class StagePicture extends Building{
	
private String url = "FP/podium1.png";
	
	public StagePicture(int x, int y)
	{
		super.coordinates.setLocation(x, y);
		setImageIcon(url);
	}

}

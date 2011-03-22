
public class StagePicture extends Building{
	
private String url = "FP/podium.png";
	
	public StagePicture(int x, int y)
	{
		super.coordinates.setLocation(x, y);
		setImageIcon(url);
	}

}

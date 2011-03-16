
public class Visitor extends Person {

private String url = "FP/female.png";
private String url2 = "FP/male.png";
private String url3 = "FP/busyFemale.png";
private String url4 = "FP/busyMale.png";
	
	public Visitor(int x, int y)
	{
		super.coordinates.setLocation(x, y);
		setImageIcon(url);
		addImageIcon(getImageIcon());
		setImageIcon(url2);
		addImageIcon(getImageIcon());
		setImageIcon(url3);
		addImageIcon(getImageIcon());
		setImageIcon(url4);
		addImageIcon(getImageIcon());
		setStatus(0);
	}
	
}

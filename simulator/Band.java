
public class Band extends Person{
	
	private String url = "FP/.png";
	private String url2 = "FP/.png";
		
		public Band(int x, int y, String url, String url2)
		{
			super.coordinates.setLocation(x, y);
			setImageIcon(url);
			addImageIcon(getImageIcon());
			setImageIcon(url2);
			addImageIcon(getImageIcon());
		}
}

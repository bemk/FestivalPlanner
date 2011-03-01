import javax.swing.ImageIcon;


public abstract class Person extends Sprite {
	
	protected ImageIcon imageIcon;
	
	public void setImageIcon(String url)
	{
		imageIcon = new ImageIcon(this.getClass().getResource(url));
	}

	public ImageIcon getImageIcon()
	{
		return this.imageIcon;
	}
	
	public void act(String direction, int distance) 
	{
		if(direction == "LEFT")
		{
			super.coordinates.x -= distance;
		}
		if(direction == "RIGHT")
		{
			super.coordinates.x += distance;
		}
		if(direction == "UP")
		{
			super.coordinates.y -= distance;
		}
		if(direction == "DOWN")
		{
			super.coordinates.y += distance;
		}
	}	

}

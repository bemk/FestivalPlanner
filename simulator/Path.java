import javax.swing.ImageIcon;


public abstract class Path extends Sprite {
	
	protected ImageIcon imageIcon;
	
	public void setImageIcon(String url)
	{
		imageIcon = new ImageIcon(this.getClass().getResource(url));
	}

	public ImageIcon getImageIcon()
	{
		return this.imageIcon;
	}

}
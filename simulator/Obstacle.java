import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;


public abstract class Obstacle extends Sprite{

protected ImageIcon imageIcon;
protected Rectangle2D border;
protected int x;
protected int y;
	
	public void setImageIcon(String url)
	{
		imageIcon = new ImageIcon(this.getClass().getResource(url));
		super.setWidth(imageIcon.getIconWidth());
		super.setHeight(imageIcon.getIconHeight());
	}

	public ImageIcon getImageIcon()
	{
		return this.imageIcon;
	}
	
	public void setBorder()
	{
		border = new Rectangle2D.Double(imageIcon.getIconWidth(), imageIcon.getIconWidth(), x, y);
	}

}

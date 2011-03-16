import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public abstract class Sprite {

protected Point coordinates = new Point(0,0);
protected int height;
protected int width;
protected ArrayList<ImageIcon> imageIcons = new ArrayList<ImageIcon>();

public void setCoordinates(int x, int y)
{
	this.coordinates.x = x;
	this.coordinates.y = y;
}

public Point getCoordinates()
{
	return this.coordinates;
}	

public void setX(int x)
{
	this.coordinates.x = x;
}

public int getX()
{
	return this.coordinates.x;
}

public void setY(int y)
{
	this.coordinates.y = y;
}

public int getY()
{
	return this.coordinates.y;
}

public void addImageIcon(ImageIcon i)
{
	imageIcons.add(i);
}

public ArrayList<ImageIcon> getImageIcons()
{
	return this.imageIcons;
}

public void setHeight(int height)
{
	this.height = height;
}

public int getHeight()
{
	return this.height;
}

public void setWidth(int width)
{
	this.width = width;
}

public int getWidth()
{
	return this.width;
}
}



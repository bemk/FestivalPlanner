//package fp;

import java.util.*;
import java.awt.Color;

public class Act extends TimeLine
{
	private Calendar startTime;
	private int endTime;
	private ArrayList<Artist> artists;
	private String description;
	private int duration;
	private Color color;
	private TimeLine supremeTimeLine;
	private String genre;

	public void setStartTime(Calendar c)
	{
		startTime = c;
	}
	public Calendar getStartTime()
	{
		return startTime;
	}

	public void setDuration(int d)
	{
		duraton = d;
	}
	public int getDuration()
	{
		return duration;
	}

	public void addArtist(Artist a)
	{
		artists.add(a);
	}
	public void removeArtist(int a)
	{
		artists.remove(a);
	}
	public void removeArtist(Artist a)
	{
		this.removeArtist(indexOf(a));
	}

	public void setDescription(String d)
	{
		description = d;
	}
	public String getDescription()
	{
		return d;
	}
}
 

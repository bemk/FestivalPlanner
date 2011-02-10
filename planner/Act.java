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
		duration = d;
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
		this.removeArtist(artists.indexOf(a));
	}

	public void setDescription(String d)
	{
		description = d;
	}
	public String getDescription()
	{
		return description;
	}

	public void setGenre (String g)
	{
		genre = g;
	}
	public String getGenre()
	{
		return genre;
	}
	public Stage findStage()
	{
		if (!(supremeTimeLine instanceof Stage))
		{
			if (!(supremeTimeLine instanceof Act))
			{
				Act a = (Act)supremeTimeLine;
				return a.findStage();
			}
			else
			{
				return null;
			}
		}
		else
		{
			return (Stage)(supremeTimeLine);
		}
	}

	public void setGenre (String g)
	{
		genre = g;
	}
	public String getGenre()
	{
		return genre;
	}
	public Stage findStage()
	{
		if (supremeTimeLine != instanceof(Stage))
		{
			if (supremeTimeLine!= instanceof(Act))
			{
				return supremeTimeLine.findStage();
			}
			else
			{
				return null;
			}
		}
		else
		{
			return supremeTimeLine;
		}
	}
}
 

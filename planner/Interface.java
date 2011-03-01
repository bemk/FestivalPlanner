import java.awt.Color;
import java.util.*;

public class Interface
{
	protected Planning planning = new Planning();
	protected AddressBook addressBook = new AddressBook();
	protected Artist tmpArtist;
	protected Act tmpAct;
	private ArrayList<TimeLine> timelines = new ArrayList<TimeLine>();

	public ArrayList<Integer> getAllActs()
	{
		ArrayList<Integer> retval = new ArrayList<Integer>();
		for (TimeLine i : timelines)
		{
			if (i instanceof Act)
			{
				retval.add(i.ID());
			}
		}
		return retval;
	}
	
	//Planning
	public int newStage(String name)
	{
		Stage tmpStage = new Stage(name);
		planning.addStage(tmpStage);
		timelines.add(tmpStage);
		return tmpStage.ID();
	}
    public void removeStage(String s)
    {
    	planning.removeStage(s);
    }

    public void removeStage(int s)
    {
    	for (Stage i : planning.getAllStages())
    	{
    		if (i.ID() == s)
    		{
    			planning.removeStage(i);
    			break;
    		}
    	}
    	for (TimeLine i : timelines)
    	{
    		if (i.ID() == s)
    		{
    			timelines.remove(i);
    			break;
    		}
    	}
    }
    public Stage getStage(int s)
    {
    	return planning.getStage(s);
    }

//    public ArrayList<Stage> getAllStages()
//    {
//    	return planning.getAllStages();
//    }
    //Stage
    public void setNaam(int stage, String name)
    {
    	Stage tmp = findStage(stage);
    	tmp.setName(name);

    }
    public int getID(int stage)
    {
    	 return planning.getStage(stage).ID();
    }

    public Act getAct(int stage , int ListNo)
    {
    	Stage tmp = findStage(stage);
    	 return tmp.getAct(ListNo);
    }
     
    public ArrayList<Integer> getAllActs(int stage)
    {
    	return findStage(stage).getAllActs();
    }
    
    public void removeAct(int stage, int id)
    {
    	planning.getStage(stage).removeAct(id);
    	for (TimeLine i : timelines)
    	{
    		if (i.ID() == id && i instanceof Act)
    		{
    			timelines.remove(i);
    		}
    	}
    }
    
    //Act
    public int newAct(GregorianCalendar startTime, int duration, ArrayList<Artist> artist, String description, String genre, Color c, int stage)
    {
    	 Act tmpAct = new Act(startTime,duration,artist,description,genre,c);
    	 Stage tmpStage = findStage(stage);
    	 tmpStage.addAct(tmpAct);
    	 return tmpAct.ID();
    }
    public void setStartTime(int stage, int act, GregorianCalendar c)
    {
    	 planning.getStage(stage).getAct(act).setStartTime(c);
    }
     
    public Calendar getStartTime(int stage, int act)
    {
    	 return planning.getStage(stage).getAct(act).getStartTime();
    }
     
    public void setDuration(int stage, int act, int d)
    {
    	 planning.getStage(stage).getAct(act).setDuration(d);
    }
     
    public int getDuration(int stage, int act)
    {
    	 return planning.getStage(stage).getAct(act).getDuration();
    }
    
    public void addArtist(int stage, int act, int Artist)
    {
    	planning.getStage(stage).getAct(act).addArtist(findArtist(Artist));
    }
     
    public void removeArtist(int stage, int act, int a)
    {
    	 planning.getStage(stage).getAct(act).removeArtist(findArtist(a));
    }
    
    public void setDescription(int stage, int act, String s)
    {
    	planning.getStage(stage).getAct(act).setDescription(s);
    }
     
    public String getDescription(int stage, int act)
    {
    	 return planning.getStage(stage).getAct(act).getDescription();
    }
     
    public void setGenre(int stage, int act, String s)
    {
    	 planning.getStage(stage).getAct(act).setGenre(s);
    }
     
    public String getGenre(int stage, int act)
    {
    	 return planning.getStage(stage).getAct(act).getGenre();
    }
     
    public void setColor(int stage, int act, Color c)
    {
    	 planning.getStage(stage).getAct(act).setColor(c);
    }
    public Color getColor(int stage, int act)
    {
         return planning.getStage(stage).getAct(act).getColor();
    }
    
    public void findStage(int stage, int act)
    {
    	 planning.getStage(stage).getAct(act).findStage();
    }

     
    //AddressBook
    public int newArtist(String name, int rating, String comments)
    {
    	Artist tmpArtist = new Artist(name, comments, rating);
    	addressBook.addArtist(tmpArtist);
    	return tmpArtist.ID();
    }

    public void removeArtist(int n)
    {
    	Artist tmp = findArtist(n);
    	if (tmp!= null)
    	{
    		addressBook.removeArtist(tmp);
    	}
    }

    public ArrayList<Integer> getAllArtists()
    {
    	ArrayList<Integer> artists = new ArrayList<Integer>();
    	for (Artist i : addressBook.getAllArtists())
    	{
    		artists.add(i.ID());
    	}
    	return artists;
    }
    	 
     
    //Artist
 
    public void setName(int i, String n)
    {
    	 findArtist(i).setName(n);
    }
	
    public String getName(int i)
    {
    	 return findArtist(i).getName();
    }
     
    public void setPreferences(int i, String p)
    {
    	findArtist(i).setPreferences(p);
    }
     
    public String getPreferences(int i)
    {
    	 return findArtist(i).getPreferences();
    }
     
    public void setComments(int i, String c)
    {
    	findArtist(i).setComments(c);
    }
     
    public String getComments(int i)
    {
    	 return findArtist(i).getComments();
    }
     
    public void setRating(int i, int r)
    {
    	 findArtist(i).setRating(r);
    }
     
    public int getRating(int i)
    {
    	return findArtist(i).getRating();
    }
    
    private Artist findArtist(int id)
    {
    	for (Artist i : addressBook.getAllArtists())
    	{
    		if (i.ID() == id)
    		{
    			return i;
    		}
    	}
    	return null;
    }
    
    private Stage findStage (int id)
    {
    	Stage tmp = null;
    	for (Iterator<Stage> i = planning.getAllStages().iterator(); i.hasNext();tmp = i.next())
    	{
    		if (tmp.ID()==id)
    		{
    			return tmp;
    		}
    	}
    	return null;
    }
}
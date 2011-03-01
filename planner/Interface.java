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
    
    public void removeAct(int stage, int ListNo)
    {
    	planning.getStage(stage).removeAct(ListNo);
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
     
    public void addArtist(int stage, int act, Artist a)
    {
    	 planning.getStage(stage).getAct(act).addArtist(a);
    }
     
    public void removeArtist(int stage, int act, Artist a)
    {
    	 planning.getStage(stage).getAct(act).removeArtist(a);
    }
     
    public void removeArtist(int stage, int act, int i)
    {
    	 planning.getStage(stage).getAct(act).removeArtist(i);
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
    public Artist getArtist(int id)
    {
        return addressBook.getArtist(id);
    }

    public void addArtist(Artist a)
    {
    	 addressBook.addArtist(a);
    }

    public void removeArtist(Artist a)
    {
    	 addressBook.removeArtist(a);
    }

    public void removeArtist(int n)
    {
    	 addressBook.removeArtist(n);
    }

    public ArrayList<Artist> getAllArtists()
    {
    	 return addressBook.getAllArtists();
    }
    	 
     
    //Artist
     
    public Artist newArtist(String name, String preferences, int rating)
    {
    	 return new Artist(name,preferences,rating);
    	 
    	 
    }
    public void setName(int i, String n)
    {
    	 addressBook.getArtist(i).setName(n);
    }
	
    public String getName(int i)
    {
    	 return addressBook.getArtist(i).getName();
    }
     
    public void setPreferences(int i, String p)
    {
    	 addressBook.getArtist(i).setPreferences(p);
    }
     
    public String getPreferences(int i)
    {
    	 return addressBook.getArtist(i).getPreferences();
    }
     
    public void setComments(int i, String c)
    {
    	 addressBook.getArtist(i).setComments(c);
    }
     
    public String getComments(int i)
    {
    	 return addressBook.getArtist(i).getComments();
    }
     
    public void setRating(int i, int r)
    {
    	 addressBook.getArtist(i).setRating(r);
    }
     
    public int getRating(int i)
    {
    	return addressBook.getArtist(i).getRating();
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
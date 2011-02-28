import java.awt.Color;
import java.util.*;

public class Interface
{
	protected Planning planning = new Planning();
	protected AddressBook addressBook = new AddressBook();
	protected Artist tmpArtist;
	protected Act tmpAct;

	
	//Planning
	public int newStage(String name)
	{
		Stage tmpStage = new Stage(name);
		planning.addStage(tmpStage);
		return tmpStage.ID();
	}

    public void removeStage(Stage s)
    {
    	planning.removeStage(s);
    }
    public void removeStage(String s)
    {
    	planning.removeStage(s);
    }

    public void removeStage(int s)
    {
    	Stage tmp = null;
    	for (Iterator<Stage> p = planning.getAllStages().iterator(); p.hasNext(); tmp = p.next())
    	{
    		if (tmp.ID() == s)
    		{
    			planning.removeStage(tmp);
    			return;
    		}
    	}
    }
    public Stage getStage(String s)
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
    public int getID(String stage)
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
    
    public void removeAct(String stage, int ListNo)
    {
    	 planning.getStage(stage).removeAct(ListNo);
    }
     
    public void addAct(String stage, Act a)
    {
    	 planning.getStage(stage).addAct(a);
    }
    
    //Act
    public Act nieuwAct(GregorianCalendar startTime, int duration, ArrayList<Artist> artist, String description, String genre, Color c)
    {
    	 return new Act(startTime,duration,artist,description,genre,c);
    }
    public void setStartTime(String stage, int act, GregorianCalendar c)
    {
    	 planning.getStage(stage).getAct(act).setStartTime(c);
    }
     
    public Calendar getStartTime(String stage, int act)
    {
    	 return planning.getStage(stage).getAct(act).getStartTime();
    }
     
    public void setDuration(String stage, int act, int d)
    {
    	 planning.getStage(stage).getAct(act).setDuration(d);
    }
     
    public int getDuration(String stage, int act)
    {
    	 return planning.getStage(stage).getAct(act).getDuration();
    }
     
    public void addArtist(String stage, int act, Artist a)
    {
    	 planning.getStage(stage).getAct(act).addArtist(a);
    }
     
    public void removeArtist(String stage, int act, Artist a)
    {
    	 planning.getStage(stage).getAct(act).removeArtist(a);
    }
     
    public void removeArtist(String stage, int act, int i)
    {
    	 planning.getStage(stage).getAct(act).removeArtist(i);
    }
     
    public void setDescription(String stage, int act, String s)
    {
    	planning.getStage(stage).getAct(act).setDescription(s);
    }
     
    public String getDescription(String stage, int act)
    {
    	 return planning.getStage(stage).getAct(act).getDescription();
    }
     
    public void setGenre(String stage, int act, String s)
    {
    	 planning.getStage(stage).getAct(act).setGenre(s);
    }
     
    public String getGenre(String stage, int act)
    {
    	 return planning.getStage(stage).getAct(act).getGenre();
    }
     
    public void setColor(String stage, int act, Color c)
    {
    	 planning.getStage(stage).getAct(act).setColor(c);
    }
    public Color getColor(String stage, int act)
    {
         return planning.getStage(stage).getAct(act).getColor();
    }
    
    public void findStage(String stage, int act)
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
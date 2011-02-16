//package fp;

import java.awt.Color;
import java.util.*;

public class Interface
{
	private Planning planning;
	private AddressBook addressBook;
	public Interface()
	{
		planning = new Planning();
		addressBook = new AddressBook();
	}

	
	//Planning
     public void addStage(Stage s)
     {
    	 planning.addStage(s);
     }

     public void removeStage(Stage s)
     {
    	 planning.removeStage(s);
     }

     public void removeStage(int s)
     {
    	 planning.removeStage(s);
     }

     public Stage getStage(int s)
     {
    	 return planning.getStage(s);
     }

     public ArrayList<Stage> getAllStages()
     {
    	return planning.getAllStages();
     }

     
     //Stage
     public void setID(int stage, int id)
     {
    	 planning.getStage(stage).setId(id);
     }
     
     public void getID(int stage, int id)
     {
    	 planning.getStage(stage).getId();
     }

     public Act getAct(int stage, int ListNo)
     {
    	 return planning.getStage(stage).getAct(ListNo);
     }
     
     public void removeAct(int stage, int ListNo)
     {
    	 planning.getStage(stage).removeAct(ListNo);
     }
     
     public void addAct(int stage, Act a)
     {
    	 planning.getStage(stage).addAct(a);
     }
     
     
     //Act
     public void setStartTime(int stage, int act, Calendar c)
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
     
//     public void findStage(int stage, int act)
//     {
//    	 planning.getStage(stage).getAct(act).findStage();
//     }

     
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
}



//package fp;

import java.io.Serializable;
import java.util.*;

public class AddressBook implements Serializable
{
		private static final long serialVersionUID = 7311894096888783864L;
		private ArrayList<Artist> artists;
        private Artist tmp;
        public AddressBook()
        {
        	 this.artists = new ArrayList<Artist>();
        }
        public Artist getArtist(int id)
        {
        	try{
            tmp = artists.get(id);    
        	
        	}
        	catch(IndexOutOfBoundsException e)
        	{
        	e.printStackTrace();
        	}
        	return tmp;
        }
        public void editArtist(int index, Artist tmp)
        {
        	this.artists.set(index, tmp);
        }
        public void addArtist(Artist a)
        {
                this.artists.add(a);
        }
        
        public Artist searchArtist(String n)
        {
        	Artist tmp = null;
        	 
        	for (int i = 0; i < artists.size(); i++)
        	{
        		if(artists.get(i).getName().equals(n))
        		{
        			tmp = artists.get(i);
        		}
        		
        		
        	}
        	return tmp;
        }
        public void removeArtist(Artist a)
        {
                this.removeArtist(artists.indexOf(a));
        }
        public void removeArtist(int a)
        {
                artists.remove(a);
        }
        public ArrayList<Artist> getAllArtists()
        {
                return artists;
        }
}
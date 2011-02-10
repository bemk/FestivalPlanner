//package fp;

import java.util.*;

public class AddressBook
{
	private ArrayList<Artist> artists = new ArrayList<Artist>();

	public Artist getArtist(int id)
	{
		return artists.get(id);
	}
	public void addArtist(Artist a)
	{
		artists.add(a);
	}
	public void removeAtrist(Artist a)
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

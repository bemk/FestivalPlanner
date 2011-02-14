//package fp;

public class Artist {
	private String name;
	private String preferences;
	private String comments;
	private int rating;

	public void setName(String n)
	{
		name = n;
	}
	public String getName()
	{
		return name;
	}

	public void setPreferences(String p)
	{
		preferences = p;
	}
	public String getPreferences()
	{
		return preferences;
	}

	public void setComments(String c)
	{
		comments = c;
	}
	public String getComments()
	{
		return comments;
	}

	public void setRating (int r)
	{
		rating = r;
	}
	public int getRating()
	{
		return rating;
	}
}

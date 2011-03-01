import java.io.Serializable;

public class Artist implements Serializable{
		private static final long serialVersionUID = 3196373188236259064L;
		private String name;
        private String preferences;
        private String comments;
        private int rating;
        private final int id = serial;
        private static int serial = 0;
        
        public Artist(String name, String preferences, int rating)
        {
        	serial++;
        	this.name = name;
        	this.preferences = preferences;
        	this.rating = rating;
        }
        
        public int ID()
        {
        	return id;
        }

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

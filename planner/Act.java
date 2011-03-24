import java.util.*;
import java.awt.Color;
import java.io.Serializable;

@SuppressWarnings("unchecked")
public class Act extends TimeLine implements Serializable, Comparator
{
		private static final long serialVersionUID = -4987434497827905839L;
		private GregorianCalendar startTime;
        private int endTime;
        private ArrayList<Artist> artists;
        private String description;
        private int duration;
        private Color color;
        private TimeLine supremeTimeLine;
        private String genre;
        
        public Act()
        {
        	this (new GregorianCalendar(), 0, new ArrayList<Artist>(), new String(), new String(), new Color(1f, 1f, 1f));
        }
        
        public Act(GregorianCalendar starttijd, int duratie, ArrayList<Artist> artists, String description, String genre, Color c)
        {
        	this.startTime = starttijd;
        	this.duration = duratie;
        	this.artists = artists;
        	this.description = description;
        	this.genre = genre;
        	this.color = c;
        }

        public void setStartTime(GregorianCalendar c)
        {
                this.startTime = c;
        }
        public Calendar getStartTime()
        {
                return startTime;
        }

        public void setEndTime(int endTime) {
			this.endTime = endTime;
		}
		public int getEndTime() {
			return endTime;
		}
		
		public void setDuration(int d)
        {
                this.duration = d;
        }
        public int getDuration()
        {
                return duration;
        }

        public void addArtist(Artist a)
        {
                this.artists.add(a);
        }
        public void removeArtist(int a)
        {
                this.artists.remove(a);
        }
        public void removeArtist(Artist a)
        {
                this.removeArtist(artists.indexOf(a));
        }

        public void setDescription(String d)
        {
                this.description = d;
        }
        public String getDescription()
        {
                return description;
        }

        public void setGenre (String g)
        {
                this.genre = g;
        }
        public String getGenre()
        {
                return genre;
        }
        
        public void setColor (Color c)
        {
                this.color = c;
        }
        public Color getColor()
        {
                return color;
        }
        
        public Stage findStage()
        {
                if (!(supremeTimeLine instanceof Stage))
                {
                        if (!(supremeTimeLine instanceof Act))
                        {
                        		Act tmp = (Act)supremeTimeLine;
                                return tmp.findStage();
                        }
                        else
                        {
                                return null;
                        }
                }
                else
                {
                        return (Stage)supremeTimeLine;
                }
        }
        
		public int compare(Object o1, Object o2) {
			if (o1 instanceof Act && o2 instanceof Act)
			{
				Act a1 = (Act)o1;
				Act a2 = (Act)o2;
				Calendar first = a1.getStartTime();
				Calendar second = a2.getStartTime();
				if (first.getTimeInMillis() < second.getTimeInMillis())
					return -1;
				else if (first.getTimeInMillis() == second.getTimeInMillis())
					return 0;
				else
					return 1;
			}
			else
			{
				return 0;
			}
		}
		
		public ArrayList<String> getArtistNames()
		{
			ArrayList<String> tmpStringArrayList = new ArrayList<String>();
			for(Artist a : artists)
			{
				tmpStringArrayList.add(a.getName());
			}
			return tmpStringArrayList;
		}

		@Override
		public String getName() {
			return null;
		}
}

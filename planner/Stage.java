import java.io.Serializable;
import java.util.*;

//package fp;

public class Stage extends TimeLine implements Serializable
{
		private static final long serialVersionUID = -3885032387792550015L;
		private String name;
		private final int ID = serial;
		private static int serial = 0;
        public Stage(String name)
        {
        	this.name = name;
        	serial ++;
        }
        public int ID()
        {
        	return this.ID;
        }
        public void setName(String name)
        {
                this.name = name;
        }
        public String getName()
        {
                return name;
        }
}


import java.io.Serializable;
import java.util.*;

//package fp;

public class Stage extends TimeLine implements Serializable
{
		private static final long serialVersionUID = -3885032387792550015L;
		private String name;
        public Stage(String name)
        {
        	this.name = name;
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


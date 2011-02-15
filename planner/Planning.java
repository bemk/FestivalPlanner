//package fp;
import java.util.*;
public class Planning 
{
	private ArrayList<Stage> stages;
	
	public Planning()
	{
		
	}
	 
	public void addStage(Stage s)
	{
		stages.add(s);
	}
	
	public void removeStage(Stage s)
	{
		stages.remove(s);
	}
	
	public void removeStage(int s)
	{
		stages.remove(s);
	}
	
	public Stage getStage(int s)
	{
		return stages.get(s);
	}
	
	public ArrayList<Stage> getAllStages()
    {
   	 return stages;
    }
}

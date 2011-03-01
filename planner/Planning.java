import java.io.Serializable;
import java.util.*;
public class Planning implements Serializable
{
	private static final long serialVersionUID = 3573024112012715071L;
	private ArrayList<Stage> stages;
	
	public Planning()
	{
		stages = new ArrayList<Stage>();
	}
	 
	public void addStage(Stage s)
	{
		stages.add(s);
	}
	
	public void removeStage(Stage s)
	{
		stages.remove(s);
	}
	
	public void removeStage(String s)
	{
		for(Stage i : stages)
		{
			if(i.getName().equals(s))
			{
				stages.remove(i);
			}
		}
	}
	
	public void removeStage(int s)
	{
		for(int i = 0; i<stages.size(); i++)
		{
			if(stages.get(i).ID() == s)
			{
				stages.remove(i);
			}
		}
	}
	
	public Stage getStage(int s)
	{
		Stage tmpStage = null;
		for(int i = 0; i<stages.size(); i++)
		{
			if(stages.get(i).ID() == s)
			{
				tmpStage = stages.get(i);
			}
		}
		return tmpStage;
	}
	
	public ArrayList<Stage> getAllStages()
    {
   	 return stages;
    }
}

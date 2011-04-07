//package fp;
import java.io.Serializable;
import java.util.*;

abstract class TimeLine implements Serializable 
{
	private ArrayList<Act> acts = new ArrayList<Act>();
    private final int ID = serial;
    private static int serial = 0;
    public TimeLine()
    {
    	serial++;
    }
    
    public abstract String getName();

	public Act getAct(int a)
	{
//		return acts.get(a);
		for (Act i : acts)
		{
			if (i.ID() == a)
			{
				return i;
			}
		}
		return null;
	}
	public ArrayList <Integer> getAllActs()
	{
		ArrayList<Integer> iActs = new ArrayList<Integer>();
		for (Act a : acts)
		{
			iActs.add(a.ID());
		}
		return iActs;
	}
	public void removeAct(int a)
	{
		Act j = getAct(a);
		if(j != null)
		acts.remove(j);
	}
	public void addAct(Act a)
	{
		acts.add(a);
	}
	public int ID()
    {
    	return this.ID;
    }
	public boolean actBusy(Calendar startTime, int endTime)
	{
		boolean b = false;
		for(Act a : acts)
		{
			if(a.getStartTime().equals(startTime)||a.getEndTime()< endTime)
			{
				b = true;
			}
		}
		return b;
	}
}

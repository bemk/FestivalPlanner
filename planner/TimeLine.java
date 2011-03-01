//package fp;
import java.util.*;

abstract class TimeLine {
	private ArrayList<Act> acts = new ArrayList<Act>();
    private final int ID = serial;
    private static int serial = 0;
    public TimeLine()
    {
    	serial++;
    }

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
		for (Act i : acts)
		{
			if (i.ID() == a)
			{
				acts.remove(i);
			}
		}
	}
	public void addAct(Act a)
	{
		acts.add(a);
	}
	public int ID()
    {
    	return this.ID;
    }
}

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
		return acts.get(a);
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
		acts.remove(a);
	}
	public void addAct(Act a)
	{
		acts.add(a);
	}
	public void sort()
	{
		Collections.sort(acts, new Act());
		System.out.println("THE SORTING FUNCTION HASN'T BEEN TESTED!!!");
	}
	public int ID()
    {
    	return this.ID;
    }
}

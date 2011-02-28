//package fp;
import java.util.*;

abstract class TimeLine {
	private ArrayList<Act> acts;

	public Act getAct(int a)
	{
		return acts.get(a);
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
	abstract int ID();
}

//package fp;
import java.util.ArrayList;

//Demonstation puposes
public class TimeLine {
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
		//Sort the list of acts.
		System.out.println("THE SORTING FUNCTION NEEDS TO BE IMPLEMENTED!!!");
	}
}

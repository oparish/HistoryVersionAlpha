package data;
import java.util.ArrayList;
import java.util.List;


public class Journey 
{
	private int number;
	private int a;
	private int b;
	private int distance;
	private List<Step> steps;
	
	public Journey()
	{
		a = -1;
		b = -1;
		steps = new ArrayList<Step>();
	}
	
	public void addStep(Step newStep)
	{
		steps.add(newStep);
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public int getA()
	{
		return a;
	}
	
	public int getB()
	{
		return b;
	}

	public int getDistance()
	{
		return distance;
	}
	
	public List<Step> getSteps()
	{
		return steps;
	}
	
	public void setNumber(int value)
	{
		number = value;
	}
	
	public void setA(int value)
	{
		a = value;
	}
	
	public void setB(int value)
	{
		b = value;
	}

	public void setDistance(int value)
	{
		distance = value;
	}
	
	public void setStep(int position, Step newStep)
	{
		steps.set(position, newStep);
	}
	
	public void setSteps(List<Step> newSteps)
	{
		steps = newSteps;
	}
}

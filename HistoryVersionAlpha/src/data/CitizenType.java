package data;

public class CitizenType
{
	private double percent;
	private int number;
	
	public double getPercent()
	{
		return this.percent;
	}
	
	public int getNumber()
	{
		return this.number;
	}
	
	public void setPercent(double newPercent)
	{
		this.percent = newPercent;
	}
	
	public void setNumber(int newNumber)
	{
		this.number = newNumber;
	}
	
	public void setTotal(int total)
	{
		this.percent = ((double) this.number) / (double) total;
	}
}

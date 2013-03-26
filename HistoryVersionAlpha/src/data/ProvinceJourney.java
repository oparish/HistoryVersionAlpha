package data;

public class ProvinceJourney
{
	private int journeyNumber;
	private boolean isA;
	private String destination;
	private int distance;
	
	public ProvinceJourney(int newJourneyNumber, boolean newIsA)
	{
		journeyNumber = newJourneyNumber;
		isA = newIsA;
	}

	public int getJourneyNumber()
	{
		return journeyNumber;
	}
	
	public boolean getIsA()
	{
		return isA;
	}
	
	public String getDestination()
	{
		return destination;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public void setJourneyNumber(int value)
	{
		journeyNumber = value;
	}
	
	public void setIsA(boolean value)
	{
		isA = value;
	}
	
	public void setDestination(String value)
	{
		destination = value;
	}
	
	public void setDistance(int value)
	{
		distance = value;
	}
}

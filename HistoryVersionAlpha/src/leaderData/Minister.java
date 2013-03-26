package leaderData;




public class Minister extends PublicLeader implements CivicLeader
{
	private int farming;
	private int building;
	private int mining;

	public Minister(String newname) 
	{
		super(newname);
	}
	
	public LeaderType getType()
	{
		return LeaderType.MINISTER;
	}
	
	public int getFarming()
	{
		return farming;
	}
	
	public int getBuilding()
	{
		return building;
	}
	
	public int getMining()
	{
		return mining;
	}
	
	public void setFarming(int value)
	{
		farming = value;
	}
	
	public void setBuilding(int value)
	{
		building = value;
	}
	
	public void setMining(int value)
	{
		mining = value;
	}
	
}

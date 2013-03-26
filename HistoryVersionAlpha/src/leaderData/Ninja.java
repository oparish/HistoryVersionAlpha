package leaderData;

import data.CitizenType;
import sliders.SliderId;



public class Ninja extends NpcLeader
{

	private int spying;
	private int sabotage;
	private int assassination;
	
	public Ninja(String newname) 
	{
		super(newname);
	}
	
	public LeaderType getType()
	{
		return LeaderType.NINJA;
	}
	
	public int getSpying()
	{
		return spying;
	}
	
	public int getSabotage()
	{
		return sabotage;
	}
	
	public int getAssassination()
	{
		return assassination;
	}
	
	public void setSpying(int value)
	{
		spying = value;
	}
	
	public void setSabotage(int value)
	{
		sabotage = value;
	}
	
	public void setAssassination(int value)
	{
		assassination = value;
	}
	
	public void setMessengers(CitizenType value)
	{
		this.messengers = value;
	}
	
	public CitizenType getMessengers()
	{
		return this.messengers;
	}
}

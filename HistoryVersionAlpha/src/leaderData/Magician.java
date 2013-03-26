package leaderData;

import data.CitizenType;
import sliders.SliderId;



public class Magician extends NpcLeader
{

	private int teleportation;
	private int fires;
	private int storms;
	
	public Magician(String newname)
	{
		super(newname);
	}
	
	public LeaderType getType()
	{
		return LeaderType.MAGICIAN;
	}

	public int getTeleportation()
	{
		return teleportation;
	}
	
	public int getFires()
	{
		return fires;
	}
	
	public int getStorms()
	{
		return storms;
	}
	
	public void setTeleportation(int value)
	{
		teleportation = value;
	}
	
	public void setFires(int value)
	{
		fires = value;
	}
	
	public void setStorms(int value)
	{
		storms = value;
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

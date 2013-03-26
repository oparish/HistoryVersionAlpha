package leaderData;

import java.util.List;

import data.CitizenType;

public abstract class NpcLeader extends Leader
{
	private int loyalty;
	private int strategy;
	private int wages;
	private int reputationrequirement;
	private boolean tailorRequired;
	private boolean restaurantRequired;
	protected CitizenType messengers;
	
	NpcLeader(String newName)
	{
		super(newName);
	}
	
	public int getLoyalty()
	{
		return loyalty;
	}
	
	
	public int getStrategy()
	{
		return strategy;
	}
	
	public int getWages()
	{
		return wages;
	}
	
	public int getReputationRequirement()
	{
		return reputationrequirement;
	}
	
	public boolean getTailorRequirement()
	{
		return this.tailorRequired;
	}
	
	public boolean getRestaurantRequirement()
	{
		return this.restaurantRequired;
	}
	
	public CitizenType getMessengers()
	{
		return messengers;
	}
	
	public void setLoyalty(int value)
	{
		loyalty = value;
	}
	
	public void setStrategy(int value)
	{
		strategy = value;
	}
	
	public void setWages(int value)
	{
		wages = value;
	}
	
	public void setReputationrequirement(int value)
	{
		reputationrequirement = value;
	}
	
	public void setTailorRequirement(boolean value)
	{
		this.tailorRequired = value;
	}
	
	public void setRestaurantRequirement(boolean value)
	{
		this.restaurantRequired = value;
	}
	
	public void setMessengers(CitizenType value)
	{
		messengers = value;
		citizenTypes.add(this.messengers);
		messengers.setTotal(this.citizens);
	}
}

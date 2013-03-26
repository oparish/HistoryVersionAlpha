package leaderData;

import data.CitizenType;
import sliders.SliderId;



public class General extends PublicLeader implements MilitaryLeader
{

	private int offence;
	private int defence;
	private int recruitment;
	private CitizenType soldiers;
	
	public int getCitizens()
	{
		return this.getCarriers().getNumber() + 
		this.getMessengers().getNumber() + 
		this.getEnvoys().getNumber() +
		this.getSoldiers().getNumber();
	}
	
	public LeaderType getType()
	{
		return LeaderType.GENERAL;
	}
	
	public General(String newname)
	{
		super(newname);
	}

	public int getOffence()
	{
		return offence;
	}
	
	public int getDefence()
	{
		return defence;
	}
	
	public int getRecruitment()
	{
		return recruitment;
	}
	


	public void setOffence(int value)
	{
		offence = value;
	}
	
	public void setDefence(int value)
	{
		defence = value;
	}
	
	public void setRecruitment(int value)
	{
		recruitment = value;
	}
	
	public void setSoldiers(CitizenType value)
	{
		this.soldiers = value;
		citizenTypes.add(this.soldiers);
		this.soldiers.setTotal(this.citizens);
	}
	
	public CitizenType getSoldiers()
	{
		return this.soldiers;
	}

}

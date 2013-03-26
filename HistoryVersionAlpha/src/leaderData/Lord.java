package leaderData;

import data.CitizenType;
import sliders.SliderId;



public class Lord extends Leader implements CivicLeader, MilitaryLeader
{
	private CitizenType envoys;
	private CitizenType carriers;
	private CitizenType soldiers;
	private int farming;
	private int building;
	private int mining;
	private int offence;
	private int defence;
	private int recruitment;
	
	public CitizenType getSoldiers()
	{
		return this.soldiers;
	}
	
	public LeaderType getType()
	{
		return LeaderType.LORD;
	}
	
	public Lord(String newname)
	{
		super(newname);
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
	
	public CitizenType getEnvoys()
	{
		return envoys;
	}
	
	public CitizenType getCarriers()
	{
		return carriers;
	}
	
	public void setEnvoys(CitizenType value)
	{
		envoys = value;
		citizenTypes.add(this.envoys);
		envoys.setTotal(this.citizens);
	}
	
	public void setCarriers(CitizenType value)
	{
		carriers = value;
		citizenTypes.add(this.carriers);
		carriers.setTotal(this.citizens);
	}
	
	public void setSoldiers(CitizenType value)
	{
		soldiers = value;
		citizenTypes.add(this.soldiers);
		soldiers.setTotal(this.citizens);
	}
}

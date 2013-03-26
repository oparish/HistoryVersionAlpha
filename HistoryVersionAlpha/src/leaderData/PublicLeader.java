package leaderData;

import data.CitizenType;
import sliders.SliderId;

public abstract class PublicLeader extends NpcLeader
{
	protected CitizenType envoys;
	protected CitizenType carriers;
	
	PublicLeader(String newName)
	{
		super(newName);
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
	
	
}

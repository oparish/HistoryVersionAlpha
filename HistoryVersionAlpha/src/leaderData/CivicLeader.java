package leaderData;

import data.CitizenType;


public interface CivicLeader
{
	public CitizenType getEnvoys();

	public CitizenType getCarriers();
	
	public int getFarming();
	
	public int getBuilding();
	
	public int getMining();
	
	public void setEnvoys(CitizenType value);
	
	public void setCarriers(CitizenType value);
	
	public void setFarming(int value);
	
	public void setBuilding(int value);
	
	public void setMining(int value);
}

package leaderData;

import data.CitizenType;
import sliders.SliderId;

public interface MilitaryLeader
{
	public int getOffence();
	
	public int getDefence();
	
	public int getRecruitment();
	
	public void setOffence(int value);
	
	public void setDefence(int value);
	
	public void setRecruitment(int value);
	
	public void setSoldiers(CitizenType value);
	
	public CitizenType getSoldiers();
}

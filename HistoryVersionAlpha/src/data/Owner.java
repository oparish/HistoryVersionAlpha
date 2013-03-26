package data;

import java.util.ArrayList;

import leaderData.Leader;

public class Owner 
{
	private OwnerType type;
	private String name;
	private ArrayList<Province> provinces;
	private ArrayList<Leader> leaders;
	
	public Owner(OwnerType newType, String newName)
	{
		type = newType;
		name = newName;
		provinces = new ArrayList<Province>();
		leaders = new ArrayList<Leader>();
	}
	
	public void addProvince(Province province)
	{
		provinces.add(province);
	}
	
	public void removeProvince(Province province)
	{
		provinces.remove(province);
	}
	
	public void addLeader(Leader leader)
	{
		leaders.add(leader);
	}
	
	public void removeLeader(Leader leader)
	{
		leaders.remove(leader);
	}
	
	public OwnerType getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setType(OwnerType value)
	{
		type = value;
	}
	
	public void setName(String value)
	{
		name = value;
	}
	
	
}

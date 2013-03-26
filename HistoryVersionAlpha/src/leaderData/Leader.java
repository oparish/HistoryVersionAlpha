package leaderData;


import java.util.ArrayList;
import java.util.List;


import orderData.Order;
import orderData.OrderElement;
import ui.main.MainScreen;

import data.AllocationType;
import data.AssassinationTarget;
import data.BehaviourType;
import data.BuildingProject;
import data.CitizenContainer;
import data.CitizenType;
import data.Owner;
import data.SabotageTarget;
import data.Subject;

public abstract class Leader implements CitizenContainer, OrderElement
{
	private Owner owner;
	private String name;
	private String location;
	private int teachingability;
	private int tents;
	protected int citizens;
	protected ArrayList<CitizenType> citizenTypes;
	private ArrayList<Order> orders;
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	Leader(String newname)
	{
		name = newname;
		citizenTypes = new ArrayList<CitizenType>();
		orders = new ArrayList<Order>();
	}
	
	public abstract LeaderType getType();
	
	public int getCitizens()
	{
		return this.citizens;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public int getTeachingability()
	{
		return teachingability;
	}
	
	public int getTents()
	{
		return tents;
	}
	
	public void setName(String value)
	{
		name = value;
	}
	
	public void setLocation(String value)
	{
		location = value;
	}
	
	public void setTeachingability(int value)
	{
		teachingability = value;
	}
	
	public void setTents(int value)
	{
		tents = value;
	}
	
	public void setCitizens(int value)
	{
		this.citizens = value;
		for (CitizenType type : citizenTypes)
		{
			type.setTotal(value);
		}
	}
	
	public Object[] getValues()
	{
		ArrayList<Leader> teamLeaders = new ArrayList<Leader>();
		for(Leader leader : MainScreen.getInstance().getLeaders())
		{
			teamLeaders.add(leader);
		}
		return teamLeaders.toArray();
	}
	
	public OrderElement getDefaultValue(OrderElementType elementType)
	{
		switch(elementType)
		{
			case LEADER:
				return MainScreen.getInstance().getLeaders().get(0);
			case ALLOCATIONTYPE:
				return AllocationType.MOREBUILDERS;
			case ASSASSINATIONTARGET:
				return AssassinationTarget.TEST;
			case BEHAVIOURTYPE:
				return BehaviourType.CAUTIOUSDEFENCE;
			case BUILDINGPROJECT:
				return BuildingProject.MOREHOUSES;
			case SABOTAGETARGET:
				return SabotageTarget.TEST;
			case SUBJECT:
				return Subject.TEST;
		}
		return null;
	}
	
	public OrderElement[] getPossibleValues(OrderElementType elementType)
	{
		switch(elementType)
		{
		case LEADER:
			return (OrderElement[]) MainScreen.getInstance().getLeaders().toArray();
		case ALLOCATIONTYPE:
			return AllocationType.values();
		case ASSASSINATIONTARGET:
			return AssassinationTarget.values();
		case BEHAVIOURTYPE:
			return BehaviourType.values();
		case BUILDINGPROJECT:
			return BuildingProject.values();
		case SABOTAGETARGET:
			return SabotageTarget.values();
		case SUBJECT:
			return Subject.values();
		}
		return null;
	}
}

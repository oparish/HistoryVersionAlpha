package orderData;

import static orderData.OrderElement.OrderElementType.ALLOCATIONTYPE;
import static orderData.OrderElement.OrderElementType.ASSASSINATIONTARGET;
import static orderData.OrderElement.OrderElementType.BEHAVIOURTYPE;
import static orderData.OrderElement.OrderElementType.BUILDINGPROJECT;
import static orderData.OrderElement.OrderElementType.LEADER;
import static orderData.OrderElement.OrderElementType.SABOTAGETARGET;
import static orderData.OrderElement.OrderElementType.SUBJECT;
import orderData.OrderElement.OrderElementType;
import leaderData.Leader;
import data.AllocationType;
import data.AssassinationTarget;
import data.BehaviourType;
import data.BuildingProject;
import data.NamedEnum;
import data.SabotageTarget;
import data.Subject;

public enum OrderType implements NamedEnum
{
	MOVE_ORDER("Move"),
	ALLOCATION_ORDER("Allocation", ALLOCATIONTYPE), 
	ASSASSINATION_ORDER("Assassination", ASSASSINATIONTARGET), 
	BEHAVIOUR_ORDER("Behaviour", BEHAVIOURTYPE), 
	BUILD_ORDER("Build", BUILDINGPROJECT), 
	SABOTAGE_ORDER("Sabotage", SABOTAGETARGET), 
	TEACHING_ORDER("Teach", LEADER, SUBJECT);
	
	private final String name;
	private final OrderElementType[] elementTypes;
	
	public OrderElementType[] getElementTypes() {
		return elementTypes;
	}

	public String getName()
	{
		return name;
	}
	
	OrderType(String name, OrderElementType... elementTypes)
	{
		this.name = name;
		this.elementTypes = elementTypes;
	}
}

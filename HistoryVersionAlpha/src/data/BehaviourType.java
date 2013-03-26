package data;

import orderData.OrderElement;

public enum BehaviourType implements OrderElement
{
	MINDLESSATTACK("Mindless Attack"), CAUTIOUSDEFENCE("Cautious Defence");
	
	private final String name;
	
	public String getName() {
		return name;
	}

	public Object[] getValues()
	{
		return BehaviourType.values();
	}
	
	BehaviourType(String name)
	{
		this.name = name;
	}
}

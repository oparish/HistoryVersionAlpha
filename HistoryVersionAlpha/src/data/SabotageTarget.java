package data;

import orderData.OrderElement;

public enum SabotageTarget implements OrderElement 
{
	TEST("Test");
	
	private final String name;
	
	public String getName() {
		return name;
	}

	public Object[] getValues()
	{
		return SabotageTarget.values();
	}
	
	SabotageTarget(String name)
	{
		this.name = name;
	}
}

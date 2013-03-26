package data;

import orderData.OrderElement;

public enum AssassinationTarget implements OrderElement
{
	TEST("Test");
	
	private final String name;

	public String getName() {
		return name;
	}

	public Object[] getValues()
	{
		return AssassinationTarget.values();
	}
	
	AssassinationTarget(String name)
	{
		this.name = name;
	}
}

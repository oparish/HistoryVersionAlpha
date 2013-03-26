package data;

import orderData.OrderElement;

public enum Subject implements OrderElement
{
	TEST("Test");
	
	public Object[] getValues()
	{
		return Subject.values();
	}
	
	private final String name;
	
	Subject(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return this.name;
	}
}

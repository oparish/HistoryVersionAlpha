package data;

import orderData.OrderElement;

public enum AllocationType implements OrderElement
{
	MOREFARMERS("More Farmers"), MOREBUILDERS("More Builders"), MOREGATHERERS("More Gatherers");
	
	private final String name;
	
	public String getName() {
		return name;
	}

	public Object[] getValues()
	{
		return AllocationType.values();
	}
	
	AllocationType(String name)
	{
		this.name = name;
	}
}

package data;

import orderData.OrderElement;

public enum BuildingProject implements OrderElement
{
	MOREHOUSES("More Houses");
	
	private final String name;
	
	public String getName() {
		return name;
	}

	public Object[] getValues()
	{
		return BuildingProject.values();
	}
	
	BuildingProject(String name)
	{
		this.name = name;
	}
}

package data;

public enum ProjectType
{
	HOUSES("Houses"), TENTS("Tents"), SCHOOL("School"), TAILOR("Tailor"), RESTAURANT("Restaurant");
	
	private String name;
	
	ProjectType(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}

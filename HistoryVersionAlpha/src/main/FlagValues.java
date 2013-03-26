package main;

public enum FlagValues {
	
	REDFLAGVALUE(2), BLUEFLAGVALUE(3), GREENFLAGVALUE(4);
	
	private int number;
	
	FlagValues(int number)
	{
		this.number = number;
	}
	
	public int valueOf()
	{
		return this.number;
	}

}

package ui.main;

import data.Province;
import ui.ProvinceNumbers;

public class DetailedProvinceNumbers extends ProvinceNumbers
{
	private static final String FARMERS = "Farmers";
	private static final String BUILDERS = "Builders";
	private static final String GATHERERS = "Gatherers";
	
	private static final String[] names = {FARMERS, BUILDERS, GATHERERS};
	
	public DetailedProvinceNumbers(Province province)
	{
		super(province);
		addLabelData(names);
	}
	
	public DetailedProvinceNumbers()
	{
		super();
		addLabelData(names);
	}
	
	@Override
	public void setProvince(Province province)
	{
		setLabelValue(FARMERS, province.getFarmers().getNumber());
		setLabelValue(BUILDERS, province.getBuilders().getNumber());
		setLabelValue(GATHERERS, province.getGatherers().getNumber());
		super.setProvince(province);
	}
}

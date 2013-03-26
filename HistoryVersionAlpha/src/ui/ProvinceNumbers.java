package ui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


import data.Province;





public class ProvinceNumbers extends LabelPane
{
	private static final String NAME = "Name";
	private static final String OWNER = "Owner";
	private static final String SCHOOLPRESENT = "School Present";
	private static final String TAILORPRESENT = "Tailor Present";
	private static final String RESTAURANTPRESENT = "Restaurant Present";
	private static final String HOUSES = "Houses";
	private static final String OFFICES = "Offices";
	
	private static final String[] names = {NAME, OWNER, SCHOOLPRESENT, TAILORPRESENT, RESTAURANTPRESENT, HOUSES, OFFICES};
	
	public ProvinceNumbers(Province province)
	{
		super(names);
		setProvince(province);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public ProvinceNumbers()
	{
		super(names);
	}
	
	public void setProvince(Province province)
	{
		setLabelValue(NAME, province.getName());
		setLabelValue(OWNER, province.getOwner().getName());
		setLabelValue(SCHOOLPRESENT, String.valueOf(province.getSchoolpresent()));
		setLabelValue(TAILORPRESENT, String.valueOf(province.getTailorpresent()));
		setLabelValue(RESTAURANTPRESENT, String.valueOf(province.getRestaurantpresent()));
		setLabelValue(HOUSES, String.valueOf(province.getHouses()));
		setLabelValue(OFFICES, String.valueOf(province.getOffices()));
		validate();
		repaint();
	}
}

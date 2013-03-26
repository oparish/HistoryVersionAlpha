package ui;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Province;
import data.ProvinceJourney;

public class ProvinceJourneysDisplay extends LabelPane
{
	private static final String DESTINATION = "Destination";
	private static final String DISTANCE = "Distance";
	
	public ProvinceJourneysDisplay(Province province)
	{
		super();
		setLayout(new GridBagLayout());

		updateProvince(province);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void updateProvince(Province province)
	{
		for(ProvinceJourney provinceJourney:province.getProvinceJourneys())
		{
			setupLabelPair(DESTINATION, new JLabel(provinceJourney.getDestination()));
			setupLabelPair(DISTANCE, new JLabel(String.valueOf(provinceJourney.getDistance())));
			setupGap();
		}
	}
}

package ui.detail;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import sliders.ListenerSlider;
import sliders.SliderId;

public class FooterBar extends JPanel
{
	private static final String TRANSFER = "Transfer";
	private ListenerSlider transferSlider;
	
	FooterBar(boolean enabled)
	{
		transferSlider = new ListenerSlider(SliderId.TRANSFER_ID);
		this.setLayout(new GridBagLayout());
		this.add(transferSlider, setupSliderConstraints());
		transferSlider.addChangeListener(DetailScreen.getInstance());
		if (!enabled)
		{
			transferSlider.setEnabled(false);
		}
	}
	
	private GridBagConstraints setupSliderConstraints()
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = 0;
		componentConstraints.gridy = 0;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.gridwidth = 1;
		return componentConstraints;
	}
	
	public void setOtherSliderPosition(int number, int total)
	{
		setSliderPosition((total - number), total);
	}
	
	public void setSliderPosition(int number, int total)
	{
		double position = ((double) number) /((double) total) * transferSlider.getMaximum();
		transferSlider.setValue(Math.round((float) position));
	}
	
	public double getSliderNumber()
	{
		return ((double)transferSlider.getValue()) / ((double)transferSlider.getMaximum());
	}
}

package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Scrollable;

import ui.main.MainScreen;

import leaderData.Leader;
import main.MainFrame;

import data.Province;

@SuppressWarnings("serial")
public class ProvincePane extends JPanel implements Scrollable
{
	private static final int COMPONENTWIDTH = 200;
	private static final int COMPONENTHEIGHT = 20;
	
	private static final String DETAILS = "Details";
	
	private Province province;
	
	public ProvincePane(Province newProvince)
	{
		this();
		this.setProvince(newProvince);
	}
	
	public ProvincePane()
	{
		setLayout(new GridBagLayout());
		setupDetailsButton();
		setupProvinceLeaders();
	}
	
	public void setProvince()
	{
		setupNameLabel(province.getName());
	}
	
	private GridBagConstraints setupComponentConstraints(int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.anchor = GridBagConstraints.NORTH;
		componentConstraints.gridy = y;
		return componentConstraints;
	}
	
	private GridBagConstraints setupButtonConstraints(int y)
	{
		GridBagConstraints buttonConstraints = new GridBagConstraints();
		buttonConstraints.anchor = GridBagConstraints.NORTH;
		buttonConstraints.gridy = y;
		return buttonConstraints;
	}
	
	private void setupNameLabel(String name)
	{
		JLabel nameLabel = new JLabel(name);
		add(nameLabel, setupComponentConstraints(0));
	}
	
	private void setupDetailsButton()
	{
		ListenerButton detailsButton = new ListenerButton(DETAILS, ButtonId.DETAILS_ID);
		detailsButton.setPreferredSize(new Dimension(COMPONENTWIDTH, COMPONENTHEIGHT));
		detailsButton.addActionListener(MainFrame.getInstance());
		add(detailsButton, setupButtonConstraints(1));
	}
	
	private void setupProvinceLeaders()
	{
		int counter = 2;
		List<Leader> provinceLeaders = province.getProvinceLeaders();
		for (int i=0;i<provinceLeaders.size();i++)
		{
			Leader provinceLeader = provinceLeaders.get(i);
			LeaderButton leaderButton = new LeaderButton(((Leader)provinceLeader).getName());
			leaderButton.addActionListener(MainScreen.getInstance());
			leaderButton.setLeader(provinceLeader);
			
			leaderButton.setPreferredSize(new Dimension(COMPONENTWIDTH, COMPONENTHEIGHT));
			add(leaderButton, setupButtonConstraints(counter));
			counter++;
		}
	}
	
	public Province getProvince()
	{
		return province;
	}
	
	public void setProvince(Province value)
	{
		province = value;
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
}

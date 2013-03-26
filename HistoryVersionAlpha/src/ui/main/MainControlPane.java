package ui.main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import leaderData.Leader;

import data.Province;

public class MainControlPane extends JPanel
{
	ProvinceFrame provinceFrame;
	LeaderFrame leaderFrame;
	
	public MainControlPane()
	{
		this.setLayout(new GridLayout(1,0));
		this.provinceFrame = new ProvinceFrame();
		this.leaderFrame = new LeaderFrame();
		this.add(this.provinceFrame);
		this.add(this.leaderFrame);
	}
	
	public void changeProvince(Province selectedProvince)
	{
		this.provinceFrame.changeDetailedProvinceNumbers(selectedProvince);
	}
	
	public void changeLeader(Leader selectedLeader)
	{
		leaderFrame.changeLeader(selectedLeader);
	}
}

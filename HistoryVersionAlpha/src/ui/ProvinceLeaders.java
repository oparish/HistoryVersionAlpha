package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.detail.DetailScreen;
import ui.main.MainScreen;

import allocation.AllocationSlider;
import allocation.LeaderAllocation;
import allocation.LordAllocation;
import allocation.MilitaryLeaderAllocation;
import allocation.NpcLeaderAllocation;
import allocation.PublicLeaderAllocation;

import leaderData.Leader;
import leaderData.LeaderType;
import leaderData.Lord;
import leaderData.MilitaryLeader;
import leaderData.PublicLeader;
import lists.LeaderList;
import main.MainFrame;

import data.CitizenType;
import data.Province;

public class ProvinceLeaders extends JPanel
{
	private LeaderAllocation leaderAllocation;
	private LeaderPane leaderPane;
	
	public ProvinceLeaders(Leader leader)
	{
		setBorder(BorderFactory.createLineBorder(Color.black));
		if (leader != null)
		{
			this.setLayout(new GridBagLayout());
			this.setupLeaderAllocation(leader);
			this.setupLeaderStats(leader);
		}
		else
		{
			this.setupEmptyLeaders();
		}
	}
	
	private void setupEmptyLeaders()
	{
		this.add(new JLabel("No Leaders"));
	}
	
	private void setupLeaderStats(Leader leader)
	{
		this.leaderPane = new LeaderPane(leader);
		this.add(new JScrollPane(this.leaderPane), setupPaneConstraints(0,1));
	}
	
	private void setupLeaderAllocation(Leader leader)
	{
		if (leader instanceof Lord)
			this.leaderAllocation = new LordAllocation(leader);
		else if (leader instanceof MilitaryLeader)
			this.leaderAllocation = new MilitaryLeaderAllocation(leader);
		else if (leader instanceof PublicLeader)
			this.leaderAllocation = new PublicLeaderAllocation(leader);
		else
			this.leaderAllocation = new NpcLeaderAllocation(leader);
		this.add(this.leaderAllocation, this.setupAllocationConstraints(0, 0));
	}
	
	private void updateLeaderAllocation(Leader leader)
	{
		this.remove(this.leaderAllocation);
		setupLeaderAllocation(leader);
		this.validate();
		this.repaint();
	}
	
	private GridBagConstraints setupPaneConstraints(int x, int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = x;
		componentConstraints.gridy = y;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.BOTH;
		componentConstraints.anchor = GridBagConstraints.CENTER;
		return componentConstraints;
	}
	
	private GridBagConstraints setupAllocationConstraints(int x, int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = x;
		componentConstraints.gridy = y;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.BOTH;
		componentConstraints.anchor = GridBagConstraints.CENTER;
		return componentConstraints;
	}

	public LeaderAllocation getAllocation()
	{
		return this.leaderAllocation;
	}
	
	public void updateLeaderDetails(Leader leader)
	{
		updateLeaderAllocation(leader);
		this.leaderPane.setLeader(leader);
	}

}

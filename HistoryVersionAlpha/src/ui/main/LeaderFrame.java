package ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Province;

import leaderData.Leader;
import lists.LeaderList;
import lists.ProvinceList;


public class LeaderFrame extends JPanel
{
	private LeaderList leaderList;
	private DetailedLeaderPane detailedLeaderPane;
	
	public LeaderFrame()
	{
		this.setLayout(new GridLayout(0, 1));
		this.add(new JScrollPane(setupLeaderList()));
		this.add(new JScrollPane(setupDetailedLeaderPane()));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private JList setupLeaderList()
	{
		this.leaderList = new LeaderList(MainScreen.getInstance().getLeaders());
		this.leaderList.addMouseListener(MainScreen.getInstance());
		return this.leaderList;
	}
	
	private DetailedLeaderPane setupDetailedLeaderPane()
	{
		this.detailedLeaderPane = new DetailedLeaderPane();
		return this.detailedLeaderPane;
	}
	
	public void changeLeader(Leader selectedLeader)
	{
		this.detailedLeaderPane.setLeader(selectedLeader);
	}
}

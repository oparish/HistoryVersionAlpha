package lists;

import java.util.List;

import javax.swing.JComboBox;

import ui.ListId;
import ui.main.MainScreen;

import leaderData.Leader;

import data.Province;

public class LeaderList extends ListenerList
{
	public LeaderList(List<Leader> leaders)
	{
		super(leaders.toArray());
		this.setCellRenderer(LeaderCellRenderer.getInstance());
		this.id = ListId.SELECT_LEADER_ID;
	}
	
	public void updateLeaders(List<Leader> leaders)
	{
		this.setListData(leaders.toArray());
	}
}

package lists;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import leaderData.Leader;

import data.Province;

public class LeaderCellRenderer extends DefaultListCellRenderer
{
	private static LeaderCellRenderer leaderBoxRenderer = new LeaderCellRenderer();
	public static LeaderCellRenderer getInstance()
	{
		return LeaderCellRenderer.leaderBoxRenderer;
	}
	
	public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
	{
		Leader leader = (Leader) value;
		return super.getListCellRendererComponent(list, leader.getName(), index, isSelected, cellHasFocus);
	}
}

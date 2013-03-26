package ui;

import javax.swing.JButton;

import leaderData.Leader;

public class LeaderButton extends JButton
{
	private Leader leader;

	LeaderButton(String contentText) {
		setText(contentText);
	}
	
	public Leader getLeader()
	{
		return leader;	
	}
	
	public void setLeader(Leader value)
	{
		leader = value;
	}

}

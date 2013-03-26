package ui.main;

import ui.LeaderPane;
import leaderData.CivicLeader;
import leaderData.General;
import leaderData.Leader;
import leaderData.Lord;
import leaderData.Magician;
import leaderData.MilitaryLeader;
import leaderData.Minister;
import leaderData.Ninja;
import leaderData.NpcLeader;
import leaderData.PublicLeader;

@SuppressWarnings("serial")
public class DetailedLeaderPane extends LeaderPane
{
	private static final String LOCATION = "Location";
	private static final String MESSENGERS = "Messengers";
	private static final String SOLDIERS = "Soldiers";
	private static final String ENVOYS = "Envoys";
	private static final String CARRIERS = "Carriers";
	
	private static final String[] names = {LOCATION, MESSENGERS, SOLDIERS, ENVOYS, CARRIERS};
	
	public DetailedLeaderPane()
	{
		super();
		addLabelData(names);
	}
	
	public DetailedLeaderPane(Leader leader)
	{
		super(leader);
		addLabelData(names);
	}
	
	@Override
	protected void setupUniStats()
	{
		super.setupUniStats();
		setLabelValue(LOCATION, leader.getLocation());
	}
	
	@Override
	protected void setupNpcLeaderStats()
	{
		super.setupNpcLeaderStats();
		NpcLeader npcLeader = (NpcLeader) this.leader;
		setLabelValue(MESSENGERS, npcLeader.getMessengers().getNumber());
	}
	
	@Override
	protected void setupGeneralStats()
	{
		super.setupGeneralStats();
		General general = (General) this.leader;
		setLabelValue(SOLDIERS, general.getSoldiers().getNumber());
		setupPublicLeaderStats(general);
	}
	
	@Override
	protected void setupMinisterStats()
	{
		super.setupMinisterStats();
		setupPublicLeaderStats((Minister) this.leader);
	}
	
	protected void setupPublicLeaderStats(PublicLeader publicLeader)
	{
		setLabelValue(CARRIERS, publicLeader.getCarriers().getNumber());
		setLabelValue(ENVOYS, publicLeader.getEnvoys().getNumber());
	}
}

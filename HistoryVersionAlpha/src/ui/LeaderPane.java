package ui;

import java.awt.GridBagLayout;
import java.util.List;

import leaderData.CivicLeader;
import leaderData.General;
import leaderData.Leader;
import leaderData.LeaderType;
import leaderData.Lord;
import leaderData.Magician;
import leaderData.MilitaryLeader;
import leaderData.Minister;
import leaderData.Ninja;
import leaderData.NpcLeader;


@SuppressWarnings("serial")
public class LeaderPane extends LabelPane
{	
	private static final String YES = "Yes";
	private static final String NO = "No";
	
	private static final String TYPE = "Type";
	private static final String LOYALTY = "Loyalty";
	private static final String TEACHINGABILITY = "Teaching Ability";
	private static final String STRATEGY = "Strategy";
	private static final String WAGES = "Wages";
	private static final String REPUTATIONREQUIREMENT = "Required Reputation";
	private static final String TAILORREQUIREMENT = "Tailor Required";
	private static final String RESTAURANTREQUIREMENT = "Restaurant Required";

	private static final String OFFENCE = "Offence";
	private static final String DEFENCE = "Defences";
	private static final String RECRUITMENT = "Recruitment";

	private static final String TENTS = "Tents";

	private static final String FARMINGABILITY = "Farming Ability";
	private static final String MININGABILITY = "Mining Ability";
	private static final String BUILDINGABILITY = "Building Ability";
	private static final String TELEPORTATIONABILITY = "Teleportation Ability";
	private static final String FIRESABILITY = "Fires Ability";
	private static final String STORMSABILITY = "Storms Ability";
	private static final String SPYING = "Spying";
	private static final String SABOTAGE = "Sabotage";
	private static final String ASSASSINATION = "Assassination";
	
	private static final String[] names = {TYPE, TEACHINGABILITY, FARMINGABILITY, MININGABILITY, 
		BUILDINGABILITY, OFFENCE, DEFENCE, RECRUITMENT, SPYING, SABOTAGE, ASSASSINATION, 
		TELEPORTATIONABILITY, FIRESABILITY, STORMSABILITY, TENTS, LOYALTY, STRATEGY, WAGES, 
		REPUTATIONREQUIREMENT, TAILORREQUIREMENT, RESTAURANTREQUIREMENT};		
	private static final String[] ministerStatNames = {TEACHINGABILITY, FARMINGABILITY, MININGABILITY, 
		BUILDINGABILITY};	
	private static final String[] generalStatNames = {OFFENCE, DEFENCE, RECRUITMENT};	
	private static final String[] ninjaStatNames = {SPYING, SABOTAGE, ASSASSINATION};
	private static final String[] magicianStatNames = {TELEPORTATIONABILITY, FIRESABILITY, 
		STORMSABILITY};
	private static final String[] npcStatNames ={LOYALTY, STRATEGY, WAGES, 
		REPUTATIONREQUIREMENT, TAILORREQUIREMENT, RESTAURANTREQUIREMENT};
	private static final String[] uniStatNames = {TEACHINGABILITY, TYPE, TENTS};
	
	protected Leader leader = null;

	public LeaderPane(Leader newLeader)
	{
		super(names);
		this.setLeader(newLeader);
	}
	
	public LeaderPane()
	{
		super(names);
	}
	
	public void setLeader(Leader leader)
	{
		this.leader = leader;
		setupLeaderStats();
		setupOrders();
	}
	
	public void setBlank()
	{
		setupBlankUniStats();
		setupBlankGeneralStats();
		setupBlankMinisterStats();
		setupBlankNinjaStats();
		setupBlankMagicianStats();
		setupBlankNpcLeaderStats();
	}
	
	private void setupBlankUniStats()
	{
		setBlankValues(uniStatNames);
	}
	
	private void setupBlankGeneralStats()
	{
		setBlankValues(generalStatNames);
	}
	
	private void setupBlankMinisterStats()
	{
		setBlankValues(ministerStatNames);
	}
	
	private void setupBlankNinjaStats()
	{
		setBlankValues(ninjaStatNames);
	}
	
	private void setupBlankMagicianStats()
	{
		setBlankValues(magicianStatNames);
	}
	
	private void setupBlankNpcLeaderStats()
	{
		setBlankValues(npcStatNames);
	}
	
	protected void setupLeaderStats()
	{
		if (leader instanceof Lord)
		{
			setupUniStats();
			setupGeneralStats();
			setupMinisterStats();
			setupBlankNinjaStats();
			setupBlankMagicianStats();
			setupBlankNpcLeaderStats();
		}
		else
		{
			setupTypeStats();
			setupNpcLeaderStats();
		}
	}
	
	protected void setupUniStats()
	{
		setLabelValue(TYPE, this.getTypeString());
		setLabelValue(TENTS, leader.getTents());
	}
	
	protected void setupGeneralStats()
	{
		General general = (General) this.leader;
		setLabelValue(OFFENCE, general.getOffence());
		setLabelValue(DEFENCE, general.getDefence());
		setLabelValue(RECRUITMENT, general.getRecruitment());
	}
	
	protected void setupMinisterStats()
	{
		Minister minister = (Minister) this.leader;
		setLabelValue(FARMINGABILITY, minister.getFarming());
		setLabelValue(MININGABILITY, minister.getMining());
		setLabelValue(BUILDINGABILITY, minister.getBuilding());
	}
	
	protected void setupNinjaStats()
	{
		Ninja ninja = (Ninja) this.leader;
		setLabelValue(SPYING, ninja.getSpying());
		setLabelValue(SABOTAGE, ninja.getSabotage());
		setLabelValue(ASSASSINATION, ninja.getAssassination());
	}
	
	protected void setupMagicianStats()
	{
		Magician magician = (Magician) this.leader;
		setLabelValue(FIRESABILITY, magician.getFires());
		setLabelValue(TELEPORTATIONABILITY, magician.getTeleportation());
		setLabelValue(STORMSABILITY, magician.getStorms());
	}
	
	protected void setupNpcLeaderStats()
	{
		NpcLeader npcLeader = (NpcLeader) this.leader;
		setLabelValue(LOYALTY, npcLeader.getLoyalty());
		setLabelValue(TEACHINGABILITY, npcLeader.getTeachingability());
		setLabelValue(STRATEGY, npcLeader.getStrategy());
		setLabelValue(WAGES, npcLeader.getWages());
		setLabelValue(REPUTATIONREQUIREMENT, npcLeader.getReputationRequirement());
		setLabelValue(TAILORREQUIREMENT, getYesNoString(npcLeader.getTailorRequirement()));
		setLabelValue(RESTAURANTREQUIREMENT, getYesNoString(npcLeader.getRestaurantRequirement()));
	}
	
	private String getYesNoString(boolean value)
	{
		if (value)
			return YES;
		else
			return NO;
	}
	
	private String getTypeString()
	{
		switch(leader.getType())
		{
		case LORD:
			return "Lord";
		case MINISTER:
			return "Minister";
		case GENERAL:
			return "General";
		case NINJA:
			return "Ninja";
		case MAGICIAN:
			return "Magician";
		default:
			return null;
		}
	}
	
	private void setupTypeStats()
	{
		LeaderType type = leader.getType();
		
		if (type == LeaderType.MINISTER)
			setupMinisterStats();
		else
			setupBlankMinisterStats();
		
		if (type == LeaderType.GENERAL)
			setupMinisterStats();
		else
			setupBlankGeneralStats();
		
		if (type == LeaderType.NINJA)
			setupMinisterStats();
		else
			setupBlankNinjaStats();
		
		if (type == LeaderType.MAGICIAN)
			setupMinisterStats();
		else
			setupBlankMagicianStats();
	}
	
	private void setupOrders()
	{
		
	}
}

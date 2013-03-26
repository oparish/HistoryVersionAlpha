package allocation;

import leaderData.Leader;
import leaderData.MilitaryLeader;
import leaderData.NpcLeader;
import leaderData.PublicLeader;
import leaderData.CivicLeader;
import sliders.ListenerSlider;
import sliders.SliderId;
import ui.ButtonId;
import ui.detail.DetailScreen;
import ui.detail.ListenerRadioButton;
import ui.detail.UpdatableLabel;
import data.CitizenType;

public class MilitaryLeaderAllocation extends PublicLeaderAllocation
{
	private static final String MOVE_SOLDIERS = "Move Soldiers";
	private CitizenType soldiers;
	private AllocationSlider soldierSlider;
	private UpdatableLabel soldierLabel;
	private ListenerRadioButton soldierButton;
	
	public MilitaryLeaderAllocation(Leader newLeader)
	{
		super(newLeader);
	}
	
	@Override
	protected void setupCitizenTypes()
	{
		super.setupCitizenTypes();
		this.soldiers = ((MilitaryLeader) leader).getSoldiers();
	}
	
	protected void setupLabels()
	{
		super.setupLabels();
		
		this.soldierLabel = addLabel(this.soldiers);
		this.add(this.soldierLabel, setupComponentConstraints(LABEL_POS,3));
	}
	
	protected void addAllSliders()
	{		
		super.addAllSliders();
		
		this.soldierSlider = addSlider(SliderId.SOLDIER_ID, ((MilitaryLeader) leader).getSoldiers());
		this.add(this.soldierSlider, setupComponentConstraints(SLIDER_POS,3));
	}
	
	protected void setupButtons()
	{
		super.setupButtons();
		
		this.soldierButton = new ListenerRadioButton(MOVE_SOLDIERS, ButtonId.MOVE_SOLDIERS_ID);
		this.soldierButton.addActionListener(this);
		this.add(this.soldierButton, setupButtonConstraints(BUTTON_POS,3));
	}
	
	protected void setupButtonGroup()
	{
		super.setupButtonGroup();
		this.buttonGroup.add(this.soldierButton);
	}
	
	protected void pressedMoveButton(ButtonId id)
	{
		this.selectedSlider.setEnabled(true);
		switch(id)
		{
		case MOVE_CARRIERS_ID:
			selectMoveSlider(this.carrierSlider);
			break;
		case MOVE_ENVOYS_ID:
			selectMoveSlider(this.envoySlider);
			break;
		case MOVE_SOLDIERS_ID:
			selectMoveSlider(this.soldierSlider);
			break;
		case MOVE_MESSENGERS_ID:
			selectMoveSlider(this.messengerSlider);
			break;
		}
	}

}

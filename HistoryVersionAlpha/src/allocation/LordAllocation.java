package allocation;

import leaderData.CivicLeader;
import leaderData.Leader;
import leaderData.MilitaryLeader;
import leaderData.NpcLeader;
import leaderData.PublicLeader;
import sliders.ListenerSlider;
import sliders.SliderId;
import ui.ButtonId;
import ui.detail.DetailScreen;
import ui.detail.ListenerRadioButton;
import ui.detail.UpdatableLabel;
import data.CitizenType;

public class LordAllocation extends LeaderAllocation
{
	private static final String MOVE_ENVOYS = "Move Envoys";
	private static final String MOVE_CARRIERS = "Move Carriers";
	private static final String MOVE_SOLDIERS = "Move Soldiers";
	private CitizenType envoys;
	private CitizenType carriers;
	private CitizenType soldiers;
	private AllocationSlider envoySlider;
	private AllocationSlider carrierSlider;
	private AllocationSlider soldierSlider;
	private UpdatableLabel envoyLabel;
	private UpdatableLabel carrierLabel;
	private UpdatableLabel soldierLabel;
	private ListenerRadioButton envoyButton;
	private ListenerRadioButton carrierButton;
	private ListenerRadioButton soldierButton;
	
	public LordAllocation(Leader newLeader)
	{
		super(newLeader);
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
		}
	}
	
	@Override
	protected void setupCitizenTypes()
	{
		this.envoys = ((PublicLeader) leader).getEnvoys();
		this.carriers = ((PublicLeader) leader).getCarriers();
		this.soldiers = ((MilitaryLeader) leader).getSoldiers();
	}
	
	protected void setupLabels()
	{
		this.envoyLabel = addLabel(this.envoys);
		this.add(this.envoyLabel, setupComponentConstraints(LABEL_POS,0));	
		this.carrierLabel = addLabel(this.carriers);
		this.add(this.carrierLabel, setupComponentConstraints(LABEL_POS,1));
		this.soldierLabel = addLabel(this.soldiers);
		this.add(this.soldierLabel, setupComponentConstraints(LABEL_POS,2));
	}
	
	protected void setupButtons()
	{
		this.envoyButton = new ListenerRadioButton(MOVE_ENVOYS, ButtonId.MOVE_ENVOYS_ID);
		this.envoyButton.addActionListener(this);
		this.add(this.envoyButton, setupButtonConstraints(BUTTON_POS,0));
		
		this.carrierButton = new ListenerRadioButton(MOVE_CARRIERS, ButtonId.MOVE_CARRIERS_ID);
		this.carrierButton.addActionListener(this);
		this.add(this.carrierButton, setupButtonConstraints(BUTTON_POS,1));
		
		this.soldierButton = new ListenerRadioButton(MOVE_SOLDIERS, ButtonId.MOVE_SOLDIERS_ID);
		this.soldierButton.addActionListener(this);
		this.add(this.soldierButton, setupButtonConstraints(BUTTON_POS,2));
	}
	
	protected void setupButtonGroup()
	{
		this.buttonGroup.add(this.envoyButton);
		this.buttonGroup.add(this.carrierButton);
		this.buttonGroup.add(this.soldierButton);
	}
	
	protected void addAllSliders()
	{			
		this.envoySlider = addSlider(SliderId.ENVOY_ID, ((PublicLeader)leader).getEnvoys());
		this.add(this.envoySlider, setupComponentConstraints(SLIDER_POS,0));	
		this.carrierSlider = addSlider(SliderId.CARRIER_ID, ((PublicLeader)leader).getCarriers());
		this.add(this.carrierSlider, setupComponentConstraints(SLIDER_POS,1));
		this.soldierSlider = addSlider(SliderId.SOLDIER_ID, ((MilitaryLeader)leader).getSoldiers());
		this.add(this.soldierSlider, setupComponentConstraints(SLIDER_POS,2));
	}
}

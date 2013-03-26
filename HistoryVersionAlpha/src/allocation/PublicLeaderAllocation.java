package allocation;

import leaderData.Leader;
import leaderData.NpcLeader;
import leaderData.PublicLeader;
import sliders.ListenerSlider;
import sliders.SliderId;
import ui.ButtonId;
import ui.detail.DetailScreen;
import ui.detail.ListenerRadioButton;
import ui.detail.UpdatableLabel;
import data.CitizenType;

public class PublicLeaderAllocation extends NpcLeaderAllocation
{
	private static final String MOVE_ENVOYS = "Move Envoys";
	private static final String MOVE_CARRIERS = "Move Carriers";
	protected CitizenType envoys;
	protected CitizenType carriers;
	protected AllocationSlider envoySlider;
	protected AllocationSlider carrierSlider;
	protected UpdatableLabel envoyLabel;
	protected UpdatableLabel carrierLabel;
	protected ListenerRadioButton envoyButton;
	protected ListenerRadioButton carrierButton;
	
	
	public PublicLeaderAllocation(Leader newLeader)
	{
		super(newLeader);
	}
	
	@Override
	protected void setupCitizenTypes()
	{
		super.setupCitizenTypes();
		this.envoys = ((PublicLeader) leader).getEnvoys();
		this.carriers = ((PublicLeader) leader).getCarriers();
	}
	
	protected void setupLabels()
	{
		super.setupLabels();
		
		this.envoyLabel = addLabel(this.envoys);
		this.add(this.envoyLabel, setupComponentConstraints(LABEL_POS,1));
		
		this.carrierLabel = addLabel(this.carriers);
		this.add(this.carrierLabel, setupComponentConstraints(LABEL_POS,2));
	}
	
	protected void addAllSliders()
	{		
		super.addAllSliders();
		
		this.envoySlider = addSlider(SliderId.ENVOY_ID, ((PublicLeader) leader).getEnvoys());
		this.add(this.envoySlider, setupComponentConstraints(SLIDER_POS,1));
		
		this.carrierSlider = addSlider(SliderId.CARRIER_ID, ((PublicLeader) leader).getCarriers());
		this.add(this.carrierSlider, setupComponentConstraints(SLIDER_POS,2));
	}
	
	protected void setupButtons()
	{
		super.setupButtons();
		
		this.envoyButton = new ListenerRadioButton(MOVE_ENVOYS, ButtonId.MOVE_ENVOYS_ID);
		this.envoyButton.addActionListener(this);
		this.add(this.envoyButton, setupButtonConstraints(BUTTON_POS,1));
		
		this.carrierButton = new ListenerRadioButton(MOVE_CARRIERS, ButtonId.MOVE_CARRIERS_ID);
		this.carrierButton.addActionListener(this);
		this.add(this.carrierButton, setupButtonConstraints(BUTTON_POS,2));
	}
	
	protected void setupButtonGroup()
	{
		super.setupButtonGroup();
		this.buttonGroup.add(this.envoyButton);
		this.buttonGroup.add(this.carrierButton);
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
		case MOVE_MESSENGERS_ID:
			selectMoveSlider(this.messengerSlider);
			break;
		}
	}
	
}

package allocation;

import leaderData.Leader;
import leaderData.NpcLeader;
import sliders.ListenerSlider;
import sliders.SliderId;
import sliders.SliderSet;
import ui.ButtonId;
import ui.detail.DetailScreen;
import ui.detail.ListenerRadioButton;
import ui.detail.UpdatableLabel;
import data.CitizenType;

public class NpcLeaderAllocation extends LeaderAllocation
{
	private static final String MOVE_MESSENGERS = "Move Messengers";
	protected CitizenType messengers;
	protected AllocationSlider messengerSlider;
	protected UpdatableLabel messengerLabel;
	protected ListenerRadioButton messengerButton;
	
	public NpcLeaderAllocation(Leader newLeader)
	{
		super(newLeader);
		this.selectMoveSlider(this.messengerSlider);
	}
	
	protected void setupCitizenTypes()
	{
		this.messengers = ((NpcLeader) leader).getMessengers();
	}
	
	protected void setupLabels()
	{
		this.messengerLabel = addLabel(this.messengers);
		this.add(this.messengerLabel, setupComponentConstraints(LABEL_POS,0));
	}
	
	protected void setupButtons()
	{
		this.messengerButton = new ListenerRadioButton(MOVE_MESSENGERS, ButtonId.MOVE_MESSENGERS_ID);
		this.messengerButton.addActionListener(this);
		this.add(this.messengerButton, setupButtonConstraints(BUTTON_POS,0));
		messengerButton.setSelected(true);
	}
	
	protected void setupButtonGroup()
	{
		this.buttonGroup.add(this.messengerButton);
	}
	
	protected void addAllSliders()
	{		
		this.sliderSet = new SliderSet();
		this.messengerSlider = addSlider(SliderId.MESSENGER_ID, ((NpcLeader) leader).getMessengers());
		this.add(this.messengerSlider, setupComponentConstraints(SLIDER_POS,0));
	}

	@Override
	protected void pressedMoveButton(ButtonId id)
	{
		
	}
}

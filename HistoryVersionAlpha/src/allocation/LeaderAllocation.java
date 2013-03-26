package allocation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import leaderData.Leader;
import leaderData.MilitaryLeader;
import leaderData.NpcLeader;
import leaderData.PublicLeader;

import sliders.ListenerSlider;
import sliders.SliderId;
import sliders.SliderSet;
import ui.ButtonId;
import ui.detail.DetailScreen;
import ui.detail.ListenerRadioButton;
import ui.detail.UpdatableLabel;

import data.CitizenType;

public abstract class LeaderAllocation extends JPanel implements ActionListener
{
	protected static final int SLIDER_POS = 0;
	protected static final int LABEL_POS = 1;
	protected static final int BUTTON_POS = 2;

	protected AllocationSlider selectedSlider;
	protected SliderSet sliderSet;
	protected Leader leader;
	protected ButtonGroup buttonGroup;
	protected ArrayList<UpdatableLabel> labels;
	
	LeaderAllocation(Leader newLeader)
	{
		this.leader = newLeader;
		this.labels = new ArrayList<UpdatableLabel>();
		this.buttonGroup = new ButtonGroup();
		this.setLayout(new GridBagLayout());
		this.setupCitizenTypes();
		this.setupLabels();
		this.setupSliders();
		this.setupButtons();
		this.setupButtonGroup();
	}
	
	protected abstract void setupCitizenTypes();
	
	protected abstract void setupLabels();
	
	protected UpdatableLabel addLabel(CitizenType type)
	{
		UpdatableLabel newLabel = new UpdatableLabel(type);
		this.labels.add(newLabel);
		return newLabel;
	}
	
	public SliderSet getSliderSet()
	{
		return this.sliderSet;
	}
	
	protected abstract void setupButtons();
	
	protected abstract void setupButtonGroup();
	
	protected abstract void addAllSliders();
	
	private void setupSliders()
	{
		addAllSliders();
		this.sliderSet.correctSliders();
		addChangeListeners();
	}
	
	private void addChangeListeners()
	{
		for (AllocationSlider slider : this.sliderSet.getMembers())
		{
			slider.addChangeListener(DetailScreen.getInstance());
		}
	}
	
	public void updateCitizenDivision(AllocationSlider destinationSlider)
	{
		sliderSet.tryUpdatingSliders(destinationSlider, this.selectedSlider, this.leader);
		updateLabels();
	}
	
	protected AllocationSlider addSlider(SliderId sliderId, CitizenType citizenType)
	{
		AllocationSlider newSlider = new AllocationSlider(sliderId, citizenType);
		this.sliderSet.addSlider(newSlider);
		return newSlider;
	}
	
	protected abstract void pressedMoveButton(ButtonId id);
	
	protected void selectMoveSlider(AllocationSlider slider)
	{
		slider.setEnabled(false);
		this.selectedSlider = slider;
	}
	
	public void updateLabels()
	{
		for (UpdatableLabel label : this.labels)
			label.updateText();
	}
	
	protected GridBagConstraints setupComponentConstraints(int x, int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = x;
		componentConstraints.gridy = y;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.BOTH;
		return componentConstraints;
	}
	
	protected GridBagConstraints setupButtonConstraints(int x, int y)
	{
		GridBagConstraints buttonConstraints = new GridBagConstraints();
		buttonConstraints.gridx = x;
		buttonConstraints.gridy = y;
		buttonConstraints.weightx = 1;
		buttonConstraints.weighty = 1;
		buttonConstraints.fill = GridBagConstraints.BOTH;
		return buttonConstraints;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source.getClass() == ListenerRadioButton.class)
		{
			pressedMoveButton(((ListenerRadioButton) source).getId());
		}
	}
	
	public AllocationSlider getSelectedSlider()
	{
		return this.selectedSlider;
	}
}

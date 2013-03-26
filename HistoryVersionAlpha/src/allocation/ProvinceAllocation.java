package allocation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sliders.ListenerSlider;
import sliders.SliderId;
import sliders.SliderSet;
import ui.ButtonId;
import ui.ListenerButton;
import ui.detail.DetailScreen;
import ui.detail.ListenerRadioButton;
import ui.detail.UpdatableLabel;

import data.CitizenContainer;
import data.CitizenType;
import data.Province;

public class ProvinceAllocation extends JPanel
{
	private static final int SLIDER_POS = 0;
	private static final int LABEL_POS = 1;
	private static final int BUTTON_POS = 2;
	private static final String MOVE_FARMERS = "Move Farmers";
	private static final String MOVE_BUILDERS = "Move Builders";
	private static final String MOVE_GATHERERS = "Move Gatherers";
	
	ArrayList<UpdatableLabel> labels = new ArrayList<UpdatableLabel>();
	AllocationSlider farmerSlider;
	AllocationSlider builderSlider;
	AllocationSlider gathererSlider;
	UpdatableLabel farmerLabel;
	UpdatableLabel builderLabel;
	UpdatableLabel gathererLabel;
	Province province;
	SliderSet provinceSliderSet;
	ButtonGroup buttonGroup;
	
	private AllocationSlider selectedSlider;
	
	public ProvinceAllocation(Province province)
	{
		this.province = province;
		this.setLayout(new GridBagLayout());
		this.setupSliders();
		this.setupLabels();
		this.setupButtons();
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void setupLabels()
	{
		this.farmerLabel = setupLabel(this.province.getFarmers());
		this.builderLabel = setupLabel(this.province.getBuilders());
		this.gathererLabel = setupLabel(this.province.getGatherers());
		this.updateLabels();
		add(farmerLabel, setupComponentConstraints(LABEL_POS, 0));
		add(builderLabel, setupComponentConstraints(LABEL_POS, 1));
		add(gathererLabel, setupComponentConstraints(LABEL_POS, 2));
	}
	
	private void setupButtons()
	{
		this.buttonGroup = new ButtonGroup();
		
		ListenerRadioButton moveFarmersButton = setupRadioButton(MOVE_FARMERS, ButtonId.MOVE_FARMERS_ID);
		ListenerRadioButton moveBuildersButton = setupRadioButton(MOVE_BUILDERS, ButtonId.MOVE_BUILDERS_ID);
		ListenerRadioButton moveGatherersButton = setupRadioButton(MOVE_GATHERERS, ButtonId.MOVE_GATHERERS_ID);

		add(moveFarmersButton, setupButtonConstraints(BUTTON_POS, 0));
		add(moveBuildersButton, setupButtonConstraints(BUTTON_POS, 1));
		add(moveGatherersButton, setupButtonConstraints(BUTTON_POS, 2));
		
		moveFarmersButton.setSelected(true);
	}
	
	private ListenerRadioButton setupRadioButton(String text, ButtonId buttonId)
	{
		ListenerRadioButton radioButton = new ListenerRadioButton(text, buttonId);
		radioButton.addActionListener(DetailScreen.getInstance());
		this.buttonGroup.add(radioButton);
		return radioButton;
	}
	
	private UpdatableLabel setupLabel(CitizenType type)
	{
		UpdatableLabel label = new UpdatableLabel(type);
		this.labels.add(label);
		return label;
	}
	
	public SliderSet getSliderSet()
	{
		return this.provinceSliderSet;
	}
	
	private void setupSliders()
	{
		this.provinceSliderSet = new SliderSet();
		
		this.farmerSlider = setupProvinceSlider(SliderId.FARMER_ID, province.getFarmers());
		this.builderSlider = setupProvinceSlider(SliderId.BUILDER_ID, province.getBuilders());
		this.gathererSlider = setupProvinceSlider(SliderId.GATHERER_ID, province.getGatherers());
		this.provinceSliderSet.correctSliders();
		
		for (AllocationSlider slider : this.provinceSliderSet.getMembers())
			slider.addChangeListener(DetailScreen.getInstance());
		
		add(farmerSlider, setupComponentConstraints(SLIDER_POS, 0));
		add(builderSlider, setupComponentConstraints(SLIDER_POS, 1));
		add(gathererSlider, setupComponentConstraints(SLIDER_POS, 2));
		
		selectMoveSlider(this.farmerSlider);
	}
	
	private AllocationSlider setupProvinceSlider(SliderId sliderId, CitizenType citizenType)
	{
		AllocationSlider slider = new AllocationSlider(sliderId, citizenType);
		this.provinceSliderSet.addSlider(slider);
		return slider;
	}
	
	public void updateLabels()
	{
		for (UpdatableLabel label : this.labels)
			label.updateText();
	}
	
	public void setFarmerSliderPosition(int max)
	{
		double value = province.getFarmers().getPercent() * max;
		this.farmerSlider.setValue(Math.round((float)value));
	}
	
	public void setBuilderSliderPosition(int max)
	{
		double value = province.getBuilders().getPercent() * max;
		this.builderSlider.setValue(Math.round((float)value));
	}
	
	public void setGathererSliderPosition(int max)
	{
		double value = province.getGatherers().getPercent() * max;
		this.gathererSlider.setValue(Math.round((float)value));
	}
	
	private GridBagConstraints setupComponentConstraints(int x, int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = x;
		componentConstraints.gridy = y;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		return componentConstraints;
	}
	private GridBagConstraints setupButtonConstraints(int x, int y)
	{
		GridBagConstraints buttonConstraints = new GridBagConstraints();
		buttonConstraints.gridx = x;
		buttonConstraints.gridy = y;
		buttonConstraints.weightx = 1;
		buttonConstraints.weighty = 1;
		buttonConstraints.fill = GridBagConstraints.BOTH;
		return buttonConstraints;
	}
	
	public void pressedMoveButton(ButtonId id)
	{
		for (AllocationSlider slider : provinceSliderSet.getMembers())
			slider.setEnabled(true);
		
		switch(id)
		{
			case MOVE_FARMERS_ID:
				selectMoveSlider(this.farmerSlider);
				break;
			case MOVE_BUILDERS_ID:
				selectMoveSlider(this.builderSlider);
				break;
			case MOVE_GATHERERS_ID:
				selectMoveSlider(this.gathererSlider);
				break;
		}
	}
	
	private void selectMoveSlider(AllocationSlider slider)
	{
		slider.setEnabled(false);
		this.selectedSlider = slider;
	}
	
	public void updateCitizenDivision(AllocationSlider destinationSlider)
	{
		this.provinceSliderSet.tryUpdatingSliders(destinationSlider, this.selectedSlider, this.province);
		updateLabels();
	}
	
	public AllocationSlider getSelectedSlider()
	{
		return this.selectedSlider;
	}
}

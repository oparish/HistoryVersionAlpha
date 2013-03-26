package sliders;

import java.util.ArrayList;

import javax.swing.BoundedRangeModel;
import javax.swing.event.ChangeEvent;

import allocation.AllocationSlider;

import data.CitizenType;
import data.CitizenContainer;

public class SliderSet 
{
	private ArrayList<AllocationSlider> members;
	private boolean changingAllocation;
	
	public SliderSet()
	{
		this.members = new ArrayList<AllocationSlider>();
	}
	
	public ArrayList<AllocationSlider> getMembers()
	{
		return this.members;
	}
	
	public void addSlider(AllocationSlider slider)
	{
		members.add(slider);
	}
	
	public void tryUpdatingSliders(AllocationSlider destinationSlider, AllocationSlider sourceSlider, CitizenContainer container)
	{
		if (!this.changingAllocation)
			updateSliders(destinationSlider, sourceSlider, container);
	}
	
	public void correctSliders()
	{
		this.changingAllocation = true;
		for (AllocationSlider slider : members)
		{
			slider.correctSliderPosition();
		}
		this.changingAllocation = false;
	}
	
	private void updateSliders(AllocationSlider destinationSlider, AllocationSlider sourceSlider, CitizenContainer container)
	{
		this.changingAllocation = true;
		
		BoundedRangeModel destinationModel = destinationSlider.getModel();
		
		CitizenType sourceType = sourceSlider.getType();
		CitizenType destinationType = destinationSlider.getType();
		
		double destinationPosition = destinationModel.getValue();
		double max = destinationModel.getMaximum();
		double destinationRatio = destinationPosition/max;
		
		if (members.size() > 2)
		{
			for (AllocationSlider slider : members)
			{
				if (slider != destinationSlider && slider != sourceSlider)
				{
					slider.correctSliderPosition();
				}
			}
		}
		
		int sourceInt = sourceType.getNumber();
		
		int people = container.getCitizens();
		double destinationDouble = (double) people * destinationRatio;
		int destinationInt = Math.round((float) destinationDouble);
		int differenceInt;
		
		if (destinationInt > destinationType.getNumber())
		{
			differenceInt = destinationInt - destinationType.getNumber();
			
			if (differenceInt > sourceInt)
			{
				differenceInt = sourceInt;
				destinationInt = destinationType.getNumber() + differenceInt;
			}
		}
		else
			differenceInt = destinationInt - destinationType.getNumber();
		
		double newSourcePosition = (sourceInt - differenceInt) * max/people;
		
		sourceSlider.setValue(Math.round((float) newSourcePosition));

		int newSourceInt = sourceInt - differenceInt;
		
		destinationType.setNumber(destinationInt);
		sourceType.setNumber(newSourceInt);
		destinationType.setPercent((double) destinationInt/(double) people);
		sourceType.setPercent((double) newSourceInt/(double) people);
		
		this.changingAllocation = false;
	}
}

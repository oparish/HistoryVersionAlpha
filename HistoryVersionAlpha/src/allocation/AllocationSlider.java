package allocation;

import data.CitizenType;
import sliders.ListenerSlider;
import sliders.SliderId;

public class AllocationSlider extends ListenerSlider
{
	private CitizenType citizenType;
	
	AllocationSlider(SliderId sliderId, CitizenType citizenType)
	{
		super(sliderId);
		this.citizenType = citizenType;
	}
	
	public void correctSliderPosition()
	{
		double value = this.citizenType.getPercent() * this.getMaximum();
		this.setValue(Math.round((float)value));
	}
	
	public CitizenType getType()
	{
		return this.citizenType;
	}
}

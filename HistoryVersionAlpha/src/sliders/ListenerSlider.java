package sliders;

import javax.swing.JSlider;

@SuppressWarnings("serial")
public class ListenerSlider extends JSlider
{
	private SliderId id;
	
	public ListenerSlider(SliderId id)
	{
		this.id = id;
	}
	
	public SliderId getId()
	{
		return this.id;
	}
}

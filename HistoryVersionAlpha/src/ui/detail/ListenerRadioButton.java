package ui.detail;

import javax.swing.JRadioButton;

import ui.ButtonId;

@SuppressWarnings("serial")
public class ListenerRadioButton extends JRadioButton
{
	private ButtonId id;
	
	public ListenerRadioButton(String contentText, ButtonId id)
	{
		super(contentText);
		this.id = id;
	}
	
	public ButtonId getId()
	{
		return this.id;
	}
}

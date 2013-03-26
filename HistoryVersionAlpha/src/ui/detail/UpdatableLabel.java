package ui.detail;

import javax.swing.JLabel;

import data.CitizenType;

public class UpdatableLabel extends JLabel
{
	private static final int DIGITS = 4;
	CitizenType sourceType;
	
	public UpdatableLabel(CitizenType type)
	{
		this.sourceType = type;
		updateText();
	}
	
	public void updateText()
	{
		String textFromNumber = String.valueOf(this.sourceType.getNumber());	
		while(textFromNumber.length() < DIGITS)
		{
			textFromNumber = "0" + textFromNumber;
		}
		this.setText(textFromNumber);
	}
}

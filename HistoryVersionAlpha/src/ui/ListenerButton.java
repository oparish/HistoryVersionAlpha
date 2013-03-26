package ui;
import javax.swing.*;

@SuppressWarnings("serial")
public class ListenerButton extends JButton
{
	private ButtonId id;
	
	public ListenerButton(String contentText, ButtonId id)
	{
		this.id = id;
		setText(contentText);
	}
	
	public ButtonId getId()
	{
		return this.id;
	}
}

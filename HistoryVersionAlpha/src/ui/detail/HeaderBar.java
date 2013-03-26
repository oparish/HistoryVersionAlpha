package ui.detail;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import ui.ButtonId;
import ui.ListenerButton;

import main.MainFrame;

@SuppressWarnings("serial")
public class HeaderBar extends JPanel
{
	private static final String BACK = "Back";
	private static final String LEFT = "<";
	private static final String RIGHT = ">";
	
	HeaderBar()
	{
		setupLeftProvinceButton();
		setupBackButton();
		setupRightProvinceButton();
	}
	
	private void setupBackButton()
	{
		ListenerButton logButton = new ListenerButton(BACK, ButtonId.BACK_ID);
		add(logButton, setupButtonConstraints(1));
		logButton.addActionListener(MainFrame.getInstance());
	}
	
	private void setupLeftProvinceButton()
	{
		ListenerButton leftButton = new ListenerButton(LEFT, ButtonId.LEFT_ID);
		add(leftButton, setupButtonConstraints(0));
		leftButton.addActionListener(DetailScreen.getInstance());
	}
	
	private void setupRightProvinceButton()
	{
		ListenerButton rightButton = new ListenerButton(RIGHT, ButtonId.RIGHT_ID);
		add(rightButton, setupButtonConstraints(2));
		rightButton.addActionListener(DetailScreen.getInstance());
	}
	
	private GridBagConstraints setupButtonConstraints(int x)
	{
		GridBagConstraints buttonConstraints = new GridBagConstraints();
		buttonConstraints.fill = GridBagConstraints.VERTICAL;
		buttonConstraints.gridx = x;
		
		return buttonConstraints;
	}
}

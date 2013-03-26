package ui.main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ui.ButtonId;
import ui.ListenerButton;



public class ButtonPane extends JPanel
{
	private static final String VIEWLOG = "View Log";
	private static final String OPTIONS = "Options";
	private static final String ENDTURN = "End Turn";
	private static final String QUIT = "Quit";
	
	public ButtonPane()
	{
		setLayout(new GridLayout(2,2));
		
		setupLogButton();
		setupOptionButton();
		setupTurnButton();
		setupQuitButton();
		
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void setupLogButton()
	{
		ListenerButton logButton = new ListenerButton(VIEWLOG, ButtonId.LOG_ID);
		add(logButton);
		logButton.addActionListener(MainScreen.getInstance());
	}
	
	private void setupOptionButton()
	{
		ListenerButton optionButton = new ListenerButton(OPTIONS, ButtonId.OPTION_ID);
		add(optionButton);
		optionButton.addActionListener(MainScreen.getInstance());
	}
	
	private void setupTurnButton()
	{
		ListenerButton turnButton = new ListenerButton(ENDTURN, ButtonId.TURN_ID);
		add(turnButton);
		turnButton.addActionListener(MainScreen.getInstance());
	}
	
	private void setupQuitButton()
	{
		ListenerButton quitButton = new ListenerButton(QUIT, ButtonId.QUIT_ID);;
		add(quitButton);
		quitButton.addActionListener(MainScreen.getInstance());
	}
}

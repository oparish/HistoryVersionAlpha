package main;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lists.ListenerList;


import ui.ListenerButton;
import ui.ProvincePane;
import ui.detail.DetailScreen;
import ui.main.MainScreen;

import data.Province;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener
{
	private static MainFrame instance;
	private MainScreen mainScreen;
	private DetailScreen detailScreen;
	private JPanel currentPanel;
	
	MainFrame()
	{
		MainFrame.instance = this;
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		this.mainScreen = new MainScreen();
		this.currentPanel = this.mainScreen;
		add(mainScreen);
		setVisible(true);
	}
	
	private void changeToDetailsScreenFromMainScreen(ListenerButton button)
	{	
		ProvincePane pane = (ProvincePane) button.getParent();
		Province province = pane.getProvince();
		changeToDetailsScreen(province);
	}
	
	public void changeToDetailsScreen(Province province)
	{
		remove(currentPanel);
		String ownerName = province.getOwner().getName();
		if (this.detailScreen == null)
			this.detailScreen = new DetailScreen(province, ownerName);
		else
			this.detailScreen.setProvince(province);
		this.currentPanel = this.detailScreen;
		add(currentPanel);
		validate();
		repaint();
	}
	
	private void changeToMainScreen()
	{
		remove(currentPanel);
		this.currentPanel = this.mainScreen;
		add(currentPanel);
		validate();
		repaint();
	}
	
	public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
               new MainFrame();
            }
        });
	}
	
	public static MainFrame getInstance()
	{
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source =  e.getSource();
		
		if (source.getClass() == ListenerButton.class)
		{
			ListenerButton button = (ListenerButton) source;
			switch(button.getId())
			{
				case DETAILS_ID:
					changeToDetailsScreenFromMainScreen(button);
					break;
				case BACK_ID:
					changeToMainScreen();
					break;
			}
		}
	}
}

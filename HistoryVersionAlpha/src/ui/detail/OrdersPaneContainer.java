package ui.detail;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import leaderData.Leader;
import lists.OrderTypeList;
import orderData.OrderType;
import ui.ButtonId;
import ui.ListenerButton;
import ui.main.MainScreen;

public class OrdersPaneContainer extends JPanel implements ActionListener
{
	private final static String BACKTEXT = "Back";
	private final static String ADDTEXT = "Add";
	private OrdersPane ordersPane;
	private JScrollPane scrollPane;
	private OrderTypeList orderTypeList;
	
	public OrdersPaneContainer(Leader leader)
	{
		this.setLayout(new GridLayout(0, 1));
		this.ordersPane = new OrdersPane(leader);
		this.scrollPane = new JScrollPane(this.ordersPane, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(this.scrollPane);
		this.add(getControlPanel());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private GridBagConstraints setupComponentConstraints(int x, int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = x;
		componentConstraints.gridy = y;
		componentConstraints.weightx = 0;
		componentConstraints.weighty = 0;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;
		return componentConstraints;
	}
	
	private GridBagConstraints setupListConstraints(int x, int y)
	{
		GridBagConstraints paneConstraints = new GridBagConstraints();
		paneConstraints.gridx = x;
		paneConstraints.gridy = y;
		paneConstraints.weightx = 1;
		paneConstraints.weighty = 1;
		paneConstraints.gridheight = 2;
		paneConstraints.fill = GridBagConstraints.BOTH;
		return paneConstraints;
	}
	
	private JPanel getControlPanel()
	{
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridBagLayout());
		setOrderBox();
		controlPanel.add(getAddButton(), setupComponentConstraints(0, 0));
		controlPanel.add(this.orderTypeList, setupListConstraints(1, 0));
		controlPanel.add(getBackButton(), setupComponentConstraints(0, 1));
		return controlPanel;
	}
	
	private JButton getBackButton()
	{
		ListenerButton backButton = new ListenerButton(BACKTEXT, ButtonId.BACK_ID);
		backButton.addActionListener(MainScreen.getInstance());
		return backButton;
	}
	
	private JButton getAddButton()
	{
		ListenerButton addButton = new ListenerButton(ADDTEXT, ButtonId.ADD_ID);
		addButton.addActionListener(this);
		return addButton;
	}
	
	private void setOrderBox()
	{
		this.orderTypeList = new OrderTypeList();
		this.orderTypeList.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		OrderType selectedType = (OrderType) this.orderTypeList.getSelectedValue();
		if (selectedType != null)
		{
			this.ordersPane.addNewOrder(selectedType);
			this.scrollPane.validate();
		}
	}
	
	public void changeLeader(Leader leader)
	{
		this.ordersPane.changeLeader(leader);
		this.validate();
		this.repaint();
	}
}

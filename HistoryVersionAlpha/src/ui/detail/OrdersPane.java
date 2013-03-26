package ui.detail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

import data.Province;

import orderData.Order;
import orderData.OrderElement;
import orderData.OrderElement.OrderElementType;
import orderData.OrderType;
import orderData.ThreeElementOrder;
import orderData.TwoElementOrder;
import orderEntries.OrderEntry;
import orderEntries.ThreeElementOrderEntry;
import orderEntries.TwoElementOrderEntry;
import ui.main.MainScreen;


import leaderData.Leader;

@SuppressWarnings("serial")
public class OrdersPane extends JPanel
{
	private final Leader leader;
	private final JLabel fillerLabel;
	private final JLabel noOrdersLabel = new JLabel("No Orders");
	private int counter = 0;
	public OrdersPane(Leader leader)
	{
		this.leader = leader;
		this.fillerLabel = new JLabel();
		this.setLayout(new GridBagLayout());
		this.updateLeader(leader);
	}
	
	private void updateLeader(Leader leader)
	{
		if (leader != null)
		{
			ArrayList<Order> orders = leader.getOrders();
			for  (Order order : orders)
			{
				this.addOrderEntry(order);
			}
		}
		else
		{
			this.addNoOrders();
		}
	}
	
	private void clearOrders()
	{
		this.removeAll();
	}
	
	private void addNoOrders()
	{
		this.add(this.noOrdersLabel, getNoOrdersConstraints());
	}
	
	private void addFiller()
	{
		this.add(this.fillerLabel, getFillerConstraints());
	}
	
	private void addOrderEntry(Order order)
	{
		OrderEntry orderEntry;
		if (order instanceof ThreeElementOrder)
		{
			orderEntry = new ThreeElementOrderEntry((ThreeElementOrder) order);
		}
		else if (order instanceof TwoElementOrder)
		{
			orderEntry = new TwoElementOrderEntry((TwoElementOrder) order);
		}
		else
		{
			orderEntry = new OrderEntry(order);
		}
		
		this.add(orderEntry, getConstraints());
	}
	
	private GridBagConstraints getNoOrdersConstraints()
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = 0;
		componentConstraints.gridy = 0;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;
		return componentConstraints;
	}
	
	private GridBagConstraints getConstraints()
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = 0;
		componentConstraints.gridy = counter;
		counter++;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 0;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;
		return componentConstraints;
	}
	
	private GridBagConstraints getFillerConstraints()
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = 0;
		componentConstraints.gridy = counter + 1;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;
		return componentConstraints;
	}
	
	public void addNewOrder(OrderType type)
	{
		OrderElementType[] elementTypes = type.getElementTypes();
		Order newOrder;
		Province defaultProvince = MainScreen.getInstance().getProvinceList().get(0);
		if (elementTypes.length == 1)
		{
			newOrder = new TwoElementOrder(defaultProvince, elementTypes[0].getDefaultElement());
		}
		else if (elementTypes.length == 2)
		{
			newOrder = new ThreeElementOrder(defaultProvince, elementTypes[0].getDefaultElement(), elementTypes[1].getDefaultElement());
		}
		else
		{
			newOrder = new Order(defaultProvince);
		}
		
		ArrayList<Order> orders = this.leader.getOrders();
		int size = orders.size();
		orders.add(newOrder);
		if (size == 0)
		{
			this.remove(this.noOrdersLabel);
		}
		addOrderEntry(newOrder);
		this.remove(this.fillerLabel);
		this.addFiller();
		this.validate();
		this.repaint();
	}
	
	public void changeLeader(Leader leader)
	{
		clearOrders();
		updateLeader(leader);
		this.validate();
		this.repaint();
	}
}

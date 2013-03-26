package orderEntries;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import orderData.Order;

@SuppressWarnings("serial")
public class OrderEntry extends JPanel implements ActionListener
{
	private JLabel locationLabel;
	protected final Order order;
	protected JComboBox firstElementBox;
	protected JComboBox secondElementBox;
	
	public OrderEntry(Order order)
	{
		this.order = order;
		this.setLayout(new GridBagLayout());
		this.addLocationLabel();
		this.addChangeButton();
		this.addElementBoxes();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	protected void addElementBoxes()
	{
		this.firstElementBox = new JComboBox();
		this.add(this.firstElementBox, this.getConstraints(0, 1));
		this.secondElementBox = new JComboBox();
		this.add(this.secondElementBox, this.getConstraints(1, 1));
	}
	
	private void addLocationLabel()
	{
		String locationName = order.getProvince().getName();
		this.locationLabel = new JLabel(locationName);
		this.add(locationLabel, getConstraints(0, 0));
	}
	
	private void addChangeButton()
	{
		JButton changeButton = new JButton("Change");
		changeButton.addActionListener(this);
		this.add(changeButton, getConstraints(1,0));
	}
	
	protected GridBagConstraints getConstraints(int x, int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = x;
		componentConstraints.gridy = y;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;
		return componentConstraints;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}
}

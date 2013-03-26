package orderEntries;

import javax.swing.JComboBox;

import orderData.Order;
import orderData.OrderElement;
import orderData.TwoElementOrder;
import orderData.OrderElement.OrderElementType;

@SuppressWarnings("serial")
public class TwoElementOrderEntry extends OrderEntry
{	
	public TwoElementOrderEntry(TwoElementOrder order)
	{
		super(order);
	}

	protected void addFirstElementBox()
	{
		OrderElement orderElement = ((TwoElementOrder) order).getFirstElement();
		OrderElementType orderElementType = OrderElementType.typeFromClass(orderElement.getClass());
		OrderElement[] values = orderElementType.getElementCollection();
		this.firstElementBox = new JComboBox(values);
		this.firstElementBox.setSelectedItem(orderElementType.getDefaultElement());
		this.add(this.firstElementBox, this.getConstraints(0, 1));
	}
	
	@Override
	protected void addElementBoxes()
	{
		addFirstElementBox();
		this.secondElementBox = new JComboBox();
		this.add(this.secondElementBox, this.getConstraints(1, 1));
	}
}

package orderEntries;

import javax.swing.JComboBox;

import orderData.Order;
import orderData.OrderElement;
import orderData.ThreeElementOrder;
import orderData.TwoElementOrder;
import orderData.OrderElement.OrderElementType;

public class ThreeElementOrderEntry extends TwoElementOrderEntry
{
	public ThreeElementOrderEntry(ThreeElementOrder order)
	{
		super(order);
	}

	private void addSecondElementBox()
	{
		OrderElement orderElement = ((ThreeElementOrder) order).getSecondElement();
		OrderElementType orderElementType = OrderElementType.typeFromClass(orderElement.getClass());
		OrderElement[] values = orderElementType.getElementCollection();
		this.secondElementBox = new JComboBox(values);
		this.secondElementBox.setSelectedItem(orderElementType.getDefaultElement());
		this.add(this.secondElementBox, this.getConstraints(0, 2));
	}
	
	@Override
	protected void addElementBoxes()
	{
		super.addFirstElementBox();
		addSecondElementBox();
	}
}

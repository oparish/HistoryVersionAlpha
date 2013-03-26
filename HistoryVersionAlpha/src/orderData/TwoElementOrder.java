package orderData;

import java.lang.reflect.Constructor;

import data.Province;

public class TwoElementOrder extends Order
{
	private OrderElement firstElement;
	
	public TwoElementOrder(Province province, OrderElement firstElement)
	{
		super(province);
		this.firstElement = firstElement;
	}

	public void setFirstElement(OrderElement firstElement) {
		this.firstElement = firstElement;
	}

	public OrderElement getFirstElement() {
		return firstElement;
	}
}

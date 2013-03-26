package orderData;

import data.Province;

public class ThreeElementOrder extends TwoElementOrder
{
	private OrderElement secondElement;
	
	public ThreeElementOrder(Province province, OrderElement firstElement, OrderElement secondElement)
	{
		super(province, firstElement);
		this.secondElement = secondElement;
	}

	public void setSecondElement(OrderElement secondElement) {
		this.secondElement = secondElement;
	}

	public OrderElement getSecondElement() {
		return secondElement;
	}
	
	

}

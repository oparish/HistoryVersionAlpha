package orderData;

import data.Province;

public class Order
{
	private Province province;
	
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Order(Province province)
	{
		super();
		this.province = province;
	}
}

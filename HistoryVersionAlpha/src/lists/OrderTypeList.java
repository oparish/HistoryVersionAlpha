package lists;

import orderData.OrderType;
import ui.ListId;

public class OrderTypeList extends ListenerList
{
	public OrderTypeList()
	{
		super(OrderType.values());
		this.setCellRenderer(OrderTypeCellRenderer.getInstance());
		this.id = ListId.SELECT_ORDERTYPE_ID;
	}
}

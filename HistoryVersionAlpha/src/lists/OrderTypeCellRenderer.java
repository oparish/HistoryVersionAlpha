package lists;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import orderData.OrderType;

import leaderData.Leader;

public class OrderTypeCellRenderer extends DefaultListCellRenderer 
{
	private static OrderTypeCellRenderer orderTypeCellRenderer = new OrderTypeCellRenderer();
	public static OrderTypeCellRenderer getInstance()
	{
		return OrderTypeCellRenderer.orderTypeCellRenderer;
	}
	
	public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
	{
		OrderType orderType = (OrderType) value;
		return super.getListCellRendererComponent(list, orderType.getName(), index, isSelected, cellHasFocus);
	}
}

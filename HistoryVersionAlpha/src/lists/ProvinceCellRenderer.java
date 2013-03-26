package lists;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import data.Province;

public class ProvinceCellRenderer extends DefaultListCellRenderer
{
	private static ProvinceCellRenderer provinceBoxRenderer = new ProvinceCellRenderer();
	public static ProvinceCellRenderer getInstance()
	{
		return ProvinceCellRenderer.provinceBoxRenderer;
	}
	
	public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
	{
		Province province = (Province) value;
		return super.getListCellRendererComponent(list, province.getName(), index, isSelected, cellHasFocus);
	}
}

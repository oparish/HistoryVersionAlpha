package ui.detail;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import data.NamedEnum;


public class EnumRenderer extends DefaultListCellRenderer implements ListCellRenderer
{	
	@Override
	public Component getListCellRendererComponent(JList list, Object object,
			int index, boolean isSelected, boolean hasFocus)
	{
		String name;
		if (object instanceof NamedEnum)
		{
			name = ((NamedEnum) object).getName();
		}
		else
		{
			name = object.toString();
		}
        return super.getListCellRendererComponent(list, name, index, isSelected, hasFocus);
	}
}

package lists;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import data.ProjectType;

import orderData.OrderType;

public class ProjectTypeCellRenderer extends DefaultListCellRenderer
{
	private static ProjectTypeCellRenderer projectTypeCellRenderer = new ProjectTypeCellRenderer();
	public static ProjectTypeCellRenderer getInstance()
	{
		return ProjectTypeCellRenderer.projectTypeCellRenderer;
	}
	
	public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
	{
		ProjectType projectType = (ProjectType) value;
		return super.getListCellRendererComponent(list, projectType.getName(), index, isSelected, cellHasFocus);
	}
}

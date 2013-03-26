package lists;

import data.ProjectType;
import orderData.OrderType;
import ui.ListId;

public class ProjectTypeList extends ListenerList
{
	public ProjectTypeList()
	{
		super(ProjectType.values());
		this.setCellRenderer(ProjectTypeCellRenderer.getInstance());
		this.id = ListId.SELECT_PROJECTTYPE_ID;
	}
}

package ui.detail;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.ProjectType;
import data.Province;

public class QueuePane extends JPanel
{
	private Province province;
	private JList items;
	private final JLabel noOrdersLabel = new JLabel("No Orders");
	
	QueuePane(Province province)
	{
		super();
		this.province = province;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setupQueueItems();
	}
	
	private void setupQueueItems()
	{
		if (this.province.getProjectQueue().size() == 0)
		{
			this.add(this.noOrdersLabel);
		}
		else
		{
			this.items = new JList();
			DefaultListModel model = new DefaultListModel();
			this.items.setModel(model);	
			for (ProjectType type : this.province.getProjectQueue())
			{
				model.addElement(type);
			}
			this.add(new JScrollPane(this.items));
		}
	}
	
	public void changeProject(ProjectType projectType)
	{
		if (this.items != null)
		{
			int index = this.items.getSelectedIndex();
			this.province.getProjectQueue().set(index, projectType);
			this.changeProjectInModel(projectType, index);
		}	
	}
	
	private void changeProjectInModel(ProjectType newType, int index)
	{
		DefaultListModel listModel = (DefaultListModel) this.items.getModel();
		listModel.add(index, newType);
	}
	
	private void addProjectToModel(ProjectType newType)
	{
		DefaultListModel listModel = (DefaultListModel) this.items.getModel();
		listModel.addElement(newType);
	}
	
	public void addProject(ProjectType projectType)
	{
		int newIndex = this.province.getProjectQueue().size();
		this.province.addProjectToQueue(projectType, newIndex);
		if (this.items != null)
		{
			this.addProjectToModel(projectType);
		}
		else
		{
			this.remove(this.noOrdersLabel);
			this.setupQueueItems();
		}
		this.validate();
		this.repaint();
	}
	
	public void bumpProject()
	{
		if (this.items != null)
		{
			int index = this.items.getSelectedIndex();
			ArrayList<ProjectType> queue = this.province.getProjectQueue();
			ArrayList<ProjectType> newQueue = new ArrayList<ProjectType>();
			newQueue.add(queue.get(index));
			for (int i = 0; i < queue.size(); i++)
			{
				if (i != index + 1)
				{
					newQueue.add(queue.get(i));
				}
			}
			this.province.setProjectQueue(newQueue);
		}	
	}
}

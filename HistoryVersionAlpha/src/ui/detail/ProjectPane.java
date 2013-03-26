package ui.detail;

import static ui.ButtonId.ADD_PROJECT_ID;
import static ui.ButtonId.BUMP_PROJECT_ID;
import static ui.ButtonId.CHANGE_PROJECT_ID;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import orderData.Order;
import orderEntries.OrderEntry;
import ui.ButtonId;
import ui.ListenerButton;

import leaderData.Leader;
import lists.ProjectTypeList;

import data.ProjectType;
import data.Province;

public class ProjectPane extends JPanel implements ActionListener
{
	private static final String ADDTOQUEUE = "Add to Queue";
	private static final String CHANGECURRENTPROJECT = "Change Current Project";
	private static final String BUMPCURRENTPROJECT = "Bump Current Project";
	
	private Province province;
	private final JLabel fillerLabel;
	private final JLabel noQueueLabel = new JLabel("No Queue");
	private ProjectTypeList projectTypeList;
	private QueuePane queuePane;
	private ListenerButton addButton;
	private ListenerButton changeCurrentButton;
	private ListenerButton bumpButton;
	private JLabel currentProject;
	
	ProjectPane(Province province)
	{
		super();
		this.setLayout(new GridBagLayout());
		this.province = province;
		this.fillerLabel = new JLabel();
		this.currentProject = new JLabel();
		
		setupProjectTypeList();
		setupQueuePane();
		updateQueuePane();
		setupChangeCurrentButton();
		setupBumpButton();
		setupAddButton();
	}
	
	private void setupProjectTypeList()
	{
		this.projectTypeList = new ProjectTypeList();
		this.add(this.projectTypeList, getComponentConstraints(0, 0));
	}
	
	private void setupQueuePane()
	{
		this.queuePane = new QueuePane(province);
		this.add(this.queuePane, getQueuePaneConstraints(1, 0));
	}
	
	private void setupChangeCurrentButton()
	{
		this.changeCurrentButton = new ListenerButton(CHANGECURRENTPROJECT, CHANGE_PROJECT_ID);
		this.changeCurrentButton.addActionListener(this);
		this.add(this.changeCurrentButton, getComponentConstraints(2, 1));
	}
	
	private void setupBumpButton()
	{
		this.bumpButton = new ListenerButton(BUMPCURRENTPROJECT, BUMP_PROJECT_ID);
		this.bumpButton.addActionListener(this);
		this.add(this.bumpButton, getComponentConstraints(1, 1));
	}
	
	private void setupAddButton()
	{
		this.addButton = new ListenerButton(ADDTOQUEUE, ADD_PROJECT_ID);
		this.addButton.addActionListener(this);
		this.add(this.addButton, getComponentConstraints(0, 1));
	}
	
	public void setProvince(Province province)
	{
		this.province = province;
		this.removeAll();
		updateQueuePane();

	}
	
	private void updateQueuePane()
	{
		ArrayList<ProjectType> queue = province.getProjectQueue();
		if (queue != null)
		{
			for  (ProjectType project : queue)
			{
				this.addProjectToQueue(project);
			}
		}
		else
		{
			this.addNoQueue();
		}
	}
	
	private void addProjectToQueue(ProjectType projectType)
	{
		this.queuePane.addProject(projectType);
	}
	
	private void addNoQueue()
	{
		this.add(this.noQueueLabel, getNoQueueConstraints());
	}
	
	private GridBagConstraints getNoQueueConstraints()
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = 0;
		componentConstraints.gridy = 0;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;
		return componentConstraints;
	}
	
	private GridBagConstraints getComponentConstraints(int x, int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = x;
		componentConstraints.gridy = y;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.VERTICAL;
		return componentConstraints;
	}
	
	private GridBagConstraints getQueuePaneConstraints(int x, int y)
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = x;
		componentConstraints.gridy = y;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 1;
		componentConstraints.fill = GridBagConstraints.BOTH;
		return componentConstraints;
	}
	
	private void addProject()
	{
		ProjectType selectedType = (ProjectType) this.projectTypeList.getSelectedValue();
		if (selectedType != null)
			this.addProjectToQueue(selectedType);
	}
	
	private void changeProject()
	{
		ProjectType selectedType = (ProjectType) this.projectTypeList.getSelectedValue();
		if (selectedType != null)
			this.queuePane.changeProject(selectedType);
	}
	
	private void bumpProject()
	{
		this.queuePane.bumpProject();
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		ListenerButton sourceButton = (ListenerButton) ae.getSource();
		switch(sourceButton.getId())
		{
			case ADD_PROJECT_ID:
				addProject();
				break;
			case CHANGE_PROJECT_ID:
				changeProject();
				break;
			case BUMP_PROJECT_ID:
				bumpProject();
				break;
			default:		
		}
	}
}

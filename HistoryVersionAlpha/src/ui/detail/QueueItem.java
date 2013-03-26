package ui.detail;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.ProjectType;

public class QueueItem extends JPanel
{
	private static final String CHANGE = "Change";
	private static final String REMOVE = "Remove";
	private ProjectType projectType;
	private JLabel projectLabel;
	
	QueueItem(ProjectType projectType)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setupProjectLabel();
		setupChangeButton();
		setupRemoveButton();
		this.setProjectType(projectType);
	}
	
	private void setupProjectLabel()
	{
		this.projectLabel = new JLabel();
		this.add(this.projectLabel);
	}
	
	private void setupChangeButton()
	{
		this.add(new JLabel(CHANGE));
	}
	
	private void setupRemoveButton()
	{
		this.add(new JLabel(REMOVE));
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
		this.projectLabel.setText(projectType.getName());
	}

	public ProjectType getProjectType() {
		return projectType;
	}
}

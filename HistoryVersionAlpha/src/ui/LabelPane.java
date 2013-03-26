package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public abstract class LabelPane extends JPanel
{
	protected static final String BLANK = "-";
	
	boolean onLeftLabel = true;
	HashMap<String, JLabel> labelData;
	JPanel columnsPanel;
	
	LabelPane(String[] names)
	{
		this();
		setupLabelData(names);
		setupLabels();
	}
	
	LabelPane()
	{
		setLayout(new GridBagLayout());
		setupColumnsPanel();
	}
	
	public void clearPane()
	{
		this.columnsPanel.removeAll();
		this.onLeftLabel = true;
	}
	
	private void setupColumnsPanel()
	{
		this.columnsPanel = new JPanel();
		this.columnsPanel.setLayout(new GridLayout(0,2));
		this.add(this.columnsPanel, setupColumnsPanelConstraints());
	}
	
	private GridBagConstraints setupColumnsPanelConstraints()
	{
		GridBagConstraints columnPanelConstraints = new GridBagConstraints();
		columnPanelConstraints.anchor = GridBagConstraints.NORTH;
		columnPanelConstraints.gridx = 0;
		columnPanelConstraints.gridy = 1;
		return columnPanelConstraints;
	}
	
	protected void setupLabels()
	{
		for (String name : labelData.keySet())
		{
			setupLabelPair(name, labelData.get(name));
		}
	}
	
	protected void setupLabelPair(String name, JLabel label)
	{
		setupLeftLabel(name);
		setupRightLabel(label);
	}
	
	private void setupLabelData(String[] names)
	{
		labelData = new HashMap<String, JLabel>();
		addLabelData(names);
	}
	
	protected void addLabelData(String[] names)
	{
		for (String name : names)
		{
			labelData.put(name, new JLabel(BLANK));
		}
	}
	
	protected void setupTitleLabel(String text)
	{
		JLabel label = new JLabel(text);
		GridBagConstraints titleConstraints = setupTitleConstraints();
		add(label, titleConstraints);
	}
	
	private GridBagConstraints setupTitleConstraints()
	{
		GridBagConstraints titleConstraints = new GridBagConstraints();
		titleConstraints.anchor = GridBagConstraints.NORTH;
		titleConstraints.gridx = 0;
		titleConstraints.gridy = 0;
		return titleConstraints;
	}
	
	protected void setupLeftLabel(String text)
	{
		if (!this.onLeftLabel)
			setupGap();
		JLabel label = new JLabel(text);
		this.columnsPanel.add(label);
		this.onLeftLabel = !this.onLeftLabel;
	}
	
	protected void setupGap()
	{
		JLabel label = new JLabel("");
		this.columnsPanel.add(label);
		this.onLeftLabel = !this.onLeftLabel;
	}
	
	protected void setupLeftLabel(int text)
	{
		setupLeftLabel(String.valueOf(text));
	}
	
	protected void setupRightLabel(JLabel label)
	{
		if (this.onLeftLabel)
			setupGap();
		this.columnsPanel.add(label);
		this.onLeftLabel = !this.onLeftLabel;
	}
	
	protected void setLabelValue(String key, String value)
	{
		if (labelData.containsKey(key))
		{
			JLabel label = labelData.get(key);
			label.setText(value);
			if (this.isAncestorOf(label))
			{
				label.repaint();
			}
		
		}
		else
		{
			System.out.println(key + " not recognised as a label name.");
		}
	}
	
	protected void setBlankValue(String key)
	{
		setLabelValue(key, BLANK);
	}
	
	protected void setBlankValues(String[] keys)
	{
		for (String key : keys)
		{
			setBlankValue(key);
		}
	}
	
	protected void setLabelValue(String key, int value)
	{
		setLabelValue(key, String.valueOf(value));
	}
}

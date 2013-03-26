package lists;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import data.Province;

import ui.ListId;
import ui.main.MainScreen;

public abstract class ListenerList extends JList
{
	protected ListId id;
	
	public ListId getId()
	{
		return this.id;
	}

	public ListenerList(Object[] objects)
	{
		super(objects);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setVisibleRowCount(-1);
		this.addListSelectionListener(MainScreen.getInstance());
	}
}

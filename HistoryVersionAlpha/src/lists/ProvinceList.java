package lists;

import java.awt.Component;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import ui.ListId;
import ui.main.MainScreen;

import data.Province;

public class ProvinceList extends ListenerList
{
	public ProvinceList(List<Province> provinces)
	{
		super(provinces.toArray());
		this.setCellRenderer(ProvinceCellRenderer.getInstance());
		this.id = ListId.SELECT_PROVINCE_ID;
	}
}





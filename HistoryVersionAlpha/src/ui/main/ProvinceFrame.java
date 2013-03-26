package ui.main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lists.ProvinceList;


import data.Province;

import ui.ListenerButton;

public class ProvinceFrame extends JPanel
{
	private ProvinceList provinceList;
	private DetailedProvinceNumbers detailedProvinceNumbers;
	
	public ProvinceFrame()
	{
		this.setLayout(new GridLayout(0, 1));
		this.detailedProvinceNumbers = new DetailedProvinceNumbers();
		this.add(new JScrollPane(setupProvinceList()));
		this.add(this.detailedProvinceNumbers);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private JList setupProvinceList()
	{
		this.provinceList = new ProvinceList(MainScreen.getInstance().getProvinceList());
		this.provinceList.addMouseListener(MainScreen.getInstance());
		return this.provinceList;
	}
	
	public void changeDetailedProvinceNumbers(Province selectedProvince)
	{
		detailedProvinceNumbers.setProvince(selectedProvince);
	}
}

package ui.detail;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ui.ProvinceNumbers;

import data.Province;

public class ProvinceStructurePanel extends JPanel
{
	private ProvinceNumbers provinceNumbers;
	private ProjectPane projectPane;
	
	ProvinceStructurePanel(Province province)
	{
		super();
		this.setLayout(new GridLayout(0, 1));
		this.add(new ProvinceNumbers(province));
		this.add(new ProjectPane(province));
	}
	
	public void updateProvince(Province province)
	{
		this.provinceNumbers.setProvince(province);
		this.projectPane.setProvince(province);
	}
}

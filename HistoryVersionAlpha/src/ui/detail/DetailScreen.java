package ui.detail;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sliders.ListenerSlider;
import ui.ButtonId;
import ui.ListenerButton;
import ui.ProvinceJourneysDisplay;
import ui.ProvinceLeaders;
import ui.ProvinceNumbers;
import ui.main.MainScreen;

import allocation.AllocationSlider;
import allocation.LeaderAllocation;
import allocation.ProvinceAllocation;

import leaderData.Leader;
import lists.LeaderList;
import main.MainFrame;
import map.MapButton;

import data.CitizenType;
import data.Province;

@SuppressWarnings("serial")
public class DetailScreen extends JPanel implements ActionListener, ChangeListener, ListSelectionListener
{
	private Leader currentLeader;
	private Province province;
	private String ownerName;
	private static DetailScreen instance;
	ProvinceStructurePanel provinceStructurePanel;
	ProvinceJourneysDisplay provinceJourneysDisplay;
	LeaderList leaderList;
	private ProvinceAllocation provinceAllocation;
	private ProvinceLeaders provinceLeaders;
	private OrdersPaneContainer ordersPaneContainer;
	private FooterBar footerBar;
	private boolean movingTransferSlider;
	
	public DetailScreen(Province newProvince, String newOwnerName)
	{
		DetailScreen.instance = this;
		province = newProvince;
		ownerName = newOwnerName;

		ArrayList<Leader> provinceLeaderList = province.getProvinceLeaders();
		if (provinceLeaderList.size() != 0)
			this.currentLeader = provinceLeaderList.get(0);
		else
			this.currentLeader = null;
		
		setLayout(new GridBagLayout());

		HeaderBar headerBar = new HeaderBar();
		add(headerBar, setupHeaderConstraints());
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridLayout(2,3));
		
		this.provinceStructurePanel = new ProvinceStructurePanel(province);
		innerPanel.add(provinceStructurePanel);
		
		this.provinceJourneysDisplay = new ProvinceJourneysDisplay(province);
		innerPanel.add(provinceJourneysDisplay);
		
		LeaderList leaderList = new LeaderList(province.getProvinceLeaders());
		innerPanel.add(leaderList);
		leaderList.addListSelectionListener(this);
		leaderList.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.provinceAllocation = new ProvinceAllocation(province);
		innerPanel.add(provinceAllocation);
		
		this.provinceLeaders = new ProvinceLeaders(this.currentLeader);
		innerPanel.add(provinceLeaders);
		
		this.ordersPaneContainer = new OrdersPaneContainer(this.currentLeader);
		innerPanel.add(this.ordersPaneContainer);
		
		this.add(innerPanel, setupInnerPanelConstraints());
		
		if (this.province.getProvinceLeaders().size() != 0)
		{
			this.footerBar = new FooterBar(true);
			add(footerBar, setupFooterConstraints());
			this.movingTransferSlider = false;
			updateTransfer();
		}
		else
		{
			this.footerBar = new FooterBar(false);
			add(footerBar, setupFooterConstraints());
		}
	}
	
	private void updateDetailsWithProvince()
	{
		ArrayList<Leader> provinceLeaderList = province.getProvinceLeaders();
		if (provinceLeaderList.size() != 0)
			this.currentLeader = provinceLeaderList.get(0);
		else
			this.currentLeader = null;
		
		this.provinceStructurePanel.updateProvince(this.province);
		this.provinceJourneysDisplay.updateProvince(this.province);
		this.leaderList.updateLeaders(provinceLeaderList);
		this.provinceAllocation.
		
	}
	
	private GridBagConstraints setupHeaderConstraints()
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = 0;
		componentConstraints.gridy = 0;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 0;
		componentConstraints.gridwidth = 2;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;
		return componentConstraints;
	}
	
	private GridBagConstraints setupInnerPanelConstraints()
	{
		GridBagConstraints innerPanelConstraints = new GridBagConstraints();
		innerPanelConstraints.gridx = 0;
		innerPanelConstraints.gridy = 3;
		innerPanelConstraints.weightx = 1;
		innerPanelConstraints.weighty = 1;
		innerPanelConstraints.fill = GridBagConstraints.BOTH;
		return innerPanelConstraints;
	}
	
	private GridBagConstraints setupFooterConstraints()
	{
		GridBagConstraints componentConstraints = new GridBagConstraints();
		componentConstraints.gridx = 0;
		componentConstraints.gridy = 4;
		componentConstraints.weightx = 1;
		componentConstraints.weighty = 0;
		componentConstraints.gridwidth = 2;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;
		return componentConstraints;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source =  e.getSource();
		
		if (source.getClass() == ListenerButton.class)
			pressedListenerButton(source);
		else if (source.getClass() == ListenerRadioButton.class)
			pressedListenerRadioButton(source);
	}
	
	private void pressedListenerButton(Object source)
	{
		ListenerButton button = (ListenerButton) source;
		ButtonId id = button.getId();
		
		switch(id)
		{
			case LEFT_ID:
				pressedLeftButton();
				break;
			case RIGHT_ID:
				pressedRightButton();
				break;
		}
	}
	
	private void pressedListenerRadioButton(Object source)
	{
		ListenerRadioButton button = (ListenerRadioButton) source;
		ButtonId id = button.getId();
		
		switch(id)
		{
			case MOVE_FARMERS_ID:
			case MOVE_BUILDERS_ID:
			case MOVE_GATHERERS_ID:
				provinceAllocation.pressedMoveButton(id);
				break;
		}
	}
	
	private void pressedLeftButton()
	{
		List<Province> provinceList = MainScreen.getInstance().getProvinceList();
		int provinceIndex = provinceList.indexOf(this.province);
		provinceIndex--;
		
		if (provinceIndex == -1)
			provinceIndex = provinceList.size() - 1;
		
		this.setProvince(provinceList.get(provinceIndex));
	}
	
	public void setProvince(Province province)
	{
		this.province = province;
		this.updateDetailsWithProvince();
	}
	
	private void pressedRightButton()
	{
		List<Province> provinceList = MainScreen.getInstance().getProvinceList();
		int provinceIndex = provinceList.indexOf(this.province);
		provinceIndex++;
		
		if (provinceIndex == provinceList.size())
			provinceIndex = 0;
		
		this.setProvince(provinceList.get(provinceIndex));
	}
	
	public static DetailScreen getInstance()
	{
		return DetailScreen.instance;
	}
	
	public Province getProvince()
	{
		return this.province;
	}
	
	private int getProvinceSliderNumber()
	{
		return this.provinceAllocation.getSelectedSlider().getType().getNumber();
	}
	
	private int getLeaderSliderNumber()
	{
		LeaderAllocation leaderAllocation = this.provinceLeaders.getAllocation();
		return leaderAllocation.getSelectedSlider().getType().getNumber();
	}
	
	private void updateProvinceAllocation(AllocationSlider destinationSlider)
	{
		this.provinceAllocation.updateCitizenDivision(destinationSlider);
		updateTransfer();
	}
	
	private void updateLeaderAllocation(AllocationSlider destinationSlider)
	{
		LeaderAllocation leaderAllocation = this.provinceLeaders.getAllocation();
		leaderAllocation.updateCitizenDivision(destinationSlider);
		updateTransfer();
	}
	
	private void updateTransfer()
	{
		int provinceSliderNumber = getProvinceSliderNumber();
		int leaderSliderNumber = getLeaderSliderNumber();
		movingTransferSlider = true;
		this.footerBar.setSliderPosition(provinceSliderNumber, (provinceSliderNumber + leaderSliderNumber));
		movingTransferSlider = false;
	}
	
	private void transferCitizens()
	{
		if (movingTransferSlider == false)
		{			
			double position = this.footerBar.getSliderNumber();
			int provinceNumber = getProvinceSliderNumber();
			int leaderNumber = getLeaderSliderNumber();
			int total = provinceNumber + leaderNumber;
			double newProvinceDouble = (1 - position) * total;
			double newLeaderDouble = position * total;
			int newProvinceNumber = Math.round((float) newProvinceDouble);		
			int newLeaderNumber = Math.round((float) newLeaderDouble);
			LeaderAllocation leaderAllocation = this.provinceLeaders.getAllocation();
			AllocationSlider provinceSlider = this.provinceAllocation.getSelectedSlider();
			AllocationSlider leaderSlider = leaderAllocation.getSelectedSlider();
			CitizenType provinceSelectedType = provinceSlider.getType();
			CitizenType leaderSelectedType = leaderSlider.getType();
			
			provinceSelectedType.setNumber(newProvinceNumber);
			leaderSelectedType.setNumber(newLeaderNumber);
			
			this.province.setCitizens(this.province.getCitizens() + newProvinceNumber - provinceNumber);		
			Leader currentLeader = this.currentLeader;
			currentLeader.setCitizens(currentLeader.getCitizens() + newLeaderNumber - leaderNumber);
			
			this.provinceAllocation.getSliderSet().correctSliders();
			leaderAllocation.getSliderSet().correctSliders();
			
			leaderAllocation.updateLabels();
			this.provinceAllocation.updateLabels();
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		ListenerSlider destinationSlider = (ListenerSlider) e.getSource();
		switch(destinationSlider.getId())
		{
		case BUILDER_ID:
		case FARMER_ID:
		case GATHERER_ID:
			updateProvinceAllocation((AllocationSlider) destinationSlider);
			break;
		case CARRIER_ID:
		case ENVOY_ID:
		case MESSENGER_ID:
		case SOLDIER_ID:
			updateLeaderAllocation((AllocationSlider) destinationSlider);
			break;
		case TRANSFER_ID:
			transferCitizens();
			break;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		int value = ((JList) e.getSource()).getSelectedIndex();
		this.currentLeader = province.getProvinceLeaders().get(value);
		provinceLeaders.updateLeaderDetails(this.currentLeader);
		ordersPaneContainer.changeLeader(this.currentLeader);
		updateTransfer();
	}
	
	
	public Leader getCurrentLeader()
	{
		return this.currentLeader;
	}
	
	public void setCurrentLeader(Leader leader)
	{
		this.currentLeader = leader;
	}
}

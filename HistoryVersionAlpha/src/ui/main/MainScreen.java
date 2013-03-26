package ui.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import leaderData.CivicLeader;
import leaderData.General;
import leaderData.Leader;
import leaderData.Lord;
import leaderData.Magician;
import leaderData.MilitaryLeader;
import leaderData.Minister;
import leaderData.Ninja;
import leaderData.NpcLeader;
import lists.ListenerList;
import lists.ProvinceList;
import main.FlagValues;
import main.MainFrame;
import map.MapButton;
import map.MapPanel;
import map.ProvinceButton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import ui.ButtonId;
import ui.Cursors;
import ui.LeaderButton;
import ui.LeaderPane;
import ui.ListenerButton;
import ui.ProvincePane;
import ui.detail.OrdersPaneContainer;

import data.CitizenType;
import data.Journey;
import data.Owner;
import data.OwnerType;
import data.Province;
import data.ProvinceJourney;
import data.Step;
import data.StepState;
import file.MapFileFilter;
import file.MissionFileFilter;

@SuppressWarnings("serial")
public class MainScreen extends JPanel implements ActionListener, ListSelectionListener, MouseListener
{
	private static final int OPEN = 0;
	private static final int SAVE = 1;
	
	private static final int LORDTYPE = 0;
	private static final int MINISTERTYPE = 1;
	private static final int GENERALTYPE = 2;
	private static final int NINJATYPE = 3;
	private static final int MAGICIANTYPE = 4;
	
	private static final String DEFAULTMAPDIRECTORY = "C:\\HistoryGame\\Maps\\";
	private static final String DEFAULTMISSIONDIRECTORY = "C:\\HistoryGame\\Missions\\";
	
	private static final String X = "x";
	private static final String Y = "y";
	private static final String PROVINCE = "province";
	private static final String PROVINCENAME = "provincename";
	private static final String RAIDCHANCE = "raidchance"; 
	private static final String RAIDMINIM = "raidminim";
	private static final String RAIDADDIT = "raidaddit";
	private static final String CROPMULTI = "cropmulti";
	private static final String GATHERERMULTI = "minemulti";
	private static final String OWNER = "owner";
	private static final String CONTAINEROWNER = "containerowner";
	private static final String SCHOOLPRESENT = "schoolpresent";
	private static final String RESTAURANTPRESENT = "restaurantpresent";
	private static final String TAILORPRESENT = "tailorpresent";
	private static final String FARMERS = "farmers";
	private static final String GATHERERS = "gatherers";
	private static final String BUILDERS = "builders";
	private static final String CITIZENS = "citizens";
	private static final String HOUSES = "houses";
	private static final String OFFICES = "offices";
	private static final String CROPS = "crops";
	private static final String MINERALS = "minerals";
	private static final String LUXURIES = "luxuries";
	private static final String LEADER = "leader";
	private static final String NEWLEADER = "newleader";
	
	private static final String LEADERNAME = "leadername";
	private static final String LEADERTYPE = "leadertype";
	private static final String LOCATION = "location";
	private static final String LOYALTY = "loyalty";
	private static final String TEACHINGABILITY = "teachingability";
	private static final String STRATEGY = "strategy";
	private static final String WAGES = "wages";
	private static final String REPUTATIONREQUIREMENT = "reputationrequirement";
	private static final String TAILORREQUIREMENT = "tailorrequirement";
	private static final String RESTAURANTREQUIREMENT = "restaurantrequirement";
	private static final String MESSENGERS = "messengers";
	private static final String OFFENCE = "offence";
	private static final String DEFENCE = "defence";
	private static final String RECRUITMENT = "recruitment";
	private static final String SOLDIERS = "soldiers";
	private static final String TENTS = "tents";
	private static final String ENVOYS = "envoys";
	private static final String CARRIERS = "carriers";
	private static final String FARMING = "farming";
	private static final String MINING = "mining";
	private static final String BUILDING = "building";
	private static final String TELEPORTATION = "teleportation";
	private static final String FIRES = "fires";
	private static final String STORMS = "storms";
	private static final String SPYING = "spying";
	private static final String SABOTAGE = "sabotage";
	private static final String ASSASSINATION = "assassination";
	
	private static final String PROVINCEJOURNEY = "provincejourney";
	private static final String ISA = "isa";
	private static final String JOURNEYNUMBER = "journeynumber";
	
	private static final String OWNERNAME = "ownername";
	private static final String OWNERTYPE = "ownertype";
	
	private static final String NUMBER = "number";
	
	private static BufferedImage baseTile;
	private static BufferedImage redFlag;
	private static BufferedImage blueFlag;
	private static BufferedImage greenFlag;
	private static BufferedImage[] terrain1;
	private static BufferedImage[] terrain2;
	private static BufferedImage[] largeTerrain;
	private static BufferedImage[] fences;
	private static BufferedImage[] roads;
	
	private static MainScreen instance;
	
	private int width;
	private int height;
	private int[][] mapData;
	
	private List<Province> provinces;
	private List<Leader> leaders;
	public List<Leader> getLeaders() {
		return leaders;
	}

	public void setLeaders(List<Leader> leaders) {
		this.leaders = leaders;
	}

	private ArrayList<Journey> journeys;
	private List<Owner> owners;
	private ArrayList<int[]> startingPoints;
	
	private MapPanel mapPanel;
	private JScrollPane scrollPane;
	private MainControlPane controlPane;
	private ButtonPane buttonPane;
	private ProvincePane provincePane;
	private DetailedLeaderPane leaderPane;
	
	private Province selectedProvince;
	private Leader selectedLeader;

	public Province getSelectedProvince()
	{
		return selectedProvince;
	}

	public void setSelectedProvince(Province selectedProvince)
	{
		this.selectedProvince = selectedProvince;
		if (this.controlPane != null)
			controlPane.changeProvince(selectedProvince);
	}
	
	public void setSelectedLeader(Leader selectedLeader)
	{
		this.selectedLeader = selectedLeader;
		if (this.controlPane != null)
		{
			controlPane.changeLeader(selectedLeader);
		}

	}

	public MainScreen()
	{	
		MainScreen.instance = this;
		loadAllImages();
		
		File mapFile = chooseMap(OPEN);
		File missionFile = chooseMission(OPEN);
		
		try
		{
			readMapFile(mapFile);
			loadMission(missionFile);
			setupScreen(mapData);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		catch (ParserConfigurationException e)
		{
			System.out.println(e);
		}
		catch (SAXException e)
		{
			System.out.println(e);
		}

	}
	
	private void loadAllImages()
	{
	    baseTile = LoadImage("Green.PNG");
	    redFlag = LoadImage("GRedFlag.PNG");
	    blueFlag = LoadImage("GBlueFlag.PNG");
	    greenFlag = LoadImage("GGreenFlag.PNG");
	    
	    terrain1 = arrayfill("Forest",16);
	    terrain2 = arrayfill("Swamp",16);
	    largeTerrain = arrayfill("LargeSwamp",3);
	    fences = arrayfill("Fences",16);
	    roads = arrayfill("Road",16);
	}
	
	public static BufferedImage LoadImage(String name)
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File("Images/" + name));
		}
		catch(IOException e)
		{
	    	JOptionPane.showMessageDialog(new JFrame(),
	    			name + " cannot be loaded. Exception: " + e, "Error",
	    	        JOptionPane.ERROR_MESSAGE);
		}	
		return img;
	}
	
	private BufferedImage[] arrayfill(String name, int number)
	{
		BufferedImage[] imageArray = new BufferedImage[number];
		String fullName;
			for(int i=0;i<number;i++)
			{
				fullName = "G" + name + (i+1) + ".PNG";
				imageArray[i ]= LoadImage(fullName);
			}
		
		return imageArray;
	}
	
	
	public static BufferedImage getBaseTile()
	{
		return baseTile;
	}
	
	public static BufferedImage getRedFlag()
	{
		return redFlag;
	}
	
	public static BufferedImage getGreenFlag()
	{
		return greenFlag;
	}
	
	public static BufferedImage getBlueFlag()
	{
		return blueFlag;
	}
	
	public static BufferedImage getTerrain1(int number)
	{
		return terrain1[number];
	}
	
	public static BufferedImage getTerrain2(int number)
	{
		return terrain2[number];
	}
	
	public static BufferedImage getLargeTerrain(int number)
	{
		return largeTerrain[number];
	}
	
	public static BufferedImage getFences(int number)
	{
		return fences[number];
	}

	public static BufferedImage getRoads(int number)
	{
		return roads[number];
	}
	
	private File chooseMap(int chooserMode)
	{
		File initialDirectory = new File(DEFAULTMAPDIRECTORY);
		JFileChooser myFileChooser = new JFileChooser(initialDirectory);
		MapFileFilter myFileFilter = new MapFileFilter();
		myFileChooser.addChoosableFileFilter(myFileFilter);
		
		int result;
		
		if (chooserMode==SAVE)
			result = myFileChooser.showSaveDialog(this);
		else
			result = myFileChooser.showOpenDialog(this);
		
		if (result==JFileChooser.APPROVE_OPTION)
			return myFileChooser.getSelectedFile();
		else
			return null;
	}
	
	private File chooseMission(int chooserMode)
	{
		File initialDirectory = new File(DEFAULTMISSIONDIRECTORY);
		JFileChooser myFileChooser = new JFileChooser(initialDirectory);
		MissionFileFilter myFileFilter = new MissionFileFilter();
		myFileChooser.addChoosableFileFilter(myFileFilter);
		
		int result;
		
		if (chooserMode==SAVE)
			result = myFileChooser.showSaveDialog(this);
		else
			result = myFileChooser.showOpenDialog(this);
		
		if (result==JFileChooser.APPROVE_OPTION)
			return myFileChooser.getSelectedFile();
		else
			return null;
	}
	
	private void readMapFile(File myFile) throws IOException
	{
		FileReader myFileReader = null;
		myFileReader = new FileReader(myFile);
		
		loadMapData(myFileReader);
		loadJourneyData(myFileReader);
		
	}
	
	private void loadMapData(FileReader myFileReader) throws IOException
	{
		int[] parameters = loadParametersFromFile(myFileReader);
		mapData = loadDataFromFile(myFileReader, parameters);
		
		width = parameters[0];
		height = parameters[1];
	}
	
	private void loadJourneyData(FileReader myFileReader) throws IOException
	{
		ArrayList<Journey> journeys = new ArrayList<Journey>();
		char[] dataChars = new char[3];
	
		int data = myFileReader.read(dataChars, 0, 2);
		
		while (data != -1)
		{
			List<Step> steps = new ArrayList<Step>();
			steps.add(new Step((int)dataChars[0], (int)dataChars[1], StepState.DESTINATION));
			Journey newJourney = new Journey();
			newJourney.setA(checkStartingPoints((int)dataChars[0], (int)dataChars[1]));
			StepState currentStepState;
			do
			{
				myFileReader.read(dataChars, 0, 3);
				currentStepState = StepState.getStepState((int)dataChars[2]);
				steps.add((new Step((int)dataChars[0], (int)dataChars[1], currentStepState)));
			}while (currentStepState != StepState.DESTINATION);
			newJourney.setB(checkStartingPoints((int)dataChars[0], (int)dataChars[1]));
			newJourney.setSteps(steps);
			journeys.add(newJourney);
			
			data = myFileReader.read(dataChars, 0, 2);
		}
	}
	
	private int checkStartingPoints(int x, int y)
	{
		for (int i=0;i<startingPoints.size();i++)
		{
			if (startingPoints.get(i)[0] == x && startingPoints.get(i)[1] == y)
				return i;
		}
		return -1;
	}
	
	private int[] loadParametersFromFile(FileReader myFileReader) throws IOException
	{
		char[] limitChars = new char[2];
		myFileReader.read(limitChars,0,2);
		
		int[] parameters = new int[2];
		
		parameters[0] = (int) limitChars[0];
		parameters[1] = (int) limitChars[1];
		
		return parameters;
	}
	
	private int[][] loadDataFromFile(FileReader myFileReader, int[] parameters) throws IOException
	{
		int charNumber = (parameters[0] * parameters[1]);
		char[] dataChars = new char[charNumber];
		myFileReader.read(dataChars,0,charNumber);
		
		int[][] mapData = new int[parameters[0]][parameters[1]];
		startingPoints = new ArrayList<int[]>();
		
		for(int i=0;i<parameters[0];i++)
		{
			for(int j=0;j<parameters[1];j++)
			{
				mapData[i][j] = (int)dataChars[(parameters[1]*i)+j];
				if (mapData[i][j]==2 || mapData[i][j]==3 || mapData[i][j]==4)
					startingPoints.add(new int[]{i,j});
			}
		}
		
		return mapData;
	}
	
	private Owner getOwnerByName(String name)
	{
		for (Owner owner : this.owners)
		{
			if (owner.getName().compareTo(name) == 0)
				return owner;
		}
		return null;
	}
	
	private void setupScreen(int[][] mapData)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		mapPanel = new MapPanel(width, height, mapData);
		setupScrollPane(mapPanel);
		setupSidebar();
		
		setSelectedProvince(provinces.get(0));
	}
	
	private void setupSidebar()
	{
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridBagLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int buttonWidth = screenSize.width /3;
		Dimension sidebarSize = new Dimension(buttonWidth, screenSize.height);
		sidebar.setMaximumSize(sidebarSize);
		sidebar.setPreferredSize(sidebarSize);
	
		buttonPane = new ButtonPane();
		sidebar.add(buttonPane, setupButtonPaneConstraints());
		controlPane = new MainControlPane();
		sidebar.add(controlPane, setupControlPaneConstraints());
		
		this.add(sidebar);
	}
	
	private GridBagConstraints setupButtonPaneConstraints()
	{
		GridBagConstraints buttonPaneConstraints = new GridBagConstraints();
		buttonPaneConstraints.gridx = 1;
		buttonPaneConstraints.gridy = 0;
		buttonPaneConstraints.weightx = 3;
		buttonPaneConstraints.weighty = 0;
		buttonPaneConstraints.fill = GridBagConstraints.BOTH;
		buttonPaneConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		buttonPaneConstraints.insets = new Insets(5,5,5,5);
		
		return buttonPaneConstraints;
	}
	
	private void setupScrollPane(MapPanel mapPanel)
	{
		scrollPane = new JScrollPane(mapPanel);
	    scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
	       
	    add(scrollPane);
	}
	
	private GridBagConstraints setupScrollPaneConstraints()
	{
		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 0;
		scrollConstraints.gridheight = 4;
		scrollConstraints.weightx = 1;
		scrollConstraints.weighty = 1;
		scrollConstraints.insets = new Insets(5,5,5,5);
		scrollConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		scrollConstraints.fill = GridBagConstraints.BOTH;
		return scrollConstraints;
	}
	
	private GridBagConstraints setupControlPaneConstraints()
	{
		GridBagConstraints controlPaneConstraints = new GridBagConstraints();
		controlPaneConstraints.gridx = 1;
		controlPaneConstraints.gridy = 1;
		controlPaneConstraints.weightx = 3;
		controlPaneConstraints.weighty = 1;
		controlPaneConstraints.fill = GridBagConstraints.BOTH;
		controlPaneConstraints.insets = new Insets(5,5,5,5);
		controlPaneConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		
		return controlPaneConstraints;
	}
	
	private void quit()
	{
		System.exit(0);
	}
	
	private void loadMission(File missionFile) throws ParserConfigurationException,
	SAXException, IOException
	{
		Document document = getDocument(missionFile);
		loadOwners(document);
		loadLeaders(document);
		loadProvinces(document);
	}
	
	private Document getDocument(File missionFile) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(missionFile);
		document.getDocumentElement().normalize();
		return document;
	}
	
	private void loadProvinces(Document document)
	{
		NodeList provinceNodes = document.getElementsByTagName(PROVINCE);
		provinces = new ArrayList<Province>();
		
		for(int i=0; i<provinceNodes.getLength();i++)
		{
			Element element = (Element)provinceNodes.item(i);
			Province newProvince = createProvince(element);
			if (newProvince!=null)
			{
				finishProvince(newProvince, element);
				provinces.add(newProvince);
			}
		}
	}
	
	private Province createProvince(Element provinceElement)
	{
		int x = loadInt(provinceElement, X);
		int y = loadInt(provinceElement, Y);
		
		if (mapData[x][y] == FlagValues.REDFLAGVALUE.valueOf() ||
			mapData[x][y] == FlagValues.BLUEFLAGVALUE.valueOf() ||
			mapData[x][y] == FlagValues.GREENFLAGVALUE.valueOf())
			return new Province(x,y);
		else
			return null;
	}
	
	private void finishProvince(Province newProvince, Element element)
	{
		newProvince.setName(loadString(element,PROVINCENAME));
		newProvince.setProvinceJourneys(loadProvinceJourneys(element));
		newProvince.setRaidchance(loadInt(element,RAIDCHANCE));
		newProvince.setRaidminim(loadInt(element,RAIDMINIM));
		newProvince.setRaidaddit(loadInt(element,RAIDADDIT));
		newProvince.setCropmulti(loadInt(element,CROPMULTI));
		newProvince.setMinemulti(loadInt(element,GATHERERMULTI));
		newProvince.setOwner(this.getOwnerByName(loadString(element, CONTAINEROWNER)));
		newProvince.setSchoolpresent(loadBool(element,SCHOOLPRESENT));
		newProvince.setRestaurantpresent(loadBool(element,RESTAURANTPRESENT));
		newProvince.setTailorpresent(loadBool(element,TAILORPRESENT));
		
		int farmerNumber = loadInt(element,FARMERS);
		int gathererNumber= loadInt(element,GATHERERS);
		int builderNumber = loadInt(element,BUILDERS);
		
		CitizenType farmerType = setupNewCitizenType(farmerNumber);
		CitizenType gathererType = setupNewCitizenType(gathererNumber);
		CitizenType builderType = setupNewCitizenType(builderNumber);
		
		newProvince.setFarmers(farmerType);
		newProvince.setGatherers(gathererType);
		newProvince.setBuilders(builderType);
		
		newProvince.setCitizens(farmerNumber + gathererNumber + builderNumber);

		newProvince.setHouses(loadInt(element,HOUSES));
		newProvince.setOffices(loadInt(element,OFFICES));
		newProvince.setCrops(loadInt(element,CROPS));
		newProvince.setMinerals(loadInt(element,MINERALS));
		newProvince.setLuxuries(loadInt(element,LUXURIES));
		
		List<String> provinceLeaderStrings = (loadStringList(element,LEADER));
		ArrayList<Leader> provinceLeaderList = loadLeadersFromStrings(provinceLeaderStrings);
		newProvince.setProvinceLeaders(provinceLeaderList);
	}
	
	private CitizenType setupNewCitizenType(int number)
	{
		CitizenType newType = new CitizenType();
		newType.setNumber(number);
		return newType;
	}
	
	private ArrayList<Leader> loadLeadersFromStrings(List<String> provinceLeaderStrings)
	{
		ArrayList<Leader> provinceLeaderList = new ArrayList<Leader>();
		
		for(String provinceLeader: provinceLeaderStrings)
		{
			Leader leader = getLeaderByName(provinceLeader);
			if (leader!=null)
				provinceLeaderList.add(leader);
		}
		
		return provinceLeaderList;
	}
	
	private Leader getLeaderByName(String provinceLeader)
	{
		for(Leader leader: leaders)
		{
			if (provinceLeader.compareTo(leader.getName())==0)
			{
				return leader;
			}
		}
		return null;
	}
	
	private void loadLeaders(Document document)
	{
		NodeList leaderNodes = document.getElementsByTagName(NEWLEADER);
		leaders = new ArrayList<Leader>();
		
		for(int i=0; i<leaderNodes.getLength();i++)
		{
			Element element = (Element)leaderNodes.item(i);
			Leader newLeader = createLeader(element);
			leaders.add(newLeader);
		}
	}
	
	private Leader createLeader(Element leaderElement)
	{
		String name = loadString(leaderElement, LEADERNAME);
		int type = loadInt(leaderElement, LEADERTYPE);
		Leader newLeader;
		
		switch (type)
		{
		case LORDTYPE:
			newLeader = createLord(leaderElement, name);
			break;
		case MINISTERTYPE:
			newLeader = createMinister(leaderElement, name);
			break;
		case GENERALTYPE:
			newLeader = createGeneral(leaderElement, name);
			break;
		case NINJATYPE:
			newLeader = createNinja(leaderElement, name);
			break;
		case MAGICIANTYPE:
			newLeader = createMagician(leaderElement, name);
			break;
		default:
			newLeader=null;
		}
		return newLeader;
	}
	
	private void setNpcQualities(NpcLeader newLeader, Element leaderElement)
	{
		newLeader.setLoyalty(loadInt(leaderElement, LOYALTY));
		newLeader.setStrategy(loadInt(leaderElement, STRATEGY));
		newLeader.setWages(loadInt(leaderElement, WAGES));
		newLeader.setReputationrequirement(loadInt(leaderElement, REPUTATIONREQUIREMENT));
		newLeader.setTailorRequirement(loadBool(leaderElement, TAILORREQUIREMENT));
		newLeader.setRestaurantRequirement(loadBool(leaderElement, RESTAURANTREQUIREMENT));
	}
	
	private void setCivicQualities(CivicLeader newLeader, Element leaderElement)
	{
		newLeader.setFarming(loadInt(leaderElement, FARMING));
		newLeader.setMining(loadInt(leaderElement, MINING));
		newLeader.setBuilding(loadInt(leaderElement, BUILDING));
	}
	
	private void setMilitaryQualities(MilitaryLeader newLeader, Element leaderElement)
	{
		newLeader.setOffence(loadInt(leaderElement, OFFENCE));
		newLeader.setDefence(loadInt(leaderElement, DEFENCE));
		newLeader.setRecruitment(loadInt(leaderElement, RECRUITMENT));
	}
	
	private void setUniversalQualities(Leader newLeader, Element leaderElement)
	{
		newLeader.setOwner(this.getOwnerByName(loadString(leaderElement, CONTAINEROWNER)));
		newLeader.setLocation(loadString(leaderElement, LOCATION));
		newLeader.setTeachingability(loadInt(leaderElement, TEACHINGABILITY));
		newLeader.setTents(loadInt(leaderElement, TENTS));
	}
	
	private Leader createLord(Element leaderElement, String name)
	{
		Lord lord = new Lord(name);
		this.setUniversalQualities(lord, leaderElement);
		this.setCivicQualities(lord, leaderElement);
		this.setMilitaryQualities(lord, leaderElement);
		
		int envoyNumber = loadInt(leaderElement, ENVOYS);
		int carrierNumber = loadInt(leaderElement, CARRIERS);
		int soldierNumber = loadInt(leaderElement, SOLDIERS);
		
		lord.setEnvoys(setupNewCitizenType(envoyNumber));
		lord.setCarriers(setupNewCitizenType(carrierNumber));
		lord.setSoldiers(setupNewCitizenType(soldierNumber));
		lord.setCitizens(soldierNumber + envoyNumber + carrierNumber);

		return lord;
	}
	
	private Leader createMinister(Element leaderElement, String name)
	{
		Minister minister = new Minister(name);
		this.setUniversalQualities(minister, leaderElement);
		this.setNpcQualities(minister, leaderElement);
		this.setCivicQualities(minister, leaderElement);
		
		int messengerNumber = loadInt(leaderElement, MESSENGERS);
		int envoyNumber = loadInt(leaderElement, ENVOYS);
		int carrierNumber = loadInt(leaderElement, CARRIERS);
	
		minister.setMessengers(setupNewCitizenType(messengerNumber));
		minister.setEnvoys(setupNewCitizenType(envoyNumber));
		minister.setCarriers(setupNewCitizenType(carrierNumber));
		minister.setCitizens(messengerNumber + envoyNumber + carrierNumber);
		
		return minister;
	}
	
	private Leader createGeneral(Element leaderElement, String name)
	{
		General general = new General(name);
		this.setUniversalQualities(general, leaderElement);
		this.setNpcQualities(general, leaderElement);
		this.setMilitaryQualities(general, leaderElement);
		
		int messengerNumber = loadInt(leaderElement, MESSENGERS);
		int envoyNumber = loadInt(leaderElement, ENVOYS);
		int carrierNumber = loadInt(leaderElement, CARRIERS);
		int soldierNumber = loadInt(leaderElement, SOLDIERS);
	
		general.setMessengers(setupNewCitizenType(messengerNumber));
		general.setEnvoys(setupNewCitizenType(envoyNumber));
		general.setCarriers(setupNewCitizenType(carrierNumber));
		general.setSoldiers(setupNewCitizenType(soldierNumber));
		general.setCitizens(messengerNumber + envoyNumber + carrierNumber + soldierNumber);
		
		return general;
	}
	
	private Leader createNinja(Element leaderElement, String name)
	{
		Ninja ninja = new Ninja(name);
		this.setUniversalQualities(ninja, leaderElement);
		this.setNpcQualities(ninja, leaderElement);
		ninja.setSpying(loadInt(leaderElement, SPYING));
		ninja.setAssassination(loadInt(leaderElement, ASSASSINATION));
		ninja.setSabotage(loadInt(leaderElement, SABOTAGE));
		
		int messengerNumber = loadInt(leaderElement, MESSENGERS);
		ninja.setMessengers(setupNewCitizenType(messengerNumber));
		ninja.setCitizens(messengerNumber);
		
		return ninja;
	}
	
	private Leader createMagician(Element leaderElement, String name)
	{
		Magician magician = new Magician(name);
		this.setUniversalQualities(magician, leaderElement);
		this.setNpcQualities(magician, leaderElement);
		magician.setTeleportation(loadInt(leaderElement, TELEPORTATION));
		magician.setFires(loadInt(leaderElement, FIRES));
		magician.setStorms(loadInt(leaderElement, STORMS));
		
		int messengerNumber = loadInt(leaderElement, MESSENGERS);
		magician.setMessengers(setupNewCitizenType(messengerNumber));
		magician.setCitizens(messengerNumber);
		
		return magician;
	}
	
	private int loadInt(Element element, String tagName)
	{
		NodeList nodeList = element.getElementsByTagName(tagName);
		Node node = nodeList.item(0);
		Node textNode = node.getFirstChild();
		String string = textNode.getTextContent();
		return Integer.parseInt(string);
	}
	
	private boolean loadBool(Element element, String tagName)
	{
		NodeList nodeList = element.getElementsByTagName(tagName);
		Node node = nodeList.item(0);
		Node boolNode= node.getFirstChild();
		String boolValue=boolNode.getNodeValue();
		return boolValue.compareTo("true")==0;
	}
	
	private String loadString(Element element, String tagName)
	{
		NodeList nodeList = element.getElementsByTagName(tagName);
		Node node = nodeList.item(0);
		Node stringNode = node.getFirstChild();
		return stringNode.getNodeValue();
	}
	
	private List<String> loadStringList(Element element, String tagName)
	{
		NodeList nodeList = element.getElementsByTagName(tagName);
		List<String> stringList = new ArrayList<String>();
		for (int i=0;i<nodeList.getLength();i++)
		{
			Node node = nodeList.item(i);
			Node listNode = node.getFirstChild();
			String string = listNode.getNodeValue();
			stringList.add(string);
		}
		return stringList;
	}
	
	private List<Integer> loadIntegerList(Element element, String tagName)
	{
		NodeList nodeList = element.getElementsByTagName(tagName);
		List<Integer> integerList = new ArrayList<Integer>();
		for (int i = 0;i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			Node listNode = node.getFirstChild();
			String string = listNode.getNodeValue();
			integerList.add(Integer.getInteger(string));
		}
		return integerList;
	}
	
	private ArrayList<ProvinceJourney> loadProvinceJourneys(Element element)
	{
		NodeList nodeList = element.getElementsByTagName(PROVINCEJOURNEY);
		ArrayList<ProvinceJourney> provinceJourneyList = new ArrayList<ProvinceJourney>();
		for (int i=0;i<nodeList.getLength();i++)
		{
			Node node = nodeList.item(i);
			Element provinceJourneyElement = (Element) node;
			ProvinceJourney provinceJourney = loadProvinceJourney(provinceJourneyElement);
			provinceJourneyList.add(provinceJourney);
		}
		return provinceJourneyList;
	}
	
	private ProvinceJourney loadProvinceJourney(Element element)
	{
		int number = loadInt(element, JOURNEYNUMBER);
		boolean isA = loadBool(element, ISA);
		ProvinceJourney provinceJourney = new ProvinceJourney(number, isA);
		
		return provinceJourney;
	}
	
	private int loadAttribute (Element element, String attributeName)
	{
		String string = element.getAttribute(attributeName);
		return Integer.parseInt(string);
	}
	
	private void loadOwners(Document document)
	{
		NodeList nodeList = document.getElementsByTagName(OWNER);
		owners = new ArrayList<Owner>(nodeList.getLength());
		for (int i=0; i < nodeList.getLength();i++)
		{
			Node node = nodeList.item(i);
			Element listElement = (Element) node;
			Owner owner = loadOwner(listElement);
			owners.add(owner);
		}
	}
	
	private Owner loadOwner(Element element)
	{
		String name = loadString(element, OWNERNAME);
		OwnerType type = OwnerType.valueOf(loadString(element, OWNERTYPE));
		Owner owner = new Owner(type, name);
		return owner;
	}

	private void changedList(ListenerList list)
	{
		switch(list.getId())
		{
			case SELECT_PROVINCE_ID:
				this.setSelectedProvince((Province) list.getSelectedValues()[0]);
				break;
			case SELECT_LEADER_ID:
				this.setSelectedLeader((Leader) list.getSelectedValues()[0]);
				break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source =  e.getSource();
		
		if (source.getClass() == ListenerButton.class)
		{
			pressedListenerButton((ListenerButton)source);
		}
		else if (source.getClass() == ProvinceButton.class)
		{
			pressedProvinceButton((ProvinceButton)source);
		}
		else if (source.getClass() == LeaderButton.class)
		{
//			controlPane.changeToLeader(((LeaderButton) source).getLeader());
		}
	}
	
	private void pressedProvinceButton(ProvinceButton button)
	{
		Province province = button.getProvince();
		this.setSelectedProvince(province);
	}
	
	private void pressedListenerButton(ListenerButton button)
	{
		ButtonId id = button.getId();
		
		switch(id)
		{
			case LOG_ID:
				break;
			case OPTION_ID:
				break;
			case TURN_ID:
				break;
			case BACK_ID:
//				this.controlPane.changeToProvince(this.selectedProvince);
				break;
			case QUIT_ID:
				quit();
				break;
		}
	}
	
	public static MainScreen getInstance()
	{
		return instance;
	}
	
	public List<Province> getProvinceList()
	{
		return this.provinces;
	}
	
	public Province getProvinceByPos(int x, int y)
	{
		for (Province province : this.provinces)
		{
			if (province.getXposition() == x && province.getYposition() == y)
				return province;
		}
		return null;
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getSource() instanceof ListenerList)
		{
			changedList((ListenerList) e.getSource());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
       if (e.getClickCount() == 2)
       {
    	   Component component = e.getComponent();
    	   
    	   if (component instanceof ListenerList)
    	   {
    		   ListenerList list = (ListenerList) component;
    		   Point point = e.getPoint();
    		   int index = list.locationToIndex(point);
    		   if (list.getCellBounds(index, index).contains(point))
    		   {
    			   if (list instanceof ProvinceList)
    			   {
        			   MainFrame.getInstance().changeToDetailsScreen((Province) list.getModel().getElementAt(index));
    			   }
    		   }

    	   }

       }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
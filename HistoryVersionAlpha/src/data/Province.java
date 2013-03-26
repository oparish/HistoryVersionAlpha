package data;
import java.util.ArrayList;
import java.util.List;

import leaderData.Leader;

import sliders.SliderId;

public class Province implements CitizenContainer
{
	private int xposition;
	private int yposition;
	private String name;
	private ArrayList<ProvinceJourney> provinceJourneys;
	private int raidchance;
	private int raidminim;
	private int raidaddit;
	private int cropmulti;
	private int minemulti;
	private Owner owner;
	private boolean ownerpresent;
	private ArrayList<Leader> provinceLeaders;
	private boolean schoolpresent;
	private boolean restaurantpresent;
	private boolean tailorpresent;
	private CitizenType farmers;
	private CitizenType gatherers;
	private CitizenType builders;
	private ArrayList<CitizenType> citizenTypes;
	private int citizens;
	private int houses;
	private int offices;
	private int crops;
	private int resources;
	private int luxuries;
	private ProjectType currentProject;
	private ArrayList<ProjectType> projectQueue;
	
	public ProjectType getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(ProjectType currentProject) {
		this.currentProject = currentProject;
	}


	public ArrayList<ProjectType> getProjectQueue() {
		return projectQueue;
	}

	private int buildPoints;
	
	public int getBuildPoints() {
		return buildPoints;
	}

	public void setBuildPoints(int buildPoints) {
		this.buildPoints = buildPoints;
	}

	public void addProjectToQueue(ProjectType project, int index)
	{
		this.projectQueue.add(index, project);
	}
	
	public void removeProjectFromQueue(int index)
	{
		this.projectQueue.remove(index);
	}
	
	public void setProjectQueue(ArrayList<ProjectType> queue)
	{
		this.projectQueue = queue;
	}
	
	public Province(int x, int y)
	{
		xposition = x;
		yposition = y;
		this.citizenTypes = new ArrayList<CitizenType>();
		this.projectQueue = new ArrayList<ProjectType>();
	}
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public int getXposition()
	{
		return xposition;
	}
	
	public int getYposition()
	{
		return yposition;
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<ProvinceJourney> getProvinceJourneys()
	{
		return provinceJourneys;
	}
	
	public int getRaidchance()
	{
		return raidchance;
	}
	
	public int getRaidminim()
	{
		return raidminim;
	}
	
	public int getRaidaddit()
	{
		return raidaddit;
	}
	
	public int getCropmulti()
	{
		return cropmulti;
	}
	
	public int getMinemulti()
	{
		return minemulti;
	}
	
	public boolean getOwnerpresent()
	{
		return ownerpresent;
	}
	
	public ArrayList<Leader> getProvinceLeaders()
	{
		return provinceLeaders;
	}
	
	public boolean getSchoolpresent()
	{
		return schoolpresent;
	}
	
	public boolean getRestaurantpresent()
	{
		return restaurantpresent;
	}
	
	public boolean getTailorpresent()
	{
		return tailorpresent;
	}
	
	public CitizenType getFarmers()
	{
		return this.farmers;
	}
	
	public CitizenType getGatherers()
	{
		return this.gatherers;
	}
	
	public CitizenType getBuilders()
	{
		return this.builders;
	}
	
	public int getCitizens()
	{
		return citizens;
	}
	
	public int getHouses()
	{
		return houses;
	}
	
	public int getOffices()
	{
		return offices;
	}
	
	public int getCrops()
	{
		return crops;
	}
	
	public int getMinerals()
	{
		return resources;
	}
	
	public int getLuxuries()
	{
		return luxuries;
	}
	
	public void setXposition(int value)
	{
		xposition = value;
	}
	
	public void setYposition(int value)
	{
		yposition = value;
	}
	
	public void setName(String value)
	{
		name = value;
	}
	
	public void setProvinceJourneys(ArrayList<ProvinceJourney> value)
	{
		provinceJourneys = value;
	}
	
	public void setRaidchance(int value)
	{
		raidchance = value;
	}
	
	public void setRaidminim(int value)
	{
		raidminim = value;
	}
	
	public void setRaidaddit(int value)
	{
		raidaddit = value;
	}
	
	public void setCropmulti(int value)
	{
		cropmulti = value;
	}
	
	public void setMinemulti(int value)
	{
		minemulti = value;
	}
	
	public void setOwnerpresent(boolean value)
	{
		ownerpresent = value;
	}
	
	public void setProvinceLeaders(ArrayList<Leader> value)
	{
		provinceLeaders = value;
	}
	
	public void setSchoolpresent(boolean value)
	{
		schoolpresent = value;
	}
	
	public void setRestaurantpresent(boolean value)
	{
		restaurantpresent = value;
	}
	
	public void setTailorpresent(boolean value)
	{
		tailorpresent = value;
	}
	
	public void setCitizens(int value)
	{
		this.citizens = value;
		for (CitizenType type : citizenTypes)
		{
			type.setTotal(value);
		}
	}
	
	public void setHouses(int value)
	{
		houses = value;
	}
	
	public void setOffices(int value)
	{
		offices = value;
	}
	
	public void setCrops(int value)
	{
		crops = value;
	}
	
	public void setMinerals(int value)
	{
		resources = value;
	}
	
	public void setLuxuries(int value)
	{
		luxuries = value;
	}
	
	public void setFarmers(CitizenType value)
	{
		this.farmers = value;
		citizenTypes.add(this.farmers);
	}
	
	public void setBuilders(CitizenType value)
	{
		this.builders = value;
		citizenTypes.add(this.builders);
	}
	
	public void setGatherers(CitizenType value)
	{
		this.gatherers = value;
		citizenTypes.add(this.gatherers);
	}
}

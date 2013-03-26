package orderData;

import data.AllocationType;
import data.AssassinationTarget;
import data.BehaviourType;
import data.BuildingProject;
import data.NamedEnum;
import data.SabotageTarget;
import data.Subject;
import leaderData.Leader;
import ui.main.MainScreen;

public interface OrderElement extends NamedEnum
{
	public Object[] getValues();
	
	public enum OrderElementType
	{
		LEADER(Leader.class, null)
		{
			@Override
			public OrderElement getDefaultElement()
			{
				return MainScreen.getInstance().getLeaders().get(0);
			}
			
			@Override
			public OrderElement[] getElementCollection()
			{
				return (OrderElement[]) MainScreen.getInstance().getLeaders().toArray();
			}
		},
		ALLOCATIONTYPE(AllocationType.class, AllocationType.MOREBUILDERS), 
		ASSASSINATIONTARGET(AssassinationTarget.class, AssassinationTarget.TEST), 
		BEHAVIOURTYPE(BehaviourType.class, BehaviourType.CAUTIOUSDEFENCE), 
		BUILDINGPROJECT(BuildingProject.class, BuildingProject.MOREHOUSES), 
		SABOTAGETARGET(SabotageTarget.class, SabotageTarget.TEST), 
		SUBJECT(Subject.class, Subject.TEST);
		
		private final OrderElement defaultElement;
		
		public OrderElement getDefaultElement() {
			return defaultElement;
		}

		private final Class<? extends OrderElement> elementClass;

		public Class<? extends OrderElement> getElementClass() {
			return this.elementClass;
		}

		OrderElementType(Class<? extends OrderElement> elementClass, OrderElement defaultElement)
		{
			this.elementClass = elementClass;
			this.defaultElement = defaultElement;
		}
		
		public OrderElement[] getElementCollection()
		{
			return this.elementClass.getEnumConstants();
		}
		
		public static OrderElementType typeFromClass(Class<? extends OrderElement> elementClass)
		{
			for (OrderElementType orderElementType : OrderElementType.values())
			{
				if ((orderElementType.getElementClass().isAssignableFrom(elementClass)))
					return orderElementType;
			}
			return null;
		}
	}
}

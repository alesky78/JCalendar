package it.spaghettisource.certgen.ui.swing.component.calendar.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;

/**
 * This class create a grid of x days for all the event passed during the {@link #populate(Collection, Date)}} method.
 * It is able to organize the event positioning in order like the classic agenda and expose all the method to analyze it.
 * By default the empty parameter constructor initialize it to support 7 days, but it is possible use the customize  constructor to change the amount of managed days
 * 
 * @author Alessandro D'Ottavio
 *
 */
public class EventGrid {

	private int managedDays;
	private Date[] index;
	private ArrayList<CalendarEvent>[] grid;
	
	public EventGrid() {
		this(7);
	}
	
	public EventGrid(int managedDays) {
		this.managedDays = managedDays;
		index = new Date[managedDays];
		grid  = new ArrayList[managedDays];
	}

	
	/**
	 * populate the grip considering the received list already ordered
	 * 
	 * @param events
	 */
	public void populate(Collection<CalendarEvent> events, Date start){
				
		
		//initialize the index data
    	Calendar day = CalendarUtil.getCalendar(start, true);
		for (int i = 0; i < managedDays; i++) {
			index[i] = day.getTime();
			grid[i] = new ArrayList<>();
			day.add(Calendar.DAY_OF_MONTH, 1);
		}

		//populate the grid
		int firstIndex;
		int lastIndex;
		int position;
		boolean found;		
		for (CalendarEvent event : events) {
			firstIndex = findIndex(event.getStart());
			lastIndex = findIndex(event.getEnd());
			
			//start to search an available position for this event
			found = false;
			position = 0;
			
			while(!found) {
				if(isPositionFree(position,firstIndex,lastIndex)) {
					found = true;
					addInPosition(position,firstIndex,lastIndex,event);
				}else {
					position += 1;
				}	
			}
		}	
	}
	
	public int findPosition(CalendarEvent events, Date toCheck) {
		int index = findIndex(toCheck);
		if(index==-1) {
			return -1;
		}
		
		for (int i = 0; i < grid[index].size(); i++) {
			if(events.equals(grid[index].get(i))) {
				return i;
			}
		}
		
		return -1;
	}
	
	public CalendarEvent findEventAtPosition(int xPosition, int yPosition) {
		if(!isInitialized()) {
			return null;
		}
		
		if(grid[xPosition].size() <(yPosition+1)) {
			return null;
		}
		
		return grid[xPosition].get(yPosition);

	}
	
	
	/**
	 * return all the event starting in this date,
	 * if the date if the first day of the week and the event is starting before the first day, it is returned in the first day of the week
	 * 
	 * @param actualDate
	 * @return
	 */
	public List<CalendarEvent> findEventStartingAtDate(Date actualDate) {
		ArrayList<CalendarEvent> list =  new ArrayList();
		
		int listIndex = findIndex(actualDate);
		if(listIndex==-1) {
			return list;
		}
		
		ArrayList<CalendarEvent> managedList = grid[listIndex];
		
		boolean firstDayOfWeek = actualDate.equals(index[0]) ? true:false;
		
		for (CalendarEvent calendarEvent : managedList) {
			//skip empty element managed by the list
			if(calendarEvent != null) {
				
				//if start in this day add it to the list
				if(CalendarUtil.isSameDay(calendarEvent.getStart(),actualDate)) {									
					list.add(calendarEvent);
				}

				//if we are checking the first day of the week and it start before it add it
				if(firstDayOfWeek) {
					if(CalendarUtil.isDayBefore(calendarEvent.getStart(),actualDate)) {	
						if(calendarEvent.getStart().before(index[0])) {
							list.add(calendarEvent);
						}
					}
				}
				
			}
		}
		
		return list;
	}
	
	
	private boolean isPositionFree(int position,int firstIndex,int lastIndex) {
		for (int i = firstIndex; i <= lastIndex; i++) {
			//is ArrayList is shorter is size then it is ok
			//then check immediately if the size is grater but the cell is used already 
			if(grid[i].size() >= (position+1)) {
				if(grid[i].get(position) != null) {
					return false;
				}
			}
		}
		return true;
	}

	private void addInPosition(int position, int firstIndex, int lastIndex, CalendarEvent event) {
		for (int i = firstIndex; i <= lastIndex; i++) {
			
			//increase the size if not enough
			for (int j = grid[i].size(); j < position+1; j++) {
				grid[i].add(null);
			}

			grid[i].set(position, event);
		}
	}	
	
	private int findIndex(Date toCheck) {
		if(!isInitialized()) {
			return -1;
		}
		
		toCheck = CalendarUtil.stripTime(toCheck);
		for (int i = 0; i < index.length; i++) {
			if(index[i].equals(toCheck)) {
				return i;
			}
		}
		
		//if date before first the is 0
		if(toCheck.compareTo(index[0]) <0) {
			return 0;
		}
		
		return managedDays-1;
		
	}
	
	private boolean isInitialized() {
		for (int i = 0; i < index.length; i++) {
			if(index[i]==null) {
				return false;
			}
		}
		return true;
	}









	

	
	
	
	
}

package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

public class WeekGrid {

	private Date[] index = new Date[7];
	private ArrayList<CalendarEvent>[] grid  = new ArrayList[7];
	
	public WeekGrid() {
	}

	
	/**
	 * populate the grip considering the received list already ordered
	 * 
	 * @param events
	 */
	public void populate(Collection<CalendarEvent> events, Date start){
				
		
		//initialize the index data
    	Calendar day = CalendarUtil.getCalendar(start, true);
		for (int i = 0; i < 7; i++) {
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
	
	public CalendarEvent findEventAtPosition(int position, Date toCheck) {	
		int index = findIndex(toCheck);
		if(index==-1) {
			return null;
		}

		if(grid[index].size() <(position+1)) {
			return null;
		}
		
		return grid[index].get(position);
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
		
		return 6;
		
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

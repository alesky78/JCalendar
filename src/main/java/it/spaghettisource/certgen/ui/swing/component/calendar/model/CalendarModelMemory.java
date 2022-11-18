package it.spaghettisource.certgen.ui.swing.component.calendar.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/** 
 * example implementation of the in-memory model
 * based in a Map {{@link CalendarModelMemory#memoryData}  that keep the the event grouped for each day 
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarModelMemory extends CalendarModelAbstract {

	/**
	 * store the event that are involved in a day, it means for example
	 * that an event that start from the 1 January to the 3 January will be present 3 times for day 1,2 and 3 of January
	 * 
	 * is it responsibility of this implementation remove the duplicate event to return
	 * {{@see CalendarModelMemory#getEvents(Date, Date)}
	 * 
	 * 
	 */
    private final Map<Date,List<CalendarEvent>> memoryData;    

    /**
     * Creates a new instance of {@link CalendarModelMemory}
     */
    public CalendarModelMemory() {
    	super();
        this.memoryData = new HashMap<Date, List<CalendarEvent>>();
    }
    
    
    /**
     * this is one of the most important event to understand, this event is activate only one time when you enter
     * in a view (Month, Week or Day) for a specific range of time, then this is the best point when to interact with the persistence and cache the data
     * 
     * it will be call again when the user change the range of visibility, for example move from the month of October to November in the month view
     * then at this stage interact again with the persistence and refresh the cache
     * 
     * all the find method like {{@see CalendarModelMemory#getEvents(Date, Date)} or {{@see CalendarModelMemory#getEvents(Date)}
     * should work then with the cache data
     * 
     * when instead all the methods that change the persistence state like {{@see CalendarModelMemory#add(CalendarEvent)} or {{@see CalendarModelMemory#update(CalendarEvent)} and {{@see CalendarModelMemory#remove(CalendarEvent)}
     * show interact whit the persistence and update the cache
     * 
     */
	@Override
	public void intervalChange(Date intervalStart, Date intervalEnd) {
		super.intervalChange(intervalStart, intervalEnd);
	}
    
    
    @Override
    public void add(final CalendarEvent calendarEvent) {
    	        
        final Collection<Date> dates = CalendarUtil.getDates(calendarEvent.getStart(), calendarEvent.getEnd());
        for (final Date date : dates) {
        	
        	List<CalendarEvent> result = memoryData.get(date);
        	if(result==null) {
        		result = new ArrayList<>();
        		memoryData.put(date, result);
        	}
        	result.add(calendarEvent);
        }

        fireAddEvent(calendarEvent);
    }

    @Override
    public void remove(final CalendarEvent calendarEvent) {
    	
    	boolean removed = false;
    	
        for (Date key : memoryData.keySet()) {
        	List<CalendarEvent> events = memoryData.get(key);
            if (events.contains(calendarEvent)) {
            	events.remove(calendarEvent);            	
            	removed = true;
            }
        }
        
        if(removed) {
            fireRemoveEvent(calendarEvent);        	
        }

    }
    
    public void update(final CalendarEvent calendarEvent) {
    	
    	boolean updated = false;
    	
        for (Date key : memoryData.keySet()) {
        	List<CalendarEvent> events = memoryData.get(key);
            if (events.contains(calendarEvent)) {      	
            	updated = true;
            }
        }
    	
        if(updated) {
            fireUpdateEvent(calendarEvent);        	
        }

    }
    
    @Override
    public Collection<CalendarEvent> getEvents(final Date date) {
        List<CalendarEvent> events = memoryData.get(CalendarUtil.stripTime(date));
        if (events == null) {
        	return new ArrayList<CalendarEvent>();
        }
            
        final List<CalendarEvent> result = new ArrayList<CalendarEvent>(events);
        Collections.sort(result);
        return result;
    }
    
    

	@Override
	public Collection<CalendarEvent> getEvents(Date startDate, Date endDate) {
		
        final Collection<Date> dates = CalendarUtil.getDates(startDate, endDate);
        final List<CalendarEvent> result = new ArrayList<CalendarEvent>();
        
        for (Date date : dates) {
        	List<CalendarEvent> events = memoryData.get(date);
            if (events != null) {
                for (CalendarEvent calendarEvent : events) {
    				if(!result.contains(calendarEvent)) {
    					result.add(calendarEvent);
    				}
    			}            	
            }
		}

        Collections.sort(result);
		return result;
	}


}

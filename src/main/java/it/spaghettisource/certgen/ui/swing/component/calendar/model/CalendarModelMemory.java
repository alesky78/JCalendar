package it.spaghettisource.certgen.ui.swing.component.calendar.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections.MultiHashMap;

import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarModelMemory extends CalendarModelAbstract {

    private final MultiHashMap indexedEvents;

    /**
     * Creates a new instance of {@link CalendarModelMemory}
     */
    public CalendarModelMemory() {
    	super();
        this.indexedEvents = new MultiHashMap();
    }
    
    
    
    @Override
    public void add(final CalendarEvent calendarEvent) {
    	        
        final Collection<Date> dates = CalendarUtil.getDates(calendarEvent.getStart(), calendarEvent.getEnd());
        for (final Date date : dates) {
        	indexedEvents.put(date, calendarEvent);
        }

        fireAddEvent(calendarEvent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(final CalendarEvent calendarEvent) {
    	
    	boolean removed = false;
    	
        for (final Object key : new HashSet<Object>(indexedEvents.keySet())) {
            final Collection<CalendarEvent> events = indexedEvents.getCollection(key);
            if (events.contains(calendarEvent)) {
                indexedEvents.remove(key, calendarEvent);            	
            	removed = true;
            }
        }
        
        if(removed) {
            fireRemoveEvent(calendarEvent);        	
        }

    }
    
    @SuppressWarnings("unchecked")
    public void update(final CalendarEvent calendarEvent) {
    	
    	boolean updated = false;
    	
        for (final Object key : new HashSet<Object>(indexedEvents.keySet())) {
            final Collection<CalendarEvent> events = indexedEvents.getCollection(key);
            if (events.contains(calendarEvent))
            	updated = true;
        }

        if(updated) {
            fireUpdateEvent(calendarEvent);        	
        }

    }
    
    @Override
    public Collection<CalendarEvent> getEvents(final Date date) {
        @SuppressWarnings("rawtypes")
        final Collection events = indexedEvents.getCollection(CalendarUtil.stripTime(date));
        if (events == null)
            return new ArrayList<CalendarEvent>();
        @SuppressWarnings("unchecked")
        final List<CalendarEvent> result = new ArrayList<CalendarEvent>(events);
        Collections.sort(result);
        return result;
    }

	@Override
	public Collection<CalendarEvent> getEvents(Date startDate, Date endDate) {
		
        final Collection<Date> dates = CalendarUtil.getDates(startDate, endDate);
        final List<CalendarEvent> result = new ArrayList<CalendarEvent>();
        
        for (Date date : dates) {
            final Collection<CalendarEvent> events = indexedEvents.getCollection(date);
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

package it.spaghettisource.certgen.ui.swing.component.calendar.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.IntervalChangedEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.IntervalChangedListener;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.ModelChangedEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.ModelChangedListener;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.SelectionChangedEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.SelectionChangedListener;

public abstract class AgendaModelAbstract implements AgendaModel {

    private final List<ModelChangedListener> collectionChangedListeners;
    private final List<SelectionChangedListener> selectionChangedListeners;
    private final List<IntervalChangedListener> intervalChangedListener;    
	
    public JCalendar parent;
    
    protected CalendarEvent selectedEvent;
    
    /**
     * Creates a new instance of {@link AgendaModelMemory}
     */
    public AgendaModelAbstract() {
        this.collectionChangedListeners = new ArrayList<ModelChangedListener>();
        this.selectionChangedListeners = new ArrayList<SelectionChangedListener>();
        this.intervalChangedListener = new ArrayList<IntervalChangedListener>();
        this.selectedEvent = null;
    }
    
	public void setParent(JCalendar parent) {
		this.parent = parent;
	}
	
	@Override
	public void intervalChange(Date intervalStart, Date intervalEnd) {
	
		fireIntervalChangeEvent(intervalStart, intervalEnd);
	}
	
	@Override
    public void addSelected(final CalendarEvent selected) {
    	if(selectedEvent !=null && selectedEvent.equals(selected)) {
    		return;
    	}
    	
    	if(selectedEvent !=null) {
        	selectedEvent.setSelected(false);    		
    	}
    	
    	selectedEvent = selected;
    	fireChangeSelectionEvent(selectedEvent);
    }
    
    @Override
    public CalendarEvent getSelected() {
        return selectedEvent;
    }

    @Override
    public void cleanSelected() {
   
    	if(selectedEvent==null) {
    		return;
    	}
    	
    	selectedEvent.setSelected(false);
    	selectedEvent = null;
    	fireCleanSelectionEvent();
    }
    
    @Override
    public void addModelChangedListener(final ModelChangedListener listener) {
        this.collectionChangedListeners.add(listener);
    }

    @Override
    public void removeModelChangedListener(final ModelChangedListener listener) {
        this.collectionChangedListeners.remove(listener);
    }

    @Override
    public void addSelectionChangedListener(final SelectionChangedListener listener) {
        this.selectionChangedListeners.add(listener);
    }

    @Override
    public void removeSelectionChangedListener(final SelectionChangedListener listener) {
        this.selectionChangedListeners.remove(listener);
    }
    
    @Override
    public void addIntervalChangedListener(final IntervalChangedListener listener) {
        this.intervalChangedListener.add(listener);
    }

    @Override
    public void removeIntervalChangedListener(final IntervalChangedListener listener) {
        this.intervalChangedListener.remove(listener);
    }    
    
    protected void fireAddEvent(CalendarEvent calendarEvent) {
    	parent.validate();
    	parent.repaint();
    	
        final ModelChangedEvent event = new ModelChangedEvent(parent, calendarEvent);
        for (final ModelChangedListener listener : collectionChangedListeners) {
            listener.eventAdded(event);
        }
    }

    protected void fireRemoveEvent(CalendarEvent calendarEvent) {
    	parent.validate();
    	parent.repaint();
    	
        final ModelChangedEvent event = new ModelChangedEvent(parent, calendarEvent);
        for (final ModelChangedListener listener : collectionChangedListeners) {
            listener.eventRemoved(event);
        }
    }
    
    protected void fireUpdateEvent(CalendarEvent calendarEvent) {
        parent.invalidate();
        parent.repaint();
    	
        final ModelChangedEvent event = new ModelChangedEvent(parent, calendarEvent);
        for (final ModelChangedListener listener : collectionChangedListeners) {
            listener.eventUpdate(event);
        }
    }
    
    protected void fireChangeSelectionEvent(CalendarEvent calendarEvent) {
        parent.invalidate();
        parent.repaint();
    	
        final SelectionChangedEvent event = new SelectionChangedEvent(calendarEvent);
        for (final SelectionChangedListener listener : selectionChangedListeners) {
            listener.selectionChanged(event);
        }
    }
    
    protected void fireCleanSelectionEvent() {
        parent.invalidate();
        parent.repaint();
    	
        for (final SelectionChangedListener listener : selectionChangedListeners) {
            listener.selectionClean();
        }
    }
    
    protected void fireIntervalChangeEvent(Date intervalStart,Date intervalEnd) {
        parent.invalidate();
        parent.repaint();
    	
        final IntervalChangedEvent event = new IntervalChangedEvent(parent, intervalStart, intervalEnd);
        for (final IntervalChangedListener listener : intervalChangedListener) {
            listener.intervalChanged(event);
        }
    }
    

}

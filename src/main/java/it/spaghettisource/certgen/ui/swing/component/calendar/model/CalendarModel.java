package it.spaghettisource.certgen.ui.swing.component.calendar.model;

import java.util.Collection;
import java.util.Date;

import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.IntervalChangedListener;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.ModelChangedListener;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.SelectionChangedListener;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public interface CalendarModel {

	public void setParent(JCalendar parent);
	
	public void intervalChange(Date intervalStart,Date intervalEnd);
	
	public void add(CalendarEvent event);

	public void remove(CalendarEvent event);
	
	public void update(CalendarEvent event);	

	public void addSelected(Date exactDate,CalendarEvent event);
	
	public void cleanSelected(Date exactDate);
	
	public CalendarEvent getSelected();

	
	public Collection<CalendarEvent> getEvents(Date date);
	
	public Collection<CalendarEvent> getEvents(Date startDate, Date endDate);

	public void addModelChangedListener(ModelChangedListener listener);

	public void removeModelChangedListener(ModelChangedListener listener);

	public void addSelectionChangedListener(SelectionChangedListener listener);

	public void removeSelectionChangedListener(SelectionChangedListener listener);
	
    public void addIntervalChangedListener(IntervalChangedListener listener);

    public void removeIntervalChangedListener(IntervalChangedListener listener);
    
}

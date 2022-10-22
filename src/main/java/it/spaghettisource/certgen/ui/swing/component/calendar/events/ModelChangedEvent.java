package it.spaghettisource.certgen.ui.swing.component.calendar.events;

import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;

/**
 * 
 * @author Alessandro D'Ottavio
 */
public class ModelChangedEvent {

	private JCalendar source;
	private CalendarEvent calendarEvent;

	/**
	 * Creates a new instance of {@link ModelChangedEvent}
	 */
	public ModelChangedEvent() {
	}

	/**
	 * Creates a new instance of {@link ModelChangedEvent}
	 * 
	 * @param source
	 * @param calendarEvent
	 */
	public ModelChangedEvent(JCalendar source, CalendarEvent calendarEvent) {
		super();
		this.source = source;
		this.calendarEvent = calendarEvent;
	}

	/**
	 * gets the source
	 * 
	 * @return
	 */
	public JCalendar getSource() {
		return source;
	}

	/**
	 * sets the source
	 * 
	 * @param source
	 */
	public void setSource(JCalendar source) {
		this.source = source;
	}

	/**
	 * 
	 * @return
	 */
	public CalendarEvent getCalendarEvent() {
		return calendarEvent;
	}

	/**
	 * 
	 * @param calendarEvent
	 */
	public void setCalendarEvent(CalendarEvent calendarEvent) {
		this.calendarEvent = calendarEvent;
	}
}

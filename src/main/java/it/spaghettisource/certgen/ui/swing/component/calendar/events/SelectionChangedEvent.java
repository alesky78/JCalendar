package it.spaghettisource.certgen.ui.swing.component.calendar.events;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;

public class SelectionChangedEvent {

	CalendarEvent calendarEvent;

	public SelectionChangedEvent() {

	}

	public SelectionChangedEvent(CalendarEvent calendarEvent) {
		this.calendarEvent = calendarEvent;
	}

	public CalendarEvent getCalendarEvent() {
		return calendarEvent;
	}

	public void setCalendarEvent(CalendarEvent calendarEvent) {
		this.calendarEvent = calendarEvent;
	}
}

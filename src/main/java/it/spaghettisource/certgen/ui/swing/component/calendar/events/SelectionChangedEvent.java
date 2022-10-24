package it.spaghettisource.certgen.ui.swing.component.calendar.events;

import java.util.Date;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;

public class SelectionChangedEvent {

	Date selectedDate;				//the date selected, it represent the exact date selected by the user in the UI
	CalendarEvent previousSelectedEvent;	//the previous selected event	
	CalendarEvent actualSelectedEvent;		//the actual selected event, will be null if the user didn't select an event
	
	public SelectionChangedEvent(Date selectedDate, CalendarEvent previousSelectedEvent,CalendarEvent actualSelectedEvent) {
		super();
		this.selectedDate = selectedDate;
		this.previousSelectedEvent = previousSelectedEvent;
		this.actualSelectedEvent = actualSelectedEvent;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public CalendarEvent getPreviousSelectedEvent() {
		return previousSelectedEvent;
	}

	public CalendarEvent getActualSelectedEvent() {
		return actualSelectedEvent;
	}

}

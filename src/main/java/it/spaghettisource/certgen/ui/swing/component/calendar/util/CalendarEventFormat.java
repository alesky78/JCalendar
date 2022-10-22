package it.spaghettisource.certgen.ui.swing.component.calendar.util;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;

/**
 * To provide a custom tooltip formatter implement this interfaces and use
 * the method setTooltipFormater(...) from the JCalendar class
 */
public interface CalendarEventFormat {

	public String format(CalendarEvent calendarEvent);

}

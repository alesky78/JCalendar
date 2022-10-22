package it.spaghettisource.certgen.ui.swing.component.calendar.util;

import java.text.SimpleDateFormat;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;

/**
 * The default formatter for the the tooltip text
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarEventFormatDefault implements CalendarEventFormat {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public String format(CalendarEvent calendarEvent) {
        StringBuilder formatted = new StringBuilder();
        formatted.append("<html>");
        formatted.append("<table>");

        formatted.append("<tr>");
        formatted.append("<td><b>Summary: </b></td>");
        formatted.append("<td>").append(calendarEvent.getSummary()).append("</td>");
        formatted.append("</tr>");

        if (calendarEvent.getDescription() != null) {
            formatted.append("<tr>");
            formatted.append("<td><b>Description: </b></td>");
            formatted.append("<td>").append(calendarEvent.getDescription()).append("</td>");
            formatted.append("</tr>");
        }

        if (calendarEvent.getLocation() != null) {
            formatted.append("<tr>");
            formatted.append("<td><b>Location: </b></td>");
            formatted.append("<td>").append(calendarEvent.getLocation()).append("</td>");
            formatted.append("</tr>");
        }

        formatted.append("<tr>");
        formatted.append("<td><b>Start time: </b></td>");
        formatted.append("<td>").append(sdf.format(calendarEvent.getStart())).append("</td>");
        formatted.append("</tr>");

        formatted.append("<tr>");
        formatted.append("<td><b>End time: </b></td>");
        formatted.append("<td>").append(sdf.format(calendarEvent.getEnd())).append("</td>");
        formatted.append("</tr>");

        formatted.append("</table>");
        formatted.append("</html>");
        return formatted.toString();
    }



}

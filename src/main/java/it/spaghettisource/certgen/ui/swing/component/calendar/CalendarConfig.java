package it.spaghettisource.certgen.ui.swing.component.calendar;

import java.awt.Color;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarConfig {

    private Color lineColor;
    private Color dayHeaderForegroundColor;
    private Color dayHeaderBackgroundColor;
    private Color todayHeaderForegroundColor;
    private Color todayHeaderBackgroundColor;
    private Color eventDefaultBackgroundColor;
    private Color eventDefaultForegroundColor;
    private Color dayDisabledBackgroundColor;
    
    public CalendarConfig() {
        lineColor = new Color(220, 220, 220);

        todayHeaderBackgroundColor = new Color(240, 230, 140, 128);
        dayHeaderBackgroundColor = new Color(173, 216, 230, 200);

        todayHeaderForegroundColor = Color.black;
        dayHeaderForegroundColor = Color.black;

        eventDefaultBackgroundColor = new Color(135, 184, 217, 128);
        eventDefaultForegroundColor = Color.DARK_GRAY;

        dayDisabledBackgroundColor = new Color(148, 197, 217, 128);
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }


    public Color getDayHeaderBackgroundColor() {
        return dayHeaderBackgroundColor;
    }

    public void setDayHeaderBackgroundColor(Color dayHeaderBackgroundColor) {
        this.dayHeaderBackgroundColor = dayHeaderBackgroundColor;
    }

    public Color getDayHeaderForegroundColor() {
        return dayHeaderForegroundColor;
    }

    public void setDayHeaderForegroundColor(Color dayHeaderForegroundColor) {
        this.dayHeaderForegroundColor = dayHeaderForegroundColor;
    }

    public Color getTodayHeaderBackgroundColor() {
        return todayHeaderBackgroundColor;
    }

    public void setTodayHeaderBackgroundColor(Color todayHeaderBackgroundColor) {
        this.todayHeaderBackgroundColor = todayHeaderBackgroundColor;
    }

    public Color getTodayHeaderForegroundColor() {
        return todayHeaderForegroundColor;
    }

    public void setTodayHeaderForegroundColor(Color todayHeaderForegroundColor) {
        this.todayHeaderForegroundColor = todayHeaderForegroundColor;
    }

    public Color getEventDefaultBackgroundColor() {
        return eventDefaultBackgroundColor;
    }

    public void setEventDefaultBackgroundColor(Color eventDefaultBackgroundColor) {
        this.eventDefaultBackgroundColor = eventDefaultBackgroundColor;
    }

    public Color getEventDefaultForegroundColor() {
        return eventDefaultForegroundColor;
    }

    public void setEventDefaultForegroundColor(Color eventDefaultForegroundColor) {
        this.eventDefaultForegroundColor = eventDefaultForegroundColor;
    }

    public Color getDayDisabledBackgroundColor() {
        return dayDisabledBackgroundColor;
    }

    public void setDayDisabledBackgroundColor(Color dayDisabledBackgroundColor) {
        this.dayDisabledBackgroundColor = dayDisabledBackgroundColor;
    }

    
}

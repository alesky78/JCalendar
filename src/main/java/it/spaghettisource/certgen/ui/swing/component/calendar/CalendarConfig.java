package it.spaghettisource.certgen.ui.swing.component.calendar;

import java.awt.Color;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarConfig {

	
    private Color lineColor;
    
    //Colors used by the header of 
    private Color defaultHeaderForegroundColor;
    private Color defaultHeaderBackgroundColor;
    private Color todayHeaderForegroundColor;
    private Color todayHeaderBackgroundColor;

    //Colors used by the events
    private Color eventDefaultBackgroundColor;
    private Color eventDefaultForegroundColor;
    
    //Colors used by the days    
    private Color dayDisabledBackgroundColor;
    private Color dayDefaultBackgroundColor;    
    
    public CalendarConfig() {
        lineColor = new Color(220, 220, 220);

        todayHeaderBackgroundColor = new Color(240, 230, 140, 128);
        //defaultHeaderBackgroundColor = new Color(173, 216, 230, 200);
        defaultHeaderBackgroundColor = new Color(173, 216, 230, 200);

        todayHeaderForegroundColor = Color.BLACK;
        defaultHeaderForegroundColor = Color.BLACK;

        eventDefaultBackgroundColor = new Color(135, 184, 217, 128);
        eventDefaultForegroundColor = Color.DARK_GRAY;

        dayDisabledBackgroundColor = new Color(148, 197, 217, 128);
        dayDefaultBackgroundColor = Color.WHITE;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

	public Color getDefaultHeaderForegroundColor() {
		return defaultHeaderForegroundColor;
	}

	public void setDefaultHeaderForegroundColor(Color defaultHeaderForegroundColor) {
		this.defaultHeaderForegroundColor = defaultHeaderForegroundColor;
	}

	public Color getDefaultHeaderBackgroundColor() {
		return defaultHeaderBackgroundColor;
	}

	public void setDefaultHeaderBackgroundColor(Color defaultHeaderBackgroundColor) {
		this.defaultHeaderBackgroundColor = defaultHeaderBackgroundColor;
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

	public Color getDayDefaultBackgroundColor() {
		return dayDefaultBackgroundColor;
	}

	public void setDayDefaultBackgroundColor(Color dayDefaultBackgroundColor) {
		this.dayDefaultBackgroundColor = dayDefaultBackgroundColor;
	}
    
}

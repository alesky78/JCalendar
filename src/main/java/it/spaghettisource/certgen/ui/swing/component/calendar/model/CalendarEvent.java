package it.spaghettisource.certgen.ui.swing.component.calendar.model;

import java.awt.Color;
import java.util.Date;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarEvent implements Comparable<CalendarEvent> {

	private Color backgroundColor;
	private Color foregroundColor;
	
    private Date start;
    private Date end;
	
    private String summary;
    private String description;
    private String location;

    private boolean selected;

    /**
     *
     */
    public CalendarEvent() {
    }

    public CalendarEvent(Date start, Date end) {
        this();
        this.start = start;
        this.end = end;
    }

    public CalendarEvent(String summary, Date start, Date end) {
        this(start, end);
        this.summary = summary;
    }
    
    public CalendarEvent(String summary, String description, Date start, Date end) {
        this(summary, start, end);
        this.description = description;
    }
    

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(final Date start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(final Date end) {
        this.end = end;
    }
    
 
    public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	/**
     * @return
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CalendarEvent other = (CalendarEvent) obj;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
    	
        return true;
    }

    @Override
    public int compareTo(final CalendarEvent o) {
        final int comp = start.compareTo(o.getStart());
        if (comp == 0)
            return end.compareTo(o.getEnd());
        return comp;
    }

    @Override
    public String toString() {
        return "CalendarEvent [start=" + start + ", end=" + end + ", summary=" + summary + "]";
    }



}

package it.spaghettisource.certgen.ui.swing.component.calendar;

import java.util.Calendar;

/**
 * store the data for the management of the calendar but are not part of the Model 
 * 
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarData {
	
	private Calendar date;			//represent the exact date for which is requested the calendar	
    private Calendar intervalStart;	//represent the first date show in the calendar based on the strategy
    private Calendar intervalEnd;	//represent the last date show in the calendar based on the strategy

    public CalendarData(Calendar date, Calendar intervalStart, Calendar intervalEnd) {
		super();
		this.date = date;
		this.intervalStart = intervalStart;
		this.intervalEnd = intervalEnd;
	}
    
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getIntervalStart() {
        return intervalStart;
    }

	public void setIntervalStart(Calendar intervalStart) {
        this.intervalStart = intervalStart;
    }

    public Calendar getIntervalEnd() {
        return intervalEnd;
    }

    public void setIntervalEnd(Calendar intervalEnd) {
        this.intervalEnd = intervalEnd;
    }
}

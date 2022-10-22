package it.spaghettisource.certgen.ui.swing.component.calendar.events;

import java.util.Date;

import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;

/**
 * 
 * @author Alessandro D'Ottavio
 */
public class IntervalChangedEvent{

	private JCalendar source;

	private Date intervalStart;

	private Date intervalEnd;

	/**
	 * Creates a new instance of {@link IntervalChangedEvent}
	 */
	public IntervalChangedEvent() {

	}

	/**
	 * Creates a new instance of {@link IntervalChangedEvent}
	 * 
	 * @param source
	 * @param selectedStrategy
	 * @param intervalStart
	 * @param intervalEnd
	 */
	public IntervalChangedEvent(final JCalendar source, final Date intervalStart,final Date intervalEnd) {
		super();
		this.source = source;
		this.intervalStart = intervalStart;
		this.intervalEnd = intervalEnd;
	}

	/**
	 * @return the source
	 */
	public JCalendar getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(final JCalendar source) {
		this.source = source;
	}


	/**
	 * @return the intervalStart
	 */
	public Date getIntervalStart() {
		return intervalStart;
	}

	/**
	 * @param intervalStart
	 *            the intervalStart to set
	 */
	public void setIntervalStart(final Date intervalStart) {
		this.intervalStart = intervalStart;
	}

	/**
	 * @return the intervalEnd
	 */
	public Date getIntervalEnd() {
		return intervalEnd;
	}

	/**
	 * @param intervalEnd
	 *            the intervalEnd to set
	 */
	public void setIntervalEnd(final Date intervalEnd) {
		this.intervalEnd = intervalEnd;
	}

}

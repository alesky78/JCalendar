package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarState;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/** 
 * this class is the layout manager used by the Month strategy, it represent a full week in the grid of the calendar
 * 
 * 
 * @author Alessandro D'Ottavio
 */
public class MonthLayoutManager {


	private Date startRange;	//first date of the week
	private Date endRange;		//last date of the week	
	private int managedDays;	//amount of days to show in the UI per this week
	
	private final JCalendar owner;
	private final CalendarState state;
	
	private final MonthHeaderPanel headerPanel;
	private final MonthContentPanel contentPanel;
	private float headerRatio = 0.0f;
	private int firstDayOfWeek = 0;


	/**
	 *Creates a new instance of {@link MonthLayoutManager}
	 * 
	 * @param day
	 * @param headerRatio
	 */
	public MonthLayoutManager(final JCalendar owner, CalendarState state, final Date startRange, final float headerRatio,int firstDayOfWeek, int managedDays) {
		
		this.owner = owner;
		this.state = state;
		this.firstDayOfWeek = firstDayOfWeek;
		this.headerRatio = headerRatio;
		this.managedDays = managedDays;
		this.startRange = startRange;
		
		//fix: all this objet required that managedDays is already populated 
		this.headerPanel = new MonthHeaderPanel(this);
		this.contentPanel = new MonthContentPanel(this);
		calculateEndDate();

	}

	public JPanel layout() {
		JPanel panel = new JPanel(true);
		panel.setOpaque(false);
		panel.setLayout(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = headerRatio;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		panel.add(headerPanel, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0 - headerRatio;
		panel.add(contentPanel, c);

		return panel;
	}

	public JCalendar getOwner() {
		return owner;
	}
	
	/**
	 * verify if a date is part of the manager month
	 * 
	 * @param toCheck
	 * @return
	 */
	public boolean isDateInManagedMonth(Date toCheck) {
		return CalendarUtil.isSameMonth(state.getDate(), CalendarUtil.getCalendar(toCheck, false) );
	}

	protected void calculateEndDate() {
		Calendar temp = CalendarUtil.getCalendar(startRange, true);
		temp.add(Calendar.DAY_OF_MONTH, managedDays);
		this.endRange = temp.getTime();
	}

	/**
	 * @param date the date to set
	 */
	public void setStartRange(final Date startRange) {
		this.startRange = startRange;
		calculateEndDate();

	}
	
	public Date getStartRange() {
		return startRange;
	}

	public Date getEndRange() {
		return endRange;
	}

	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	public int getManagedDays() {
		return managedDays;
	}
	
}

package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

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
	
	private final MonthHeaderPanel headerPanel;
	private final MonthContentPanel contentPanel;
	private final JCalendar owner;
	private float headerRatio = 0.0f;
	private int firstDayOfWeek = 0;

	/**
	 * Creates a new instance of {@link MonthLayoutManager}
	 * 
	 * @param startRange
	 * @param headerRatio
	 */
	public MonthLayoutManager(final JCalendar owner, final Date startRange,int firstDayOfWeek) {

		setStartRange(startRange);
		
		this.owner = owner;
		this.headerPanel = new MonthHeaderPanel(this);
		this.contentPanel = new MonthContentPanel(this);
		this.firstDayOfWeek = firstDayOfWeek;
		
	}

	/**
	 * 
	 * @param day
	 * @param headerRatio
	 */
	public MonthLayoutManager(final JCalendar owner, final Date day, final float headerRatio,int firstDayOfWeek) {

		this(owner, day, firstDayOfWeek);
		this.headerRatio = headerRatio;

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
	 * @param date
	 *            the date to set
	 */
	public void setStartRange(final Date startRange) {
		this.startRange = startRange;
		Calendar temp = CalendarUtil.getCalendar(startRange, true);
		temp.add(Calendar.DAY_OF_MONTH, 6);
		this.endRange = temp.getTime();

	}
	
	public Date getStartRange() {
		return startRange;
	}

	public Date getEndRange() {
		return endRange;
	}

	public void setEnabled(final boolean enabled) {
		this.contentPanel.setEnabled(enabled);
	}

	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

}

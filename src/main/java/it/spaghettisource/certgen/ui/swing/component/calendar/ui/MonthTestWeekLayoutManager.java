package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;

/** 
 * this class is the layout manager used by the Month strategy, it represent a single day in the grid of the calendar
 * 
 * 
 * @author Alessandro D'Ottavio
 */
public class MonthTestWeekLayoutManager {

	private final SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM");
	
	private Date day;	//day of this cell
	
	private final MonthTestWeekHeaderPanel headerPanel;
	private final MonthTestWeekContentPanel contentPanel;
	private final JCalendar owner;
	private float headerRatio = 0.0f;
	private int firstDayOfWeek = 0;

	/**
	 * Creates a new instance of {@link MonthTestWeekLayoutManager}
	 * 
	 * @param day
	 * @param headerRatio
	 */
	public MonthTestWeekLayoutManager(final JCalendar owner, final Date day,int firstDayOfWeek) {

		this.day = day;
		this.owner = owner;
		this.headerPanel = new MonthTestWeekHeaderPanel(this, sdf.format(day));
		this.contentPanel = new MonthTestWeekContentPanel(this);
		this.firstDayOfWeek = firstDayOfWeek;

	}

	/**
	 * 
	 * @param day
	 * @param headerRatio
	 */
	public MonthTestWeekLayoutManager(final JCalendar owner, final Date day, final float headerRatio,int firstDayOfWeek) {

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
	 * @return the date
	 */
	public Date getDate() {
		return day;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(final Date date) {
		this.day = date;
		headerPanel.setHeaderText(sdf.format(date));
	}

	public void setEnabled(final boolean enabled) {
		this.contentPanel.setEnabled(enabled);
	}

	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

}

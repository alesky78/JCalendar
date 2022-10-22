package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy.DisplayStrategy;

/**
 * Main Content panel of the calendar
 * This object delegate its filling to the {@link DisplayStrategy} class
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarContentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CalendarContentPanel() {
		super(true);
		setOpaque(false);
		
	}


}

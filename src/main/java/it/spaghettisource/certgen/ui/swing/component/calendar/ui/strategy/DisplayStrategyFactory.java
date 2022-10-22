package it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarData;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarContentPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarHeaderPanel;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class DisplayStrategyFactory {
	
	public static DisplayStrategy buildStrategyByType(final JCalendar calendar, final CalendarContentPanel contentPane, CalendarHeaderPanel headerPane, final DisplayStrategyType type, CalendarData data) {

		DisplayStrategy strategy;
		
		switch (type) {
			case MONTH:
				strategy = new DisplayStrategyMonth(calendar,contentPane,headerPane,data);
				strategy.init();
				return strategy;
	
			default:
				throw new IllegalArgumentException("Unknown type " + type.toString());
			}
		
	}
	
}

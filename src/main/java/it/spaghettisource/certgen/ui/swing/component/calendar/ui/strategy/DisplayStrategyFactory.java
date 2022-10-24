package it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarState;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarContentPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarHeaderPanel;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class DisplayStrategyFactory {
	
	public static DisplayStrategy buildStrategyByType(final JCalendar calendar, final CalendarContentPanel contentPane, CalendarHeaderPanel headerPane, final DisplayStrategyType type, CalendarState state) {

		DisplayStrategy strategy;
		
		switch (type) {
			case MONTH:
				strategy = new DisplayStrategyMonth(calendar,contentPane,headerPane,state);
				strategy.init();
				return strategy;
	
			default:
				throw new IllegalArgumentException("Unknown type " + type.toString());
			}
		
	}
	
}

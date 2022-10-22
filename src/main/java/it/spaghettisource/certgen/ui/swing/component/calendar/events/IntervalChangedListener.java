package it.spaghettisource.certgen.ui.swing.component.calendar.events;

import java.util.EventListener;

/**
 * 
 * @author Alessandro D'Ottavio
 */
public interface IntervalChangedListener extends EventListener {

	public void intervalChanged(IntervalChangedEvent event);
}

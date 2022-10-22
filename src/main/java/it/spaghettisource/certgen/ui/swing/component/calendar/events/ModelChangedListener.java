package it.spaghettisource.certgen.ui.swing.component.calendar.events;

import java.util.EventListener;

/**
 * 
 * @author Alessandro D'Ottavio
 */
public interface ModelChangedListener extends EventListener {

	public void eventAdded(ModelChangedEvent event);

	public void eventRemoved(ModelChangedEvent event);

	public void eventUpdate(ModelChangedEvent event);
}

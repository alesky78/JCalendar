package it.spaghettisource.certgen.ui.swing.component.calendar.events;

import java.util.EventListener;

public interface SelectionChangedListener extends EventListener {

	public void selectionChanged(SelectionChangedEvent event);
	
	public void selectionClean();
}

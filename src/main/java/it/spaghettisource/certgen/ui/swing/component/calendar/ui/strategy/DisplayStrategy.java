package it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy;

import java.util.Date;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public interface DisplayStrategy {


	public DisplayStrategyType getType();

	public void init();
	
	public void displayContentPanel();
	
	public void displayHeaderPanel();	

	public void moveIntervalLeft();

	public void moveIntervalRight();

	void changeIntervalStart(Date date);
}

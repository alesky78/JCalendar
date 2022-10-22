package it.spaghettisource.certgen.ui.swing.component.calendar.model;

import java.awt.Color;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class EventType {

	private Color backgroundColor;
	private Color foregroundColor;

	/**
	 * 
	 */
	public EventType() {

	}


	/**
	 * @return the color
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setBackgroundColor(final Color color) {
		this.backgroundColor = color;
	}

	/**
	 * 
	 * @return
	 */
	public Color getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * 
	 * @param foregroundColor
	 */
	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}


}

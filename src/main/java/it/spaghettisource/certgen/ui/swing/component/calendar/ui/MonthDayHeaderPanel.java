package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarConfig;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
@SuppressWarnings("serial")
public class MonthDayHeaderPanel extends JPanel {

	private String headerText;
	private final MonthDayLayoutManager layoutManager;

	/**
	 * Creates a new instance of {@link MonthDayHeaderPanel}
	 * 
	 * @param headerText
	 */
	public MonthDayHeaderPanel(final MonthDayLayoutManager owner, final String headerText) {
		super(true);
		setOpaque(false);
		this.headerText = headerText;
		this.layoutManager = owner;
	}

	/**
	 * returns the owner
	 * 
	 * @return
	 */
	public MonthDayLayoutManager getOwner() {
		return layoutManager;
	}

	@Override
	public void paint(final Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		final int height = getHeight();
		final int width = getWidth();

		JCalendar calendar = layoutManager.getOwner();
		CalendarConfig config = calendar.getConfig();

		final boolean isToday = CalendarUtil.isToday(layoutManager.getDate());
		final Color bgColor = isToday ? config.getTodayHeaderBackgroundColor() : config.getDayHeaderBackgroundColor();
		final Color fgColor = isToday ? config.getTodayHeaderForegroundColor() : config.getDayHeaderForegroundColor();

		
		g2d.setColor(bgColor);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setColor(fgColor);
		int fontSize = Math.round(height * 0.5f);
		fontSize = fontSize > 12 ? 12 : fontSize;

		final Font font = new Font("Verdana", Font.PLAIN, fontSize);
		final FontMetrics metrics = g2d.getFontMetrics(font);
		g2d.setFont(font);

		g2d.drawString(headerText, 5, height / 2 + metrics.getHeight() / 2);
	}

	/**
	 * @return the headerText
	 */
	public String getHeaderText() {
		return headerText;
	}

	/**
	 * @param headerText
	 *            the headerText to set
	 */
	public void setHeaderText(final String headerText) {
		this.headerText = headerText;
	}
}

package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarConfig;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
@SuppressWarnings("serial")
public class MonthHeaderPanel extends JPanel {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("EEE dd MMM");
	
	private final MonthLayoutManager layoutManager;

	/**
	 * Creates a new instance of {@link MonthHeaderPanel}
	 * 
	 * @param headerText
	 */
	public MonthHeaderPanel(final MonthLayoutManager owner) {
		super(true);
		setOpaque(false);
		this.layoutManager = owner;
	}

	/**
	 * returns the owner
	 * 
	 * @return
	 */
	public MonthLayoutManager getOwner() {
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
		
		//create the range of dates
		ArrayList<Date> list = CalendarUtil.getDatesSort(layoutManager.getStartRange(), layoutManager.getEndRange());
		
		final int dayWidth = width/7;
		int x = 0;		
		for (Date actualDate : list) {
			
			final boolean isToday = CalendarUtil.isToday(actualDate);
			final Color bgColor = isToday ? config.getTodayHeaderBackgroundColor() : config.getDefaultHeaderBackgroundColor();
			final Color fgColor = isToday ? config.getTodayHeaderForegroundColor() : config.getDefaultHeaderForegroundColor();

			
			g2d.setColor(bgColor);
			g2d.fillRect(x, 0, dayWidth, height);
			
			g2d.setColor(fgColor);
			int fontSize = Math.round(height * 0.5f);
			fontSize = fontSize > 12 ? 12 : fontSize;

			final Font font = new Font("Verdana", Font.PLAIN, fontSize);
			final FontMetrics metrics = g2d.getFontMetrics(font);
			g2d.setFont(font);

			g2d.drawString(generateHeaderText(actualDate), x + 5, height / 2 + metrics.getHeight() / 2);	
			
			x = x + dayWidth;
			
		}

	}
	
	private String generateHeaderText(Date date) {
		return SDF.format(date);
	}

}

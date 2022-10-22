package it.spaghettisource.certgen.ui.swing.component.calendar.util;

import java.awt.FontMetrics;
import java.awt.Graphics;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class GraphicsUtil {
	/**
	 * 
	 * @param g
	 * @param s
	 * @param x
	 * @param y
	 * @param width
	 */
	public static void drawString(Graphics g, String s, int x, int y, int width, int height) {
		// FontMetrics gives us information about the width,
		// height, etc. of the current Graphics object's Font.
		FontMetrics fm = g.getFontMetrics();

		int lineHeight = fm.getHeight();
		int textHeight = 0;
		int curX = x;
		int curY = y;

		String[] words = s.split(" ");

		for (String word : words) {
			// Find out thw width of the word.
			int wordWidth = fm.stringWidth(word + " ");

			// If text exceeds the width, then move to next line.
			if (curX + wordWidth >= x + width) {
				curY += lineHeight;
				textHeight += lineHeight;
				curX = x;
			}
			int charIdx = word.length();
			boolean textTrimmed = false;
			while (charIdx > 0 && wordWidth >= width) {
				charIdx -= 1;
				word = word.substring(0, charIdx);
				wordWidth = fm.stringWidth(word + " ");
				textTrimmed = true;
			}
			word = word.length() > 3 && textTrimmed ? word.substring(0, word.length() - 3) + "..."
					: word.length() > 2 ? word : "";

			if (textHeight + lineHeight > height) {
				g.drawString("...", curX, curY);
				break;
			}
			g.drawString(word, curX, curY);

			// Move over to the right for next word.
			curX += wordWidth;
		}
	}

	/**
	 * 
	 * @param g
	 * @param s
	 * @param x
	 * @param y
	 * @param width
	 */
	public static void drawTrimmedString(Graphics g, String s, int x, int y, int width) {
		// FontMetrics gives us information about the width,
		// height, etc. of the current Graphics object's Font.

        if(s == null)
            s = "" + s;

		FontMetrics fm = g.getFontMetrics();

		int wordWidth = fm.stringWidth(s + " ");

		int charIdx = s.length();
		boolean textTrimmed = false;
		while (charIdx > 0 && wordWidth >= width) {
			charIdx -= 1;
			s = s.substring(0, charIdx);
			wordWidth = fm.stringWidth(s + " ");
			textTrimmed = true;
		}
		s = s.length() > 3 && textTrimmed ? s.substring(0, s.length() - 3) + "..." : s.length() > 2 ? s : "";

		g.drawString(s, x, y);

	}
}

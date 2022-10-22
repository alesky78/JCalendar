package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarConfig;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.AgendaModel;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.GraphicsUtil;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
@SuppressWarnings("serial")
public class MonthDayContentPanel extends JPanel {
    
    private final MonthDayLayoutManager layoutManager;
    
    /**
     * Creates a new instance of {@link MonthDayContentPanel}
     */
    public MonthDayContentPanel(final MonthDayLayoutManager owner) {
        super(true);
        setOpaque(false);
        this.layoutManager = owner;
        addEventSelectedListeners();
        addToolTipListner();
    }
    

    public MonthDayLayoutManager getOwner() {
        return layoutManager;
    }
    
    /**
     * responsible to manage the click event over a calendar event and trigger the selection
     * 
     */
    private void addEventSelectedListeners() {

        addMouseListener(new MouseAdapter() {

            final JCalendar calendar = layoutManager.getOwner();

            @Override
            public void mousePressed(final MouseEvent e) {

                final CalendarEvent event = getEventFromrUI(e.getX(), e.getY());

                if (e.getClickCount() == 1) {

                    final AgendaModel events = calendar.getModel();
                    
                    if (event != null) {
                        event.setSelected(true);
                        events.addSelected(event);
                    }else {
                    	events.cleanSelected();
                    }

                }
            }
        });
    }
    
    /**
     * responsible to create a tooltip message when the mouse moved over an event
     * 
     */
    private void addToolTipListner() {
    	
        addMouseMotionListener(new MouseAdapter() {

            final JCalendar calendar = MonthDayContentPanel.this.layoutManager.getOwner();

            @Override
            public void mouseMoved(final MouseEvent e) {
                super.mouseMoved(e);

                final CalendarEvent event = getEventFromrUI(e.getX(), e.getY());
                if (event != null) {
                    setToolTipText(calendar.getTooltipFormater().format(event));
                }

            }
        });        
    }

    
    private CalendarEvent getEventFromrUI(final int x, final int y) {
    	
        final Collection<CalendarEvent> events = layoutManager.getOwner().getModel().getEvents(layoutManager.getDate());

        int pos = 2;
        if (events.size() > 0) {
            for (final CalendarEvent event : events) {

                final int rectXStart = 2;
                final int rectYStart = pos;

                final int rectWidth = getWidth() - 4;

                final int rectHeight = 15;

                final Rectangle r = new Rectangle(rectXStart, rectYStart,rectWidth, rectHeight);
                if (r.contains(x, y)) {
                    return event;
                }

                pos += 17;

            }
        }
        return null;
    }
    
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        drawBackground((Graphics2D) g);
        drawCalendarEvents((Graphics2D) g);
    }

    
    private void drawBackground(final Graphics2D graphics2d) {
        final int height = getHeight();
        final int width = getWidth();
        final JCalendar calendar = layoutManager.getOwner();
        final CalendarConfig config = calendar.getConfig();
        final Color dayDisableBackgroundColor = config.getDayDisabledBackgroundColor();

        if (!isEnabled()) {
            graphics2d.setColor(dayDisableBackgroundColor);
            graphics2d.fillRect(0, 0, width, height);
        }

        graphics2d.setColor(config.getLineColor());
        graphics2d.drawRect(0, 0, width, height);

    }

    private void drawCalendarEvents(final Graphics2D graphics2d) {

        final Collection<CalendarEvent> events = layoutManager.getOwner().getModel().getEvents(layoutManager.getDate());
        int pos = 2;
        if (events.size() > 0) {
            final CalendarConfig config = layoutManager.getOwner().getConfig();
            for (final CalendarEvent event : events) {
                
                Color bgColor = event.getType().getBackgroundColor();
                bgColor = bgColor == null ? config.getEventDefaultBackgroundColor() : bgColor;
                Color fgColor = event.getType().getForegroundColor();
                fgColor = fgColor == null ? config.getEventDefaultForegroundColor() : fgColor;
                graphics2d.setColor(!event.isSelected() ? bgColor : bgColor.darker().darker());
                graphics2d.fillRect(2, pos, getWidth() - 4, 15);

                final String eventString = event.getSummary();
                int fontSize = Math.round(getHeight() * 0.5f);
                fontSize = fontSize > 9 ? 9 : fontSize;

                final Font font = new Font("Verdana", Font.BOLD, fontSize);
                final FontMetrics metrics = graphics2d.getFontMetrics(font);
                graphics2d.setFont(font);

                graphics2d.setColor(!event.isSelected() ? fgColor : Color.white);
                GraphicsUtil.drawTrimmedString(graphics2d, eventString, 6, pos+ (13 / 2 + metrics.getHeight() / 2) - 2, getWidth());

                pos += 17;
            }
        }
    }



}

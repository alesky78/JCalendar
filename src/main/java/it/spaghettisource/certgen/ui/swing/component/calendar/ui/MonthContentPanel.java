package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarConfig;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.AgendaModel;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.GraphicsUtil;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.EventGrid;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
@SuppressWarnings("serial")
public class MonthContentPanel extends JPanel {
    
    private final MonthLayoutManager layoutManager;
    private final EventGrid grid;
    
    /**
     * Creates a new instance of {@link MonthContentPanel}
     */
    public MonthContentPanel(final MonthLayoutManager owner) {
        super(true);
        setOpaque(false);
        this.layoutManager = owner;
        grid = new EventGrid();
        addEventSelectedListeners();
        addToolTipListner();
    }
    

    public MonthLayoutManager getOwner() {
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

            final JCalendar calendar = MonthContentPanel.this.layoutManager.getOwner();

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
    	int xPosition = x/(getWidth()/7);
    	
    	if(xPosition>6) {
    		//we are in the border of the panel
    		return null;
    	}
    	
    	int yPosition = (y + 2)/17; 
    	CalendarEvent event = grid.findEventAtPosition(xPosition,yPosition);
    	return event;

    	
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
        final Color dayDefaultBackgroundColor = config.getDayDefaultBackgroundColor();

		final int dayWidth = width/7;
		int x = 0;	
		
		for (int i = 0; i < 7; i++) {
	        if (!isEnabled()) {
	            graphics2d.setColor(dayDisableBackgroundColor);
	            graphics2d.fillRect(x, 0, dayWidth, height);
	        }else {
	        	graphics2d.setColor(dayDefaultBackgroundColor);
	            graphics2d.fillRect(x, 0, dayWidth, height);
	        }

	        graphics2d.setColor(config.getLineColor());
	        graphics2d.drawRect(x, 0, dayWidth, height);
			
			x = x + dayWidth;
		}
        
    }

    
    
    private void drawCalendarEvents(final Graphics2D graphics2d) {

    	AgendaModel model = layoutManager.getOwner().getModel();

		//create the range of dates
		ArrayList<Date> list = CalendarUtil.getDatesSort(layoutManager.getStartRange(), layoutManager.getEndRange());
		
		//populate the week greed
	    final Collection<CalendarEvent> allEvents = model.getEvents(layoutManager.getStartRange(), layoutManager.getEndRange());
        grid.populate(allEvents, layoutManager.getStartRange());
    	        
        final CalendarConfig config = layoutManager.getOwner().getConfig();
                
		final int dayWidth = getWidth()/7;
        int y = 2;
        int x = 0;        
        int offset = 0;
        
        for (Date actualDate : list) {
        	List<CalendarEvent> actualEvents = grid.findEventStartingAtDate(actualDate);
            
            for (final CalendarEvent event : actualEvents) {
                
            	offset = grid.findPosition(event, actualDate);
            	y = 2 + offset*17;
            	
                Color bgColor = event.getBackgroundColor();
                bgColor = bgColor == null ? config.getEventDefaultBackgroundColor() : bgColor;
                Color fgColor = event.getForegroundColor();
                fgColor = fgColor == null ? config.getEventDefaultForegroundColor() : fgColor;
                graphics2d.setColor(!event.isSelected() ? bgColor : bgColor.darker().darker());
                
                graphics2d.fillRoundRect(x+2, y, eventWidth(event,dayWidth)-2, 15, 10, 10);

                final String eventString = event.getSummary();
                int fontSize = Math.round(getHeight() * 0.5f);
                fontSize = fontSize > 9 ? 9 : fontSize;

                final Font font = new Font("Verdana", Font.BOLD, fontSize);
                final FontMetrics metrics = graphics2d.getFontMetrics(font);
                graphics2d.setFont(font);

                graphics2d.setColor(!event.isSelected() ? fgColor : Color.white);
                GraphicsUtil.drawTrimmedString(graphics2d, eventString, x + 6, y+ (13 / 2 + metrics.getHeight() / 2) - 2, getWidth());

            }
            
            x = x + dayWidth;

        	
		}
        
    }
    
    private int eventWidth(CalendarEvent event,int dayWidth) {

    	Date start = event.getStart().before(layoutManager.getStartRange()) ? layoutManager.getStartRange() :  event.getStart();    	
    	Date end = event.getEnd().after(layoutManager.getEndRange()) ? layoutManager.getEndRange() :  event.getEnd();
    	
    	return (dayWidth * CalendarUtil.amountOfDays(start, end));
    }



}

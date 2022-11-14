package it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarState;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarContentPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarHeaderPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.MonthLayoutManager;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
class DisplayStrategyMonth implements DisplayStrategy {
	
	private final JCalendar calendar;
    private final CalendarContentPanel contentPane;  
    private final CalendarHeaderPanel headerPane;
    
    private final CalendarState state;    
  
	
    private final int WEEKS_PER_MONTH = 6;	//Amount of week to show
	private final int FIRST_DAY_WEEK = 2;	//Calendar.MONDAY to use has first day of the week
	private final int MANAGED_DAYS = 7;		//managed days per week, this value represent the amount of days show in the UI so this value cannot be more then 7
	private final int WEEK_DAYS = 7;		//number of days per week	
	
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");

    private JPanel displayPanel;	//panel whit all the days cell
    private final MonthLayoutManager[] days = new MonthLayoutManager[6];	//days cell
    

    public DisplayStrategyMonth(final JCalendar calendar, final CalendarContentPanel contentPane, final CalendarHeaderPanel headerPane, final CalendarState state) {
    	this.calendar = calendar;
    	this.headerPane = headerPane;
        this.contentPane = contentPane;
        this.state = state;
    }

    @Override
    public DisplayStrategyType getType() {
        return DisplayStrategyType.MONTH;
    }
    
    @Override
    public void init() {

    	Calendar start = CalendarUtil.copyCalendar(state.getDate(), true);
        start.set(Calendar.DAY_OF_MONTH, 1);

        displayPanel = new JPanel(true);
        displayPanel.setOpaque(false);
        displayPanel.setLayout(new GridLayout(WEEKS_PER_MONTH, 1));
        final Calendar c = CalendarUtil.copyCalendar(start, true);
        c.set(Calendar.DAY_OF_WEEK, FIRST_DAY_WEEK);
        
        state.setIntervalStart(CalendarUtil.copyCalendar(c, true));
        
        for (int i = 0; i < WEEKS_PER_MONTH; i++) {
            days[i] = new MonthLayoutManager(calendar, state, c.getTime(), 0.1f,FIRST_DAY_WEEK,MANAGED_DAYS);
            displayPanel.add(days[i].layout());
            c.add(Calendar.DATE, WEEK_DAYS);
        }
       
        Calendar endInterval = CalendarUtil.copyCalendar(c, true);
        endInterval.add(Calendar.DAY_OF_MONTH, -1);
        
        state.setIntervalEnd(CalendarUtil.copyCalendar(endInterval, true));
        
        calendar.getModel().intervalChange(state.getIntervalStart().getTime(), state.getIntervalEnd().getTime());

    }

    @Override
    public void displayContentPanel() {
        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(displayPanel, BorderLayout.CENTER);
        contentPane.validate();
        contentPane.repaint();
    }
    
	@Override
	public void displayHeaderPanel() {
        changeHeaderInterval();
	}

	
    @Override
    public void moveIntervalLeft() {
        Calendar start = CalendarUtil.copyCalendar(state.getDate(), true);

        start.add(Calendar.MONTH, -1);
        start.set(Calendar.DAY_OF_MONTH, 1);

        Calendar end = CalendarUtil.getCalendar(start.getTime(), true);
        end.add(Calendar.MONTH, 1);

        moveInterval(start, end);
    }


    @Override
    public void moveIntervalRight() {
        Calendar start = CalendarUtil.copyCalendar(state.getDate(), true);

        start.add(Calendar.MONTH, 1);
        start.set(Calendar.DAY_OF_MONTH, 1);

        Calendar end = CalendarUtil.getCalendar(start.getTime(), true);
        end.add(Calendar.MONTH, 1);

        moveInterval(start, end);
    }


    @Override
    public void changeIntervalStart(Date date) {
        Calendar start = CalendarUtil.getCalendar(date, true);
        start.set(Calendar.DAY_OF_MONTH, 1);

        Calendar end = CalendarUtil.getCalendar(start.getTime(), true);
        end.add(Calendar.MONTH, 1);

        moveInterval(start, end);
    }
    

	private void moveInterval(Calendar start, Calendar end) {
		
		state.setDate(CalendarUtil.copyCalendar(start, true));

        Calendar c = CalendarUtil.copyCalendar(start, true);
        c.set(Calendar.DAY_OF_WEEK,FIRST_DAY_WEEK);
        
        state.setIntervalStart(CalendarUtil.copyCalendar(c, true));
        
        for (int i = 0; i < WEEKS_PER_MONTH; i++) {
            days[i].setStartRange(c.getTime());
            c.add(Calendar.DAY_OF_MONTH, 7);
        }
        
        Calendar endInterval = CalendarUtil.copyCalendar(c, true);
        endInterval.add(Calendar.DAY_OF_MONTH, -1);
        
        state.setIntervalEnd(CalendarUtil.copyCalendar(endInterval, true));

        changeHeaderInterval();
        
        calendar.getModel().intervalChange(state.getIntervalStart().getTime(), state.getIntervalEnd().getTime());
        
	}
	
	private void changeHeaderInterval() {
		Calendar c = CalendarUtil.copyCalendar(state.getDate(), true);
        String interval = sdf.format(c.getTime());
        headerPane.getIntervalLabel().setText(interval);
	}


}

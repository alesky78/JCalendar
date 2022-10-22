package it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.CalendarData;
import it.spaghettisource.certgen.ui.swing.component.calendar.JCalendar;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarContentPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarHeaderPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.TestMonthDayLayoutManager;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
class TestDisplayStrategyMonth implements DisplayStrategy {
	
	private final JCalendar calendar;
    private final CalendarContentPanel contentPane;  
    private final CalendarHeaderPanel headerPane;
    
    private final CalendarData data;    
  
	
    private final int gridRows = 6;
    private final int gridColumns = 7;    
	private final int daysPerMonth = gridRows*gridColumns;	//number of cell in the grid of the agenda
	private final int firstDayOfWeek = 2;					//Calendar.MONDAY to use has first day of the week

    private final SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");

    private JPanel displayPanel;	//panel whit all the days cell
    private final TestMonthDayLayoutManager[] days = new TestMonthDayLayoutManager[daysPerMonth];	//day cell
    

    public TestDisplayStrategyMonth(final JCalendar calendar, final CalendarContentPanel contentPane, final CalendarHeaderPanel headerPane, final CalendarData data) {
    	this.calendar = calendar;
    	this.headerPane = headerPane;
        this.contentPane = contentPane;
        this.data = data;
    }

    @Override
    public DisplayStrategyType getType() {
        return DisplayStrategyType.TEST;
    }
    
    @Override
    public void init() {

    	Calendar start = CalendarUtil.copyCalendar(data.getDate(), true);
        start.set(Calendar.DAY_OF_MONTH, 1);

        displayPanel = new JPanel(true);
        displayPanel.setOpaque(false);
        displayPanel.setLayout(new GridLayout(gridRows, gridColumns));
        final Calendar c = CalendarUtil.copyCalendar(start, true);
        c.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        
        data.setIntervalStart(CalendarUtil.copyCalendar(c, true));
        
        for (int i = 0; i < daysPerMonth; i++) {
            days[i] = new TestMonthDayLayoutManager(calendar, c.getTime(), 0.1f,firstDayOfWeek);
            days[i].setEnabled(CalendarUtil.isSameMonth(start, c));
            displayPanel.add(days[i].layout());
            c.add(Calendar.DATE, 1);
        }
       
        Calendar endInterval = CalendarUtil.copyCalendar(c, true);
        endInterval.add(Calendar.DAY_OF_MONTH, -1);
        
        data.setIntervalEnd(CalendarUtil.copyCalendar(endInterval, true));
        
        calendar.getModel().intervalChange(data.getIntervalStart().getTime(), data.getIntervalEnd().getTime());

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
        Calendar start = CalendarUtil.copyCalendar(data.getDate(), true);

        start.add(Calendar.MONTH, -1);
        start.set(Calendar.DAY_OF_MONTH, 1);

        Calendar end = CalendarUtil.getCalendar(start.getTime(), true);
        end.add(Calendar.MONTH, 1);

        moveInterval(start, end);
    }


    @Override
    public void moveIntervalRight() {
        Calendar start = CalendarUtil.copyCalendar(data.getDate(), true);

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
		
		data.setDate(CalendarUtil.copyCalendar(start, true));

        Calendar c = CalendarUtil.copyCalendar(start, true);
        c.set(Calendar.DAY_OF_WEEK,firstDayOfWeek);
        
        data.setIntervalStart(CalendarUtil.copyCalendar(c, true));
        
        for (int i = 0; i < daysPerMonth; i++) {
            days[i].setDate(c.getTime());
            days[i].setEnabled(CalendarUtil.isSameMonth(start, c));
            c.add(Calendar.DATE, 1);
        }
        
        Calendar endInterval = CalendarUtil.copyCalendar(c, true);
        endInterval.add(Calendar.DAY_OF_MONTH, -1);
        
        data.setIntervalEnd(CalendarUtil.copyCalendar(endInterval, true));

        changeHeaderInterval();
        
        calendar.getModel().intervalChange(data.getIntervalStart().getTime(), data.getIntervalEnd().getTime());
        
	}
	
	private void changeHeaderInterval() {
		Calendar c = CalendarUtil.copyCalendar(data.getDate(), true);
        String interval = sdf.format(c.getTime());
        headerPane.getIntervalLabel().setText(interval);
	}


}

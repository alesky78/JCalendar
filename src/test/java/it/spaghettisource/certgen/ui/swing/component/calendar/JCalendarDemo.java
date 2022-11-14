package it.spaghettisource.certgen.ui.swing.component.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import it.spaghettisource.certgen.ui.swing.component.calendar.events.IntervalChangedEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.IntervalChangedListener;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.ModelChangedEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.ModelChangedListener;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.SelectionChangedEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.events.SelectionChangedListener;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarModel;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarModelMemory;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/**
 * 
 * 
 * @author Alessandro D'Ottavio
 *
 */
public class JCalendarDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss:SSS");
	private final Random r = new Random();

	private JSplitPane content;
	private JToolBar toolBar;
	private JTextArea console;
	
	//calendar open
	private JCalendar jCalendar;
	private CalendarModel model;

	private JButton removeButton;
	private JButton addButton;
	private JButton modifyButton;	

	public JCalendarDemo() {

		initGui();
		bindListeners();
		initData();

	}

	private void initGui() {

		toolBar = new JToolBar("Controls");
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		modifyButton = new JButton("Modify");
		toolBar.add(addButton);
		toolBar.add(removeButton);
		toolBar.add(modifyButton);

		console = new JTextArea();
		console.setLineWrap(true);
		console.setRows(10);
		
		//the framework objects
		model = new CalendarModelMemory();
		jCalendar = new JCalendar(model);
		jCalendar.setPreferredSize(new Dimension(1024, 768));
		bindJCalendarListeners();

		content = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		content.add(jCalendar);
		content.add(new JScrollPane(console));

		this.getContentPane().setLayout(new BorderLayout(10, 10));
		this.getContentPane().add(toolBar, BorderLayout.PAGE_START);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

	}

	private void initData() {

		CalendarEvent calendarEvent;
		
		//create random events
		Calendar today = CalendarUtil.getCalendar(new Date(System.currentTimeMillis()), true);
		
		//create a randomical events whit a distribution several months before and after today
		for (int i = 0; i < 150; i++) {
			int hour = r.nextInt(19);
			final int min = r.nextInt(60);
			final int day = r.nextInt(29);
			final int month = today.get(Calendar.MONTH) -5 + r.nextInt(13);  
			final int year = today.get(Calendar.YEAR);
	
			final Date start = CalendarUtil.createDate(year, month, day, hour, min, 0, 0);
			final Date end = CalendarUtil.createDate(year, month, day+r.nextInt(6), hour + r.nextInt(5), r.nextInt(59), 0, 0);			
			calendarEvent = new CalendarEvent("event "+i, start, end);
			calendarEvent.setBackgroundColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

			model.add(calendarEvent);
		}
		
		//just clean the log screen before to start the app
		console.setText("");

	}

	private void bindListeners() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
						
				Date start = new Date(System.currentTimeMillis());
				CalendarEvent calendarEvent = new CalendarEvent("event added", start, start);
				calendarEvent.setBackgroundColor(Color.RED);
				model.add(calendarEvent);
			}
		});

		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				CalendarEvent selected = model.getSelected();
				if(selected!=null) {
					model.remove(selected);
				}
			}
		});
		
		modifyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CalendarEvent selected = model.getSelected();
				if(selected!=null) {
					selected.setSummary(r.nextInt(10) +" random");
					model.update(selected);
				}
			}
		});
	}


			
	private void bindJCalendarListeners() {
		
		model.addModelChangedListener(new ModelChangedListener() {

			@Override
			public void eventRemoved(final ModelChangedEvent event) {
				console.append("Event removed " + event.getCalendarEvent() + "\n");
			}

			@Override
			public void eventUpdate(final ModelChangedEvent event) {
				console.append("Event changed " + event.getCalendarEvent() + "\n");
			}

			@Override
			public void eventAdded(final ModelChangedEvent event) {
				console.append("Event added " + event.getCalendarEvent() + "\n");
			}
		});

		/**
		 * this is the best point where to customize the component and introduce CRUD operation on the events and show popup/dialogs
		 */
		model.addSelectionChangedListener(new SelectionChangedListener() {

			/**
			 * this event is launch when you click on an exist event in the calendar 
			 * here is possible open a popoup/dialog to manage the update or deletion of this event
			 */
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				console.append("Selected event, Date: "+event.getSelectedDate()+" actual:" + event.getActualSelectedEvent() +" previous:" + event.getPreviousSelectedEvent());
				console.append("\n");
				
			}

			/**
			 * this event is launch when you click on an empty area in the calendar
			 * here is possible open a popoup/dialog to manage the creation of a new event
			 */
			@Override
			public void selectionClean(SelectionChangedEvent event) {
				console.append("Selected clean, Date: "+event.getSelectedDate()+" actual:" + event.getActualSelectedEvent() +" previous:" + event.getPreviousSelectedEvent());
				console.append("\n");
				
				
			}

		});

		model.addIntervalChangedListener(new IntervalChangedListener() {

			@Override
			public void intervalChanged(final IntervalChangedEvent event) {
				console.append("IntervalChangedListener interval changed " + sdf.format(event.getIntervalStart()) + " "+ sdf.format(event.getIntervalEnd()) + "\n");
			}
		});
		

	}

	public static void main(final String[] args) {
		final JCalendarDemo frameTest = new JCalendarDemo();
		frameTest.setVisible(true);
	}
}

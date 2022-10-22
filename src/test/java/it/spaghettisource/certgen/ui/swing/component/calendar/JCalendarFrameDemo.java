package it.spaghettisource.certgen.ui.swing.component.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
import it.spaghettisource.certgen.ui.swing.component.calendar.model.AgendaModel;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.AgendaModelMemory;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.EventType;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarUtil;

/**
 * @author costache
 * 
 */
public class JCalendarFrameDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss:SSS");
	private final Random r = new Random();

	private JSplitPane content;
	private JToolBar toolBar;
	private JTextArea description;
	
	//calendar open
	private JCalendar jCalendar;
	private AgendaModel model;

	private final String[] names = new String[] { "Team meeting", "Code review", "Project review", "Telephone conference" };

	private JButton removeButton;
	private JButton addButton;
	private JButton modifyButton;	

	public JCalendarFrameDemo() {

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


		description = new JTextArea();
		description.setLineWrap(true);
		description.setRows(10);
		
		//the framework objects
		model = new AgendaModelMemory();
		jCalendar = new JCalendar(model);
		jCalendar.setPreferredSize(new Dimension(1024, 768));

		content = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		content.add(jCalendar);
		content.add(new JScrollPane(description));

		this.getContentPane().setLayout(new BorderLayout(10, 10));
		this.getContentPane().add(toolBar, BorderLayout.PAGE_START);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

	}

	private void initData() {

		final EventType type1 = new EventType();

		final EventType type2 = new EventType();
		type2.setBackgroundColor(new Color(255, 103, 0, 128));

		final EventType type3 = new EventType();
		type3.setBackgroundColor(new Color(165, 103, 230, 128));

		final EventType[] types = new EventType[3];
		types[0] = type1;
		types[1] = type2;
		types[2] = type3;

		CalendarEvent calendarEvent;
		
		//create random events
		for (int i = 0; i < 200; i++) {
			int hour = r.nextInt(19);
			final int min = r.nextInt(60);
			final int day = r.nextInt(29);
			final int month = r.nextInt(12);
			final int year = 2022;
	
			final Date start = CalendarUtil.createDate(year, month, day, hour, min, 0, 0);
			final Date end = CalendarUtil.createDate(year, month, day+r.nextInt(4), hour + 1 + r.nextInt(4), r.nextInt(59), 0, 0);
			calendarEvent = new CalendarEvent(names[r.nextInt(3)], start, end);
			calendarEvent.setType(types[r.nextInt(3)]);
			model.add(calendarEvent);
		}

	}

	private void bindListeners() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {

				EventType type1 = new EventType();
				type1.setBackgroundColor(Color.RED);
				
				EventType type2 = new EventType();
				type2.setBackgroundColor(Color.GREEN);
				
				EventType type3 = new EventType();
				type3.setBackgroundColor(Color.GRAY);
				
				int hour = 0;
				int min = 0;
				int day = 11;
				int month = 10;
				int year = 2022;
				
				Date start = CalendarUtil.createDate(year, month, day, hour, min, 0, 0);
				Date end = CalendarUtil.createDate(year, month, day, hour+1 ,min+2, 0, 0);
				CalendarEvent calendarEvent = new CalendarEvent("evento 1 ", start, end);
				calendarEvent.setType(type1);
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


		model.addModelChangedListener(new ModelChangedListener() {

			@Override
			public void eventRemoved(final ModelChangedEvent event) {
				description.append("Event removed " + event.getCalendarEvent() + "\n");
			}

			@Override
			public void eventUpdate(final ModelChangedEvent event) {
				description.append("Event changed " + event.getCalendarEvent() + "\n");
			}

			@Override
			public void eventAdded(final ModelChangedEvent event) {
				description.append("Event added " + event.getCalendarEvent() + "\n");
			}
		});

		model.addSelectionChangedListener(new SelectionChangedListener() {

			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				description.append("Event selected " + event.getCalendarEvent());
				description.append("\n");
			}

			@Override
			public void selectionClean() {
				description.append("Selection cleared");
				description.append("\n");
			}
		});

		model.addIntervalChangedListener(new IntervalChangedListener() {

			@Override
			public void intervalChanged(final IntervalChangedEvent event) {
				description.append("IntervalChangedListener interval changed " + sdf.format(event.getIntervalStart()) + " "+ sdf.format(event.getIntervalEnd()) + "\n");
			}
		});
		

	}

	public static void main(final String[] args) throws MalformedObjectNameException, NullPointerException,
		InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		//Locale.setDefault(Locale.ITALIAN);
		final JCalendarFrameDemo frameTest = new JCalendarFrameDemo();
		frameTest.setVisible(true);
	}
}

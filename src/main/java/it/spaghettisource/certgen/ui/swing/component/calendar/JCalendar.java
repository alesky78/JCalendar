package it.spaghettisource.certgen.ui.swing.component.calendar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.AgendaModel;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.AgendaModelMemory;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarContentPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarHeaderPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy.DisplayStrategy;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy.DisplayStrategyFactory;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy.DisplayStrategyType;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarEventFormat;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.CalendarEventFormatDefault;


public class JCalendar extends JPanel {

    private static final long serialVersionUID = 1L;
   
    private CalendarHeaderPanel headerPane;
    private CalendarContentPanel contentPane;
    
    private CalendarData data;
    private CalendarConfig config;
    private CalendarEventFormat formater;
    
	private DisplayStrategy strategy;
    
    private AgendaModel model;

    public JCalendar() {
    	this(new AgendaModelMemory(),new CalendarConfig());
    }
    
    public JCalendar(CalendarConfig config) {
    	this(new AgendaModelMemory(),config);
    }
    
    public JCalendar(AgendaModel model) {
    	this(model,new CalendarConfig());
    }
    
    public JCalendar(AgendaModel model, CalendarConfig config) {

        data = new CalendarData(Calendar.getInstance(),Calendar.getInstance(), Calendar.getInstance());
        formater = new CalendarEventFormatDefault();

        this.config = config;
        this.model = model;
        model.setParent(this);
        
        initGui();
        bindListeners();
    }    

    /**
     * Initializes the GUI
     */
    private void initGui() {
        
        headerPane = new CalendarHeaderPanel();
        contentPane = new CalendarContentPanel();
        
		strategy = DisplayStrategyFactory.buildStrategyByType(this, contentPane, headerPane, DisplayStrategyType.MONTH, data);
		strategy.displayContentPanel();
		strategy.displayHeaderPanel();
        
        this.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        add(headerPane, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.9;
        c.insets = new Insets(10, 10, 10, 10);
        add(contentPane, c);
    }

    /**
     * Binds listeners to the components
     */
    private void bindListeners() {
    	
    	//change strategy listener
        final ActionListener strategyChangeActionListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
            	DisplayStrategyType selectedStrategy = DisplayStrategyType.valueOf(e.getActionCommand()) ;

                if (getDisplayStrategy() != selectedStrategy)
                    setDisplayStrategy(selectedStrategy);
            }
        };
        headerPane.addStrategyChangeActionListener(strategyChangeActionListener);

    	//go previous period listener
        headerPane.getTodayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                strategy.changeIntervalStart(new Date(System.currentTimeMillis()));
            }
        });
        
    	//go previous period listener
        headerPane.getScrollLeft().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                strategy.moveIntervalLeft();
            }
        });

    	//go next period listener
        headerPane.getScrollRight().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                strategy.moveIntervalRight();
            }
        });
    }

    
	/**
	 * change the start date and the view is update automatically accordingly with the date pass as argument
	 * 
     * @param date
     */
    public void setIntervalStartDay(final Date date) {        
        strategy.changeIntervalStart(date);
    }    
    
    /**
     * Return the model associated to this calendar
     * 
     * @return
     */
    public AgendaModel getModel() {
		return model;
	}
    	
    /**
     * Sets the display strategy
     *
     * @param strategyType the {@link Type} of strategy to be used
     */
    public void setDisplayStrategy(DisplayStrategyType strategyType) {
    	
		strategy = DisplayStrategyFactory.buildStrategyByType(this, contentPane, headerPane, strategyType, data);
		strategy.displayContentPanel();
		strategy.displayHeaderPanel();
    	
    }

    /**
     * Gets the current display strategy.
     *
     * @return {@link Type}
     */
    public DisplayStrategyType getDisplayStrategy() {
        return strategy.getType();
    }
    
    /**
     * @return
     */
    public CalendarConfig getConfig() {
        return config;
    }

    /**
     * @param config
     */
    public void setConfig(final CalendarConfig config) {
        this.config = config;
        validate();
        repaint();
    }

    /**
     * @return
     */
    public CalendarEventFormat getTooltipFormater() {
        return formater;
    }

    /**
     * @param formater
     */
    public void setTooltipFormater(final CalendarEventFormat formater) {
        this.formater = formater;
    }
    
    
}

package it.spaghettisource.certgen.ui.swing.component.calendar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarModel;
import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarModelMemory;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarContentPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.CalendarHeaderPanel;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy.DisplayStrategy;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy.DisplayStrategyFactory;
import it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy.DisplayStrategyType;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.TooltipFormat;
import it.spaghettisource.certgen.ui.swing.component.calendar.util.TooltipFormatDefault;


public class JCalendar extends JPanel {

    private static final long serialVersionUID = 1L;
   
    private CalendarHeaderPanel headerPane;
    private CalendarContentPanel contentPane;
    
    private CalendarState state;
    private CalendarConfig config;
    private TooltipFormat tooltipFormater;
    
	private DisplayStrategy strategy;
    
    private CalendarModel model;

    public JCalendar() {
    	this(new CalendarModelMemory(),new CalendarConfig(),new TooltipFormatDefault());
    }
    
    public JCalendar(CalendarConfig config) {
    	this(new CalendarModelMemory(),config, new TooltipFormatDefault());
    }
    
    public JCalendar(TooltipFormat formater) {
    	this(new CalendarModelMemory(),new CalendarConfig(), formater);
    }    
    
    public JCalendar(CalendarConfig config,TooltipFormat formater) {
    	this(new CalendarModelMemory(),config, formater);
    }    
    
    public JCalendar(CalendarModel model) {
    	this(model,new CalendarConfig(),new TooltipFormatDefault());
    }
    
    
    public JCalendar(CalendarModel model, CalendarConfig config, TooltipFormat formater) {

        state = new CalendarState(Calendar.getInstance(),Calendar.getInstance(), Calendar.getInstance());
        this.tooltipFormater = formater;

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
        
		strategy = DisplayStrategyFactory.buildStrategyByType(this, contentPane, headerPane, DisplayStrategyType.MONTH, state);
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
     * set the model associated to this calendar
     * 
     * @param model
     */	
    public void setModel(CalendarModel model) {
 
		this.model = model;
        model.setParent(this);
        
        //clean the UI and reinitialize it
        this.remove(headerPane);
        this.remove(contentPane);
        this.setLayout(null);
        
        //re-init the ui
        initGui();
        bindListeners();
	}

	/**
     * Return the model associated to this calendar
     * 
     * @return
     */
    public CalendarModel getModel() {
		return model;
	}
    	
    /**
     * Sets the display strategy
     *
     * @param strategyType the {@link Type} of strategy to be used
     */
    public void setDisplayStrategy(DisplayStrategyType strategyType) {
    	
		strategy = DisplayStrategyFactory.buildStrategyByType(this, contentPane, headerPane, strategyType, state);
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
    public TooltipFormat getTooltipFormater() {
        return tooltipFormater;
    }

    /**
     * @param formater
     */
    public void setTooltipFormater(final TooltipFormat formater) {
        this.tooltipFormater = formater;
    }
    
    
}

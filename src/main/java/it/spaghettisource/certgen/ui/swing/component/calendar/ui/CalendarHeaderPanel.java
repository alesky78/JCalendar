package it.spaghettisource.certgen.ui.swing.component.calendar.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy.DisplayStrategyType;

/**
 * Heander Content panel of the calendar
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarHeaderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton scrollLeftButton;
	private JButton scrollRightButton;
	private JLabel intervalLabel;
	private JButton todayButton;	
	
	private List<JButton> strategies;

	/**
	 * Creates a new instance of {@link CalendarHeaderPanel}
	 */
	public CalendarHeaderPanel() {
		strategies = new ArrayList<>();
		
		init();
	}

	private void init() {

		this.setOpaque(false);

		ResourceBundle bunlde = ResourceBundle.getBundle("calendar", this.getLocale());
		
		String today = bunlde.getString("today");

		todayButton = new JButton();

		scrollLeftButton = new JButton();
		scrollRightButton = new JButton();

		intervalLabel = new JLabel();

		todayButton.setText(today);

		scrollLeftButton.setBorderPainted(false);
		scrollLeftButton.setFocusPainted(false);
		scrollLeftButton.setContentAreaFilled(false);

		scrollRightButton.setBorderPainted(false);
		scrollRightButton.setFocusPainted(false);
		scrollRightButton.setContentAreaFilled(false);

		scrollLeftButton.setText("<");
		scrollRightButton.setText(">");

		todayButton.setOpaque(false);

		this.setLayout(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.insets = new Insets(10, 10, 10, 0);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;		
		this.add(todayButton, c);
		c.gridx = 1;
		c.gridy = 0;
		this.add(scrollLeftButton, c);
		c.gridx = 2;
		c.gridy = 0;
		this.add(scrollRightButton, c);
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 1.0;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(intervalLabel, c);
		
		int position = 4;
		JButton strategyButton;
		for (DisplayStrategyType type : DisplayStrategyType.values()) {

			c.gridx = position;
			c.gridy = 0;
			c.weightx = 0.0;
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(10, 0, 10, 0);
			
			strategyButton = new JButton();
			strategyButton.setOpaque(false);
			strategyButton.setText(bunlde.getString(type.getI18n()));
			strategyButton.setActionCommand(type.name());
			
			this.add(strategyButton, c);
			strategies.add(strategyButton);
			
			position++;
		}
		
		//keep little bit fo space in the end
		c.gridx = position;
		c.gridy = 0;
		c.weightx = 0.0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 0, 10, 10);
		this.add(new Label(), c);
		
	}

	
	public JButton getTodayButton() {
		return todayButton;
	}

	public JButton getScrollLeft() {
		return scrollLeftButton;
	}

	public JButton getScrollRight() {
		return scrollRightButton;
	}

	public JLabel getIntervalLabel() {
		return intervalLabel;
	}

	public void addStrategyChangeActionListener(ActionListener strategyActionListener) {
		for (JButton jButton : strategies) {
			jButton.addActionListener(strategyActionListener);
		}
	}

}

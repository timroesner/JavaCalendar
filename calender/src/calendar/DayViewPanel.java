package calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * A JPanel that represents the Calendar Day View in GUI.
 * @author Tim
 * @version 1.0
 */

public class DayViewPanel extends JPanel {

	//instance variables
	private JLabel[] labels = new JLabel[24];
	private ArrayList<Event> eventsOnDay = new ArrayList<>();

	/**
	 * Constructs a DayViewPanel object.
	 */
	public DayViewPanel() {
		
		eventsOnDay.clear();
		labels = new JLabel[24];

		// Enclosing panel with GridBagLayout
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		// Load all events happening that day
		for (Event event : Calendar.events) {
			if (event.date.equals(SimpleCalendar.month.date)) {
				eventsOnDay.add(event);
				System.out.println(event.title);
			}
		}

		// Create all 24 labels for each hour of the day
		for (int hour = 0; hour < 24; hour++) {
			LocalTime time = LocalTime.of(hour, 0);
			JLabel timeLbl = new JLabel(time.toString());
			timeLbl.setHorizontalAlignment(SwingConstants.CENTER);
			timeLbl.setOpaque(true);
			timeLbl.setBackground(Color.white);
			timeLbl.setBorder(BorderFactory.createLineBorder(Color.black));
			JLabel gap = new JLabel();
			gap.setBackground(Color.white);
			gap.setOpaque(true);
			gap.setBorder(BorderFactory.createLineBorder(Color.black));
			labels[hour] = gap;

			// If event is happening make background blue and add title of events
			for (Event event : eventsOnDay) {
				if (event.startTime.getHour() == hour) {
					labels[hour].setText(event.title);
					labels[hour].setForeground(Color.white);
					labels[hour].setBackground(Color.BLUE);
				}
				if (event.startTime.getHour() < hour && hour < event.endTime.getHour()) {
					labels[hour].setBackground(Color.BLUE);
				}
			}

			// Add labels with preferred size
			c.gridx = 0;
			c.gridy = hour;
			c.gridwidth = 1;
			timeLbl.setPreferredSize(new Dimension(45, 25));
			panel.add(timeLbl, c);
			c.gridx = 1;
			c.gridy = hour;
			c.gridwidth = 2;
			gap.setPreferredSize(new Dimension(250, 25));
			panel.add(gap, c);
		}

		// Make panel scrollable
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(325, 200));
		add(scrollPane);
	}

	
	/**
	 * This method is called to update the view whenever a new event is added or
	 *  the selected date has changed.
	 * @param month takes month from controllView to update the view if month.date has changed
	 *
	 */
	public void update(Month month) {
		eventsOnDay.clear();

		// Update events happening that day
		for (Event event : Calendar.events) {
			if (event.date.equals(month.date)) {
				eventsOnDay.add(event);
				System.out.println(event.title);
			}
		}

		// Update labels and add new events
		for (int hour = 0; hour < 24; hour++) {
			labels[hour].setText("");
			labels[hour].setBackground(Color.WHITE);
			for (Event event : eventsOnDay) {
				if (event.startTime.getHour() == hour) {
					labels[hour].setText(event.title);
					labels[hour].setForeground(Color.white);
					labels[hour].setBackground(Color.BLUE);
				} else if (event.startTime.getHour() < hour && hour < event.endTime.getHour()) {
					labels[hour].setBackground(Color.BLUE);
				}
			}
		}
	}
}
package calendar;

import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author Tim
 * @version 1.0
 */

public class WeekViewPanel extends JPanel {

	private JLabel[][] labels = new JLabel[7][24];
	private ArrayList<Event> eventsOnDay = new ArrayList<>();

	/**
	 * Adds panels of all 7 days into one
	 */
	public WeekViewPanel() {

		JPanel week = new JPanel(new FlowLayout());
		for (LocalDate weekday : SimpleCalendar.month.getWeekArray()) {
			week.add(create(weekday));
		}
		JScrollPane scrollPane = new JScrollPane(week);
		scrollPane.setPreferredSize(new Dimension(950, 200));
		add(scrollPane);
	}

	/**
	 * @param date 
	 * @return JPanel of the current day
	 */
	public JPanel create(LocalDate date) {

		int weekday = date.getDayOfWeek().getValue() - 1;
		eventsOnDay.clear();

		// Enclosing panel with GridBagLayout
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		// Load all events happening that day
		for (Event event : Calendar.events) {
			if (event.date.equals(date)) {
				eventsOnDay.add(event);
			}
		}

		JLabel weekdayName = new JLabel(date.getDayOfWeek().getDisplayName(TextStyle.FULL, SimpleCalendar.language));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		weekdayName.setPreferredSize(new Dimension(120, 25));
		weekdayName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(weekdayName, c);

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

			// If event is occuring make background blue and add title of events
			for (Event event : eventsOnDay) {
				if (event.startTime.getHour() == hour) {
					labels[weekday][hour].setText(event.title);
					labels[weekday][hour].setBackground(Color.BLUE);
				}
				if (event.startTime.getHour() < hour && hour < event.endTime.getHour()) {
					labels[weekday][hour].setBackground(Color.BLUE);
				}
			}

			// Add labels with prefered size
			labels[weekday][hour] = gap;
			c.gridx = 0;
			c.gridy = hour + 1;
			c.gridwidth = 1;
			timeLbl.setPreferredSize(new Dimension(45, 25));
			panel.add(timeLbl, c);
			c.gridx = 1;
			c.gridy = hour + 1;
			c.gridwidth = 2;
			gap.setPreferredSize(new Dimension(75, 25));
			panel.add(gap, c);
		}
		return panel;
	}

	
	/**
	 * @param month takes month from ControllView
	 * Is called when month.date is changed or 
	 * when new Event is added
	 */
	public void update(Month month) {
		for (LocalDate date : month.getWeekArray()) {
			eventsOnDay.clear();
			int weekday = date.getDayOfWeek().getValue() - 1;
			// Update events happening that day
			for (Event event : Calendar.events) {
				if (event.date.equals(date)) {
					eventsOnDay.add(event);
				}
			}

			// Update labels and add new events
			for (int hour = 0; hour < 24; hour++) {
				labels[weekday][hour].setText("");
				labels[weekday][hour].setBackground(Color.WHITE);
				for (Event event : eventsOnDay) {
					if (event.startTime.getHour() == hour) {
						labels[weekday][hour].setText(event.title);
						labels[weekday][hour].setForeground(Color.white);
						labels[weekday][hour].setBackground(Color.BLUE);
					} else if (event.startTime.getHour() < hour && hour < event.endTime.getHour()) {
						labels[weekday][hour].setBackground(Color.BLUE);
					}
				}
			}
		}
	}
}

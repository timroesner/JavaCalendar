package calendar;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.text.DateFormatSymbols;

/**
 * @author Tim Roesner
 * @version 1.0
 */

public class Month {

	public LocalDate date;
	private ArrayList<ChangeListener> listeners;

	public Month(LocalDate date) {
		this.date = date;
		listeners = new ArrayList<ChangeListener>();
	}

	/**
	 * Attach a listener to the Model
	 * 
	 * @param c
	 *            the listener
	 */
	public void attach(ChangeListener c) {
		listeners.add(c);
	}

	// Changes current date and notifies listeners
	public void changeDate(LocalDate date) {
		this.date = date;
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}

	// Returns array with all 42 days to be displayed
	public LocalDate[] getDateArray() {
		LocalDate firstOfMonth = this.date.withDayOfMonth(1);
		LocalDate currentDay = firstOfMonth.minusDays(firstOfMonth.getDayOfWeek().getValue());
		LocalDate[] result = new LocalDate[42];
		for (int day = 0; day < 42; day++) {
			result[day] = currentDay;
			currentDay = currentDay.plusDays(1);
		}
		return result;
	}

	public LocalDate[] getWeekArray() {
		LocalDate currentDate = this.date;
		LocalDate[] result = new LocalDate[7];
		for (int day = 0; day < 7; day++) {
			if (day == 0) {
				if (currentDate.getDayOfWeek().getValue() != 7) {
					currentDate = currentDate.minusDays(currentDate.getDayOfWeek().getValue());
				}
			}
			result[day] = currentDate;
			currentDate = currentDate.plusDays(1);
		}
		return result;
	}

	// Returns localized strings of weekdays
	public String[] weekdays() {
		DateFormatSymbols symbols = new DateFormatSymbols(SimpleCalendar.language);
		return symbols.getShortWeekdays();
	}

	// Returns localized string of month and year
	public String monthYearString() {
		return this.date.getMonth().getDisplayName(TextStyle.FULL, SimpleCalendar.language) + " " + this.date.getYear();
	}
	
	/**
	 * Gets the currently selected date.
	 * @return the selected date
	 */
	public LocalDate getSelectedDate()
	{
		return date;
	}
	
}

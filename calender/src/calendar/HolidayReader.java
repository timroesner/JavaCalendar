package calendar;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author sarahsaber
 * Strategy interface that will be implemented by concrete strategy classes.
 * Responsible for reading national holidays of different countries.  
 */
public interface HolidayReader {
	ArrayList<Event> readHolidays() throws IOException;
}

package calendar;

import java.time.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;


/**
 * @author Tim Roesner
 * @version 1.0
 */

public class SimpleCalendar {
	
		// User could change these, for example to localize program to other regions / time zones
		public static ZoneId z = ZoneId.of( "America/Los_Angeles" );
		public static Locale language = Locale.ENGLISH; // affects language of months and weekdays
		
		public static JFrame frame = new JFrame();
		public static LocalDate today = LocalDate.now(z);
		public static Month month = new Month(today);
		public static Calendar calendar = new Calendar(new ArrayList<Event>());
		
		public static void main(String[] args) {
			ControllView monthView = new ControllView(month);
			calendar.attach(monthView);
			month.attach(monthView);
			frame.setLayout(new FlowLayout());
			frame.add(monthView);
			
			// Finalize frame setup
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
		}
}

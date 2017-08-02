package calendar;

import java.util.*;
import java.time.*;
import java.time.format.*;
import javax.swing.event.*;

/**
 * @author Tim Roesner
 * @version 1.0
 */

public class Calendar {
	// instance variables
	public static Scanner sc = new Scanner(System.in);
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	public static ArrayList<Event> events = new ArrayList<>(); // store events here while running
	public static LocalDate today = LocalDate.now( SimpleCalendar.z );
	private ArrayList<ChangeListener> listeners;
	
    // Construct Model and Listeners
	public Calendar(ArrayList<Event> e){
		events = e;
		listeners = new ArrayList<ChangeListener>();
	}
	
	/**
     * Attach a listener to the Model
     * @param c the listener
     */
    public void attach(ChangeListener c){
        listeners.add(c);
    }
    
    /**
     * Adds new data to the model
     * @param s takes a String to add
     */
    public void add(Event e){
        events.add(e);
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }
	
}

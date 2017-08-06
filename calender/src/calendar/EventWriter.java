package calendar;

import java.io.*;

/**
 * An EventWriter which writes events to a printWriter.
 * @author Tim
 * @version 1.0
 */

public class EventWriter {

	private PrintWriter printWriter;
	
	/**
	 * EventWriter constructor 
	 * @param printWriter takes PrintWriter
	 */
	public EventWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
	
	/**
	 * writeEvent method which writes an event to a printWriter.
	 * @param event takes Event to write
	 * @throws IOException 
	 */
	public void writeEvent(Event event) throws IOException{
		printWriter.println(event.getTitle());
		printWriter.println(event.getDate());
		printWriter.println(event.getStartTime());
		if(event.getEndTime().equals("")){
			printWriter.println("Optional");
		} else{
			printWriter.println(event.getEndTime());
		}
	}
}
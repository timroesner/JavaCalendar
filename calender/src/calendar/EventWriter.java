package calendar;

import java.io.*;

/**
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
	 * writeEvent method
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
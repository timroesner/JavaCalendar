package calendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * @author sarahsaber
 * A concrete strategy class that implements the HolidayReader interface.
 * This class reads all USA Holidays in 2017 from a text file.
 */
public class USAHolidayReader implements HolidayReader {

	//instance variables
	private File usaHolidays = new File("./USAHolidays.txt");	//File that contains all the US Holidays in 2017
	
	/**
	 * No-args constructor
	 */
	public USAHolidayReader()
	{	}

	/**
	 * Returns an ArrayList<Event> containing all USA Holidays in 2017 parsed from
	 * 'USAHoliday.txt'. 
	 * @return the ArrayList<Event> containing the holidays
	 */
	public ArrayList<Event> readHolidays() throws IOException {
		
		ArrayList<Event> result = new ArrayList<>();
		
		//check if the file exits
		if (!(usaHolidays.exists()))
		{
			System.out.println("File 'USAHolidays.txt' does not exist");
		}
		
		//build chain of readers
		FileReader fr = new FileReader(usaHolidays);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		//while the EOF hasn't been reached
		while ((line = br.readLine()) != null)
		{
			//parse event info
			String title = line;
			LocalDate date = LocalDate.parse(br.readLine(), Calendar.formatter);
			//create event and add it to the result ArrayList
			Event e = new Event (title, date);
			result.add(e);
		}
		//close chain of readers
		br.close();
		fr.close();
		
		return result;
	}
	
	public static void main(String[] args)
	{
		USAHolidayReader usReader = new USAHolidayReader();
		try {
			ArrayList<Event> holidays = usReader.readHolidays();
			for (Event h : holidays)
				System.out.println(h.holidayToString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}

package calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;

/*
 * 1) when clicking "from file", should a dialogue be shown (for selecting the specific file)
 * 2) test the "Quit" button after clicking the "From File" button
 * 3) keep track of today in the month view
 * 4) change the color of today in the calendar months GUI 
 * 5) add a label to the day view showing the current date: WeekDay MM/DD/YYYY
 * 6) the calendar month arrows shouldn't change the currently selected views
 */

/**
 * 
 * @author sarahsaber
 *  A JPanel that represents the Calendar Month View in GUI. 
 */
public class MonthView extends JPanel implements ChangeListener {

	//instance variables
	private Calendar calendar;
	private Pane[] textPanes;
	private LocalDate selectedDate;
	private LocalDate today;
	private JPanel gridPanel;

	/**
	 * Constructs a MonthView object.
	 * @param c a reference to the Calendar object (model)
	 */
	public MonthView(Calendar c)
	{
		calendar = c;
		selectedDate = SimpleCalendar.month.getSelectedDate();
		textPanes = new Pane[selectedDate.lengthOfMonth()];
		today = LocalDate.now();
		gridPanel = new JPanel();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		for (int i = 0; i < textPanes.length; i++)
		{
			textPanes[i] = new Pane();
			textPanes[i].setDayValue(" " + Integer.toString(i+1));
		}
	}

	/**
	 * Draws the drawView object.
	 */
	public void drawView()
	{
		//get current month
		java.time.Month monthEnum = selectedDate.getMonth(); 
		//create label with the current month
		JLabel monthLabel = new JLabel(monthEnum.toString() + " " + selectedDate.getYear());
		this.add(monthLabel);
		//create grid of month days
		createGridPanel(selectedDate);
		this.add(gridPanel);
		this.setPreferredSize(new Dimension(300,200));
		
		JScrollPane scrollPane = new JScrollPane(gridPanel);
		scrollPane.setPreferredSize(new Dimension(800,200));
		add(scrollPane);

	}

	/**
	 * Creates the individual month days.
	 * @param date the currently selected localDate
	 */
	public void createGridPanel(LocalDate date)
	{
		//create new grid with 7 columns and 0 gaps both horizontally and vertically
		gridPanel.setLayout(new GridLayout(0, 7, 0, 0));

		//create temporary calendar with DAY_OF_MONTH = 1 : the first day of the month
		LocalDate temp = LocalDate.of(date.getYear(), date.getMonthValue(), 1);

		DateFormatSymbols symbols = new DateFormatSymbols(SimpleCalendar.language);
		List<String> weekDays = Arrays.asList(symbols.getShortWeekdays());

		JPanel weekDaysPanel = new JPanel();
		weekDaysPanel.setLayout(new GridLayout(0, 7, 0, 0));
		//add labels with weekdays to the panel
		for (int day=1; day<8;day++)
		{
			JLabel label = new JLabel(weekDays.get(day));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setPreferredSize(new Dimension(10,30));
			weekDaysPanel.add(label);
		}
		this.add(weekDaysPanel);

		//determine the name of the first day of month. 
		//getValue returns the int value of the enum
		int firstDay = temp.getDayOfWeek().getValue();

		//add blank JTextPanes till you reach the first day of the month
		for (int i = 0; i < firstDay; i++)
		{
			Pane pane = new Pane();
			gridPanel.add(pane);
		}

		for (Pane pane : textPanes)
		{			
//			if(!scheduler.hasEvents(LocalDate.of(selectedDate.getYear(), 
//					selectedDate.getMonth(), Integer.parseInt(pane.getDayValueText().trim()))))
//			{
//				pane.setFlag(false);
//			}

			pane.removeAll();
			pane.updateUI();
			
			
//			//create dummy LocalDate variable from the pane's day value
//			LocalDate dummy = LocalDate.of(date.getYear(), date.getMonthValue(), 
//					Integer.parseInt(pane.getDayValueText().trim()));
//			if (dummy.equals(today))
//				pane.getDayValue().setBackground(Color.black);
//			else
//				pane.getDayValue().setBackground(null);
			pane.insertComponent(pane.getDayValue());
			gridPanel.add(pane);
		}

	}

	/**
	 * Display events scheduled on the currently selected day in the Month view.
	 * @param date the currently selected date 
	 */
	private void displayEvents(LocalDate date)
	{
		//To keep track of events on that day
		ArrayList<Event> events = new ArrayList<>();
		
		//TreeMap<LocalDate, TreeSet<CalendarEvent>> data = scheduler.getData();
		//get a set of events on this date
		//TreeSet<CalendarEvent> events = new TreeSet<>();
		
		//iterate over all the days in the month 
		for (int i = 0; i < date.lengthOfMonth(); i++)
		{
			//create a dummy LocalDate object with changing the monthDay each iteration
			LocalDate temp = LocalDate.of(date.getYear(), date.getMonthValue(),
					i+1);
			//check if there are any events scheduled on this dummy date
			if (calendar.hasEvents(temp))
				//if so, add all of them to the events ArrayList
				events.addAll(calendar.getEventsOnThisDate(temp));
		}

		//clear all textPanes from any pre-existing string
		for (Pane p : textPanes)
		{
			p.setText("");
		}

		//iterate over all events scheduled on that day
		for (Event event : events)
		{
			//get the month day of the event 
			int monthDay = event.date.getDayOfMonth();

			//iterate over all textPanes
			for (Pane pane : textPanes)
			{
				//trim white space before you assign it to the integer variable
				int dayValue = Integer.parseInt(pane.getDayValueText().trim());
				//if the pane's dayValue corresponds to the same monthDay as that of the event
				if (dayValue == monthDay)
				{
					//append the event info to the text in the pane 
					String text = pane.getText() + "\n" + event.getTitle() + " " + 
					event.getStartTime() + " - " + event.getEndTime();
					//clear the pane
					pane.setText("");
					//display the new updated pane
					pane.setText(text);
					pane.setForeground(Color.RED);
					//pane.setBackground(Color.RED);
					break;
				}	
			}

		}
	}

	@Override
	/**
	 * Updates the MonthView upon a change in the model. 
	 * @param e the event that notifies the view of the change
	 */
	public void stateChanged(ChangeEvent e) {

		//remove any existing components in the MonthView panel
		this.removeAll();
		gridPanel.removeAll();
		this.updateUI();
		//get updated information from the model
		selectedDate = SimpleCalendar.month.getSelectedDate();
		//if the selected date has any scheduled events, display them
		if (!Calendar.events.isEmpty())
			displayEvents(selectedDate);
		
//		if(!scheduler.getData().isEmpty())
//			displayEvents(selectedDate);

		//draw the day view
		drawView();
	}

	/**
	 * Gets the array of textPanes in the dayGUI.
	 * @return the array of textPanes
	 */
	public Pane[] getTextPanes()
	{
		return textPanes;
	}

//	public static void main(String[] args)
//	{
//		Calendar c = new Calendar(new ArrayList<Event>());
//		//Month m = new Month(c.today);
//		//s.setSelectedDate(LocalDate.now());
//		MonthView mv = new MonthView(c);
//		JFrame frame = new JFrame();
//		frame.setSize(800, 800);
//		frame.setLayout(new BorderLayout());
//
//		c.attach(mv);
//		//m.attach(mv);
//		//mv.drawView();
//		
//		LocalDate d1 = LocalDate.of(2017, 8, 26);
//		LocalDate d2 = LocalDate.of(2017, 8, 29);
//		LocalDate d3 = LocalDate.of(2017, 8, 30);
//		LocalDate d4 = LocalDate.of(2017, 8, 2);
//
//		LocalTime s1 = LocalTime.of(12, 00);
//		LocalTime f1 = LocalTime.of(13, 00);
//		LocalTime s2 = LocalTime.of(12, 00);
//		LocalTime f2 = LocalTime.of(13, 00);
//		LocalTime s3 = LocalTime.of(12, 00);
//		LocalTime f3 = LocalTime.of(13, 00);
//
//		Event e = new Event("Test Event", d1 , s1, f1);
//		Event e2 = new Event("Test Event", d2, s2, f2);
//		Event e3 = new Event("Test Event", d3, s3, f3);
//		Event e4 = new Event("Test Event", d4, s3, f3);
//
//		c.add(e);
//		c.add(e2);
//		c.add(e3);
//		c.add(e4);
////		for (Pane p : mv.getTextPanes())
////		{
////			System.out.println(p.getText());
////		}
//		//mv.drawView();
//		
//		frame.add(mv, BorderLayout.EAST);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		//frame.pack();
//	}

}
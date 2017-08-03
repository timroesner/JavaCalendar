package calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.*;
import java.time.*;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;

public class MonthView extends JPanel implements ChangeListener {

	//instance variables
	private Scheduler scheduler;
	private Pane[] textPanes;
	private LocalDate selectedDate;
	private JPanel gridPanel;

	public MonthView(Scheduler s)
	{
		scheduler = s;
		selectedDate = s.getSelectedDate();
		textPanes = new Pane[selectedDate.lengthOfMonth()];
		gridPanel = new JPanel();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		for (int i = 0; i < textPanes.length; i++)
		{
			textPanes[i] = new Pane();
			textPanes[i].setDayValue(" " + Integer.toString(i+1));
		}
	}

	public void drawView()
	{
		//get current month
		Month month = selectedDate.getMonth(); 
		//create label with the current month
		JLabel monthLabel = new JLabel(month.toString() + " " + selectedDate.getYear());
		this.add(monthLabel);
		//create grid of month days
		createGridPanel(selectedDate);
		this.add(gridPanel);
		this.setPreferredSize(new Dimension(700,300));
	}

	public void createGridPanel(LocalDate date)
	{
		//create new grid with 7 columns and 0 gaps both horizontally and vertically
		gridPanel.setLayout(new GridLayout(0, 7, 0, 0));

		//create temporary calendar with DAY_OF_MONTH = 1 : the first day of the month
		LocalDate temp = LocalDate.of(date.getYear(), date.getMonthValue(), 1);

		List<String> weekDays = Arrays.asList("Sun", "Mon", "Tue",
				"Wed", "Thu", "Fri", "Sat");

		//add labels with weekdays to the panel
		for (String s : weekDays)
		{
			JLabel label = new JLabel(s);
			label.setHorizontalAlignment(JLabel.CENTER);
			gridPanel.add(label);
		}

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
			if(!scheduler.hasEvents(LocalDate.of(selectedDate.getYear(), 
					selectedDate.getMonth(), Integer.parseInt(pane.getDayValueText().trim()))))
			{
				pane.setFlag(false);
			}

			pane.removeAll();
			pane.updateUI();
			pane.insertComponent(pane.getDayValue());
			gridPanel.add(pane);
		}
	}

	private void displayEvents(LocalDate date)
	{
		//get updated data
		TreeMap<LocalDate, TreeSet<CalendarEvent>> data = scheduler.getData();
		//get a set of events on this date
		TreeSet<CalendarEvent> events = new TreeSet<>();
		
		//iterate over all the days in the month 
		for (int i = 0; i < date.lengthOfMonth(); i++)
		{
			//create a dummy LocalDate object with changing the monthDay each iteration
			LocalDate temp = LocalDate.of(date.getYear(), date.getMonthValue(),
					i+1);
			//check if there are any events scheduled on this dummy date
			if (scheduler.hasEvents(temp))
				//if so, add all of them to the events TreeSet
				events.addAll(data.get(temp));
		}

		//clear all textPanes from any pre-existing string
		for (Pane p : textPanes)
		{
			p.setText("");
		}

		//iterate over all events scheduled on that day
		for (CalendarEvent event : events)
		{
			//get the month day of the event 
			int monthDay = event.getDate().getDayOfMonth();

			//iterate over all textPanes
			for (Pane pane : textPanes)
			{
				//trim white space before you assign it to the integer variable
				int dayValue = Integer.parseInt(pane.getDayValueText().trim());
				//if the pane's dayValue corresponds to the same monthDay as that of the event
				if (dayValue == monthDay)
				{
					//append the event info to the text in the pane 
					String text = pane.getText() + "\n" + event.printEvent();
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
	public void stateChanged(ChangeEvent e) {

		//remove any existing components in the MonthView panel
		this.removeAll();
		gridPanel.removeAll();
		this.updateUI();
		//get updated information from the scheduler (the model)
		selectedDate = scheduler.getSelectedDate();
		//if the selected date has any scheduled events, display them
		if(!scheduler.getData().isEmpty())
			displayEvents(selectedDate);

		//draw the day view
		drawView();
	}

	public Pane[] getTextPanes()
	{
		return textPanes;
	}

	public static void main(String[] args)
	{
		Scheduler s = new Scheduler();
		s.setSelectedDate(LocalDate.now());
		MonthView mv = new MonthView(s);
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setLayout(new BorderLayout());

		s.attach(mv);
		//mv.drawView();

		s.createEvent("Test Event", "07/26/2017", "12:00", "13:00");
		s.createEvent("Another Event", "07/26/2017", "13:00", "14:00");
		s.createEvent("Test Event", "07/29/2017", "12:00", "13:00");
		s.createEvent("Test Event", "07/30/2017", "12:00", "13:00");
		s.createEvent("Test Event", "07/02/2017", "12:00", "13:00");

//		for (Pane p : mv.getTextPanes())
//		{
//			System.out.println(p.getText());
//		}
		//mv.drawView();
		
		frame.add(mv, BorderLayout.EAST);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
	}

}

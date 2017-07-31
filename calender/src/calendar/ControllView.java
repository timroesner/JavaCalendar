package calendar;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControllView extends JPanel implements ChangeListener {
	
	private JLabel monthYear = new JLabel();
	public Month month;
	private ArrayList<JTextField> labels = new ArrayList<>();
	private ArrayList<LocalDate> dates = new ArrayList<>();
	private Event generalEvent = new Event();
	private DayViewPanel dayView = new DayViewPanel();
	
	public ControllView(Month month){
		
		this.month = month;
		Calendar.events = generalEvent.loadEvents();
		JPanel monthView = new JPanel(); // The complete Month Panel
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		monthView.setLayout(new BoxLayout(monthView, BoxLayout.Y_AXIS));
		monthView.setSize(400,600);
		JPanel monthViewDays = new JPanel(); // Just the days and weekday labels layed out in a grid
		monthViewDays.setLayout(new GridLayout(7, 7));
		
		monthYear.setText(month.monthYearString()); // "June 2017"
		monthView.add(monthYear);
		
		String[] weekdays = month.weekdays(); // Localized weekdays in short format
				
		for(int day=1;day<8;day++){
			JLabel weekday = new JLabel(weekdays[day]);
			weekday.setHorizontalAlignment(SwingConstants.CENTER);
			monthViewDays.add(weekday);
		}
		
		LocalDate[] currentMonth = month.getDateArray(); // Get all 42 days displayed in the view
		for(LocalDate date : currentMonth){
			JTextField label = new JTextField(String.valueOf(date.getDayOfMonth()));
			label.setEditable(false);
			label.setHorizontalAlignment(JTextField.CENTER);
            
            // If date is today or the selected color it grey
			if(date.equals(Calendar.today) || date.equals(month.date)){
				label.setBackground(Color.gray);
			} else {
				label.setBackground(Color.white);
			}
			labels.add(label);
			dates.add(date);
            
            // Add MouseListener
			label.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					month.changeDate(getDateOfLbl(labels.indexOf(label)));
				}
			});
			monthViewDays.add(label);
		}
		
		monthView.add(monthViewDays);
        
        // Right view with NavPanel and DayViewPanel
		JPanel rightView = new JPanel();
		rightView.setLayout(new BoxLayout(rightView, BoxLayout.Y_AXIS));
		JPanel navPanel = new JPanel();
		JButton left = new JButton("<");
		JButton right = new JButton(">");
		JButton create = new JButton("Create");
		JButton quit = new JButton("Quit");
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				month.changeDate(month.date.minusDays(1));
			}
		});
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				month.changeDate(month.date.plusDays(1));
			}
		});
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateView(month);
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalEvent.saveEvents(Calendar.events);
				System.exit(0);
			}
		});
		navPanel.add(left);
		navPanel.add(right);
		navPanel.add(create);
		navPanel.add(quit);
		rightView.add(navPanel);
		rightView.add(dayView);
		add(monthView);
		add(rightView);
	}
	
	public LocalDate getDateOfLbl(int index){
		return dates.get(index);
	}

    // If stateChanged update panels
	public void stateChanged(ChangeEvent e) {
		dayView.update(month);
		int j = 0;
		monthYear.setText(this.month.monthYearString());
		dates.clear();
		for(LocalDate newDate : this.month.getDateArray()){
			JTextField label = labels.get(j);
			dates.add(newDate);
			label.setText(String.valueOf(newDate.getDayOfMonth()));
			if(this.month.date.equals(newDate) || Calendar.today.equals(newDate)){
				label.setBackground(Color.gray);
			} else {
				label.setBackground(Color.white);
			}
			j++;
		}
	}

}
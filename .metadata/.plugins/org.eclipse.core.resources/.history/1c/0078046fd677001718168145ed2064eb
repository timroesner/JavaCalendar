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
	private WeekViewPanel weekViewPanel = new WeekViewPanel();
	private MonthView monthViewPanel = new MonthView(SimpleCalendar.calendar);
	private AgendaViewPanel agendaView;
	
	public ControllView(Month month) {

		this.month = month;
		Calendar.events = generalEvent.loadEvents();
		JPanel monthView = new JPanel(); // The complete Month Panel
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		monthView.setLayout(new BoxLayout(monthView, BoxLayout.Y_AXIS));
		monthView.setSize(400, 600);
		JPanel monthViewDays = new JPanel(); // Just the days and weekday labels
												// layed out in a grid
		monthViewDays.setLayout(new GridLayout(7, 7));

		monthYear.setText(month.monthYearString());// "June 2017"
		JPanel navLine = new JPanel();
		navLine.setLayout(new BoxLayout(navLine, BoxLayout.X_AXIS));
		JTextField leftArrow = new JTextField("<");
		JTextField rightArrow = new JTextField(">");
		leftArrow.setEditable(false);
		rightArrow.setEditable(false);
		leftArrow.setMaximumSize(new Dimension(22, 16));
		rightArrow.setMaximumSize(new Dimension(22, 16));
		
		leftArrow.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				month.changeDate(month.date.minusMonths(1));
			}
		});
		rightArrow.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				month.changeDate(month.date.plusMonths(1));
			}
		});
		navLine.add(monthYear);
		navLine.add(leftArrow);
		navLine.add(rightArrow);
		monthView.add(navLine);

		String[] weekdays = month.weekdays(); // Localized weekdays in short
												// format

		for (int day = 1; day < 8; day++) {
			JLabel weekday = new JLabel(weekdays[day]);
			weekday.setHorizontalAlignment(SwingConstants.CENTER);
			monthViewDays.add(weekday);
		}

		LocalDate[] currentMonth = month.getDateArray(); // Get all 42 days
															// displayed in the
															// view
		for (LocalDate date : currentMonth) {
			JTextField label = new JTextField(String.valueOf(date.getDayOfMonth()));
			label.setEditable(false);
			label.setHorizontalAlignment(JTextField.CENTER);

			// If date is today or the selected color it grey
			if (date.equals(Calendar.today) || date.equals(month.date)) {
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
		JButton today = new JButton("Today");
		JButton dayPanelBtn = new JButton("Day");
		JButton weekPanelBtn = new JButton("Week");
		JButton monthPanelBtn = new JButton("Month");
		JButton agendaPanelBtn = new JButton("Agenda");
		JButton fromFile = new JButton("From File");
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
		today.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				month.changeDate(Calendar.today);
			}
		});
		dayPanelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightView.removeAll();
				rightView.add(navPanel);
				rightView.add(dayView);
				rightView.revalidate();
				rightView.repaint();
			}
		});
		weekPanelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightView.removeAll();
				rightView.add(navPanel);
				rightView.add(weekViewPanel);
				rightView.revalidate();
				rightView.repaint();
			}
		});
		fromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Event> recurringEvents = generalEvent.loadRecurringEvents();
				//iterating over entire ArrayList instead of using the addAll() method
				//because the views are only notified when the Calendar.add() method is invoked
				for (Event event : recurringEvents)
				{
					Calendar.events.add(event);
				}
				monthViewPanel.stateChanged(new ChangeEvent(this));
				dayView.update(month);
				weekViewPanel.update(month);
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalEvent.saveEvents(Calendar.events);
				System.exit(0);
			}
		});
		monthPanelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				rightView.removeAll();
				rightView.add(navPanel);
				rightView.add(monthViewPanel);
				monthViewPanel.stateChanged(new ChangeEvent(this));
				rightView.revalidate();
				rightView.repaint();
			}
		});
		agendaPanelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// pop out a new window for use to select dates
				final JFrame frame = new JFrame();
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
			    // add buttons and textFiled to the pop out window 
				JLabel start = new JLabel("Start Date:");
				final JTextField startTxt = new JTextField("08/01/2017");
				JLabel end = new JLabel("End Date:");
				final JTextField endTxt = new JTextField("08/01/2017");
				JButton cancel = new JButton("Cancel");
				JButton ok = new JButton("OK");
				//add actionListener to cancel button
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();

					}
				});

				//add actionListener to OK button
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try { // update the month.startDate and month.endDate
							SimpleCalendar.month.startDate = LocalDate.parse(startTxt.getText(), Calendar.formatter);
							SimpleCalendar.month.endDate = LocalDate.parse(endTxt.getText(), Calendar.formatter);
							// create new AgendaView
							agendaView = new AgendaViewPanel(month);
							rightView.removeAll();
							rightView.add(navPanel);
							rightView.add(agendaView);
							rightView.revalidate();
							rightView.repaint();
							frame.dispose();
						} catch (Exception e2) {
							System.out.println("error");
						};
					}
				});

				panel.add(start);
				panel.add(startTxt);
				panel.add(end);
				panel.add(endTxt);
				panel.add(ok);
				panel.add(cancel);

				frame.add(panel);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}
		});
		navPanel.add(today);
		navPanel.add(left);
		navPanel.add(right);
		navPanel.add(create);
		navPanel.add(dayPanelBtn);
		navPanel.add(weekPanelBtn);
		navPanel.add(monthPanelBtn);
		navPanel.add(agendaPanelBtn);
		navPanel.add(fromFile);
		navPanel.add(quit);
		rightView.add(navPanel);
		rightView.add(dayView);
		add(monthView);
		add(rightView);
	}

	public LocalDate getDateOfLbl(int index) {
		return dates.get(index);
	}

	// If stateChanged update panels
	public void stateChanged(ChangeEvent e) {
		dayView.update(month);
		weekViewPanel.update(month);
		monthViewPanel.stateChanged(new ChangeEvent(this));
		//monthViewPanel.update(month);
		int j = 0;
		monthYear.setText(this.month.monthYearString());
		dates.clear();
		for (LocalDate newDate : this.month.getDateArray()) {
			JTextField label = labels.get(j);
			dates.add(newDate);
			label.setText(String.valueOf(newDate.getDayOfMonth()));
			if (this.month.date.equals(newDate) || Calendar.today.equals(newDate)) {
				label.setBackground(Color.gray);
			} else {
				label.setBackground(Color.white);
			}
			j++;
		}
	}

}

package calendar;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AgendaViewPanel extends JPanel {
	private ArrayList<Event> eventsOnAgenda = new ArrayList<>();

	String agenda ;
	JTextArea agendaDetail;
	private Calendar calendar;
	public Month month;

	public AgendaViewPanel(Month month) {

		// creating the AgendaViewPanel
		JPanel panel = new JPanel();
		agendaDetail = new JTextArea(); // where the agenda is displayed
		agenda = "Events between " + month.startDate.toString() + " and " + month.endDate.toString() + '\n';

		// Load all events between start date and end date
		Event.sort();
		for (Event event : calendar.events) {

			if (event.date.equals(SimpleCalendar.month.startDate) || event.date.equals(SimpleCalendar.month.endDate))
				eventsOnAgenda.add(event);
			if (event.date.isAfter(SimpleCalendar.month.startDate) && event.date.isBefore(SimpleCalendar.month.endDate))
				eventsOnAgenda.add(event);
		}

		// display all the events on the agendaViewPanel
		for (Event e : eventsOnAgenda) {
			agenda += e.getDate() + " " + e.getStartTime() + " " + e.getEndTime() + " " + e.getTitle() + '\n';
		}
		
		if (eventsOnAgenda.size() == 0) agenda += "no events for selected period";

		agendaDetail.setText(agenda);
		panel.add(agendaDetail);

		// Make panel scrollable
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(325, 200));
		add(scrollPane);
	}
}

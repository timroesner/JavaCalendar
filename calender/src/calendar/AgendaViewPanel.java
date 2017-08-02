package calendar;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AgendaViewPanel extends JPanel {
	
	private static ArrayList<Event> eventsOnAgenda = new ArrayList<>();
	String agenda ;
	JTextArea agendaDetail;
	private Calendar calendar;

	public AgendaViewPanel() {

		// creating the AgendaViewPanel
		JPanel panel = new JPanel();
		agendaDetail = new JTextArea(); // where the agenda is displayed
		agenda = ""; // the string to save all the events
		agendaDetail.setText(agenda);
		panel.add(agendaDetail);

		// Make panel scrollable
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(325, 200));
		add(scrollPane);
	}
	
	/**
	 * update the agendaView when new event is added
	 * @param 
	 */
	public void update(Month month){
		agenda = "Events between " + month.startDate.toString() + " and " + month.endDate.toString() + '\n';
		Event.sort();
		eventsOnAgenda.clear() ;
		// save updated events to eventsOnAgenda
		for (Event event : calendar.events) {
			if (event.date.equals(month.startDate) || event.date.equals(month.endDate))
				eventsOnAgenda.add(event);
			if (event.date.isAfter(month.startDate) && event.date.isBefore(month.endDate))
				eventsOnAgenda.add(event);
		}

		// display all the events on the agendaViewPanel
		for (Event e : eventsOnAgenda) {
			agenda += e.getDate() + " " + e.getStartTime() + " " + e.getEndTime() + " " + e.getTitle() + '\n';
		}
		
		if (eventsOnAgenda.size() == 0) agenda += "no events for selected period";
		agendaDetail.setText(agenda);
	}

}

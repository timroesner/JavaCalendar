package calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * A Frame which displays a message and has an "OK" button to close.
 * @author Tim Roesner
 * @version 1.0
 */
public class AlertView extends JFrame{
	
	/**
	 * Constructs an AlertView object.
	 */
	public AlertView() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel label = new JLabel("The event you are trying to add conflicts with an existing event.");
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(label);
		panel.add(ok);
		add(panel);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
	}
}

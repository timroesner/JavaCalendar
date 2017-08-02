package calendar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 * @author sarahsaber
 * A Pane is a JTextPane representing the individual days in the month. This class is 
 * used by the MonthView class which has an array of panes. 
 *
 */
public class Pane extends JTextPane{
	
	//instance variables
	private JButton dayValue;
	private boolean flag;
	
	/**
	 * Constructs a Pane object.
	 */
	public Pane()
	{
		this.setEditable(false);
		dayValue = new JButton();
		flag = false;
		dayValue.setBorder(new EmptyBorder(0,0,0,0));
		this.insertComponent(dayValue);
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.setPreferredSize(new Dimension(80,70));
	}
	
	/**
	 * Returns day value JButton associated with pane.
	 * @return the day value button
	 */
	public JButton getDayValue()
	{
		return dayValue;
	}
	
	/**
	 * Returns the String text stored in the pane. 
	 * @return the String text
	 */
	public String getDayValueText()
	{
		return dayValue.getText();
	}
	
	/**
	 * Returns flag associated with the pane.
	 * @return true if the pane is flagged
	 */
	public boolean getFlag()
	{
		return flag;
	}
	
	/**
	 * Changes the value of the flag associated with the pane.
	 * @param value the new value
	 */
	public void setFlag(boolean value)
	{
		flag = value;
	}
	
	/**
	 * Changes the text of the JButton associated with the pane.
	 * @param s the new text
	 */
	public void setDayValue(String s)
	{
		dayValue.setText(s);
	}

}

package calendar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Pane extends JTextPane{
	
	//instance variables
	private JButton dayValue;
	private boolean flag;
	
	public Pane()
	{
		this.setEditable(false);
		dayValue = new JButton();
		flag = false;
		dayValue.setBorder(new EmptyBorder(0,0,0,0));
		this.insertComponent(dayValue);
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	}
	
	public JButton getDayValue()
	{
		return dayValue;
	}
	
	public String getDayValueText()
	{
		return dayValue.getText();
	}
	
	public boolean getFlag()
	{
		return flag;
	}
	
	public void setFlag(boolean value)
	{
		flag = value;
	}
	
	public void setDayValue(String s)
	{
		dayValue.setText(s);
	}

}

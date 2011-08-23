package openhaus.ui.chat.side;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextPane;

import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class FriendTabExtensionMessage extends OHPanel{
	private JTextPane message;
	
	public FriendTabExtensionMessage() {
		init();
		addComponents();
		addListeners();
	}
	
	private void init() {
		setPreferredSize(new Dimension(0,30));
		setLayout(new BorderLayout());
		
		message = new JTextPane();
	}
	
	private void addComponents() {
		add(message,BorderLayout.CENTER);
	}
	
	private void addListeners() {
	}
	
	public JTextPane getMessage(){
		return message;
	}

}

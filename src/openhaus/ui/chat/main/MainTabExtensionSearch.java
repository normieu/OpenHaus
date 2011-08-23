package openhaus.ui.chat.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import openhaus.ui.chat.util.Space;
import openhaus.ui.ohcomponents.OHPanel;
import openhaus.ui.ohcomponents.OHTextField;


@SuppressWarnings("serial")
public class MainTabExtensionSearch extends OHPanel{
	private OHTextField message;
	
	public MainTabExtensionSearch() {
		init();
		addComponents();
	}
	
	private void init() {
		setPreferredSize(new Dimension(0,30));
		setLayout(new BorderLayout());
		
		message = new OHTextField();
		message.setBorder(null);
	}
	
	private void addComponents() {
		add(message,BorderLayout.CENTER);
		add(new Space(20),BorderLayout.WEST);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);			
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}

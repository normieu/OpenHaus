package openhaus.ui.chat.main;
import java.awt.Color;
import java.awt.Graphics;

import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class MainTab extends OHPanel{
	
	public MainTab() {
		init();
		addListeners();
	}
	
	private void init() {
		
	}
	
	private void addListeners(){
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(new Color(100,150,200));			
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}

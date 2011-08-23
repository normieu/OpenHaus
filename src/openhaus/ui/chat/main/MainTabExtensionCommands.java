package openhaus.ui.chat.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class MainTabExtensionCommands extends OHPanel{
	
	public MainTabExtensionCommands() {
		init();
	}
	
	private void init(){
		setPreferredSize(new Dimension(0, 30));
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(new Color(100,150,200));			
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}

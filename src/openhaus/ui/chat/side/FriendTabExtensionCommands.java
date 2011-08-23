package openhaus.ui.chat.side;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class FriendTabExtensionCommands extends OHPanel{
	
	public FriendTabExtensionCommands() {
		init();
	}
	
	private void init(){
		setPreferredSize(new Dimension(0, 25));
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(new Color(100,150,200));			
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}

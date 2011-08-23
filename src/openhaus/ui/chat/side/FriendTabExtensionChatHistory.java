package openhaus.ui.chat.side;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BoxLayout;

import openhaus.ui.chat.util.MessageSegment;
import openhaus.ui.ohcomponents.OHPanel;
import openhaus.ui.ohcomponents.OHScrollPanel;

@SuppressWarnings("serial")
public class FriendTabExtensionChatHistory extends OHScrollPanel{
	private MessageSegment curMes;
	private OHPanel mesPanel;
	
	public FriendTabExtensionChatHistory() {
		init();
		addComponents();
	}
	
	private void init() {	
		mesPanel = new OHPanel();
		mesPanel.setLayout(new BoxLayout(mesPanel, BoxLayout.PAGE_AXIS));
	}
	
	private void addComponents(){
		setViewportView(mesPanel);
	}
	
	public void addMessage(String user, String s){
		if(curMes == null||curMes.getUser()!=user){			
			MessageSegment newSegment = new MessageSegment(user);
			mesPanel.add(newSegment);
			curMes = newSegment;
		}
		curMes.newMessage(s);
		updateUI();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);			
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.LIGHT_GRAY);			
		g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
	}

}

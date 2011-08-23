package openhaus.ui.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import openhaus.ui.chat.main.MainTabPanel;
import openhaus.ui.chat.side.MultiChatPanel;
import openhaus.ui.chat.util.Measures;
import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class ChatDashboard extends OHPanel{
	private MainTabPanel rightSection;
	
	public ChatDashboard() {
		init();
		addComponents();
		addListeners();
	}
	
	private void init(){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(0,25));
		
		rightSection = new MainTabPanel();
	}
	
	private void addComponents() {
		add(rightSection,BorderLayout.EAST);
		add(MultiChatPanel.getPanel(),BorderLayout.CENTER);
	}
	
	private void addListeners(){
		addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e){
				Measures.WIDTH = getWidth() - 240;
			}
		});
	}
}

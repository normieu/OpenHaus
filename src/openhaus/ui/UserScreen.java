package openhaus.ui;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import openhaus.ui.chat.ChatDashboard;
import openhaus.ui.chat.util.Measures;
import openhaus.ui.ohcomponents.OHPanel;



@SuppressWarnings("serial")
public class UserScreen extends OHPanel{
	private ChatDashboard dashboard;
	
	public UserScreen() {
		init();
		addComponents();
		addListeners();
	}
	
	private void init(){
		setLayout(new BorderLayout());
		
		dashboard = new ChatDashboard();
	}
	
	private void addComponents() {
		add(dashboard,BorderLayout.SOUTH);
	}
	
	private void addListeners(){
		addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e){
				Measures.HEIGHT = getHeight() - 30;
			}
		});
	}
	

}

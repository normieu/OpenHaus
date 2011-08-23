package openhaus.ui.chat.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import openhaus.ui.chat.ExtensionPanel;
import openhaus.ui.chat.util.Space;
import openhaus.ui.ohcomponents.OHPanel;




@SuppressWarnings("serial")
public class MainTabPanel extends OHPanel{
	private Space space;
	private MainTab listRoot;
	
	public MainTabPanel() {
		init();
		addComponents();
		addListeners();
	}
	
	private void init() {
		setPreferredSize(new Dimension(240,0));
		setLayout(new BorderLayout());
		
		listRoot = new MainTab();
		space = new Space(40);
	}
	
	private void addComponents(){
		add(space,BorderLayout.EAST);
		add(listRoot,BorderLayout.CENTER);
	}
	
	private void addListeners() {
		listRoot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				ExtensionPanel.getPanel().showChatList();
			}
		});

	}

}

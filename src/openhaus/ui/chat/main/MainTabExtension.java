package openhaus.ui.chat.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

import openhaus.ui.chat.ExtensionPanel;
import openhaus.ui.chat.util.Measures;
import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class MainTabExtension extends OHPanel{
	private MainTabExtensionCommands commands;
	private MainTabExtensionSearch search;
	private MainTabExtensionList list;
	
	public MainTabExtension() {
		init();
		addComponents();
		addListeners();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.GRAY, 1));
		
		commands = new  MainTabExtensionCommands();
		search = new MainTabExtensionSearch();
		list = new MainTabExtensionList();
	}
	
	private void addComponents(){
		add(commands,BorderLayout.NORTH);
		add(search,BorderLayout.SOUTH);
		add(list,BorderLayout.CENTER);
	}
	
	private void addListeners(){
		commands.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				ExtensionPanel.getPanel().hideAll();
			}
		});
	}
	
	public void showExtension(){
		int x = Measures.WIDTH;
		int x1 = 235;
		int y = 100;
		int y1 = Measures.HEIGHT-y+30;
		
		setBounds(x, y, x1, y1);		
	}
	
	public void updateList(ArrayList<String> online){
		list.updateList(online);
		updateUI();
	}

}

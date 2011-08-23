package openhaus.ui.chat.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;

import openhaus.ui.chat.side.MultiChatPanel;
import openhaus.ui.ohcomponents.OHLabel;
import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class MainTabExtensionList extends OHPanel{
	
	public MainTabExtensionList() {
		init();
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0;i<10;i++)
			temp.add("" +i);
		updateList(temp);
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	}
	
	private void arrangeList(ArrayList<String> list){
		Object[] s;
		s = list.toArray();
		Arrays.sort(s);
		list.clear();
		for(int i=0;i<s.length;i++){
			list.add((String) s[i]);
		}
	}

	
	public void updateList(ArrayList<String> list){
		removeAll();
		arrangeList(list);
		
		for(int i=0;i<list.size();i++){
			final OHLabel online = new OHLabel(list.get(i));
			online.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e){
					MultiChatPanel.getPanel().addNewTab(online.getText());
				}
			});
			add(online);
		}
		
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

package openhaus.ui.chat.side;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

import openhaus.ui.chat.ExtensionPanel;
import openhaus.ui.chat.util.Measures;
import openhaus.ui.chat.util.Navigation;


@SuppressWarnings("serial")
public class MultiChatPanel extends JPanel {
	private static MultiChatPanel ins;
	private FlowLayout layout;
	private Vector<FriendTab> list;
	private Navigation nav;
	private int flag = 0, ctr = 0;

	public static synchronized MultiChatPanel getPanel() {
		if (ins == null)
			ins = new MultiChatPanel();
		return ins;
	}

	public MultiChatPanel() {
		init();
		addComponents();
		addListeners();
	}

	private void init() {
		layout = new FlowLayout(FlowLayout.RIGHT);
		layout.setHgap(5);
		layout.setVgap(0);
		setLayout(layout);
		setOpaque(false);

		list = new Vector<FriendTab>();
		nav = new Navigation();
	}

	private void addComponents() {
		/*
		 * String names[] = new String[] { "James", "Euangel", "Elmundo",
		 * "Limpiado", "UPVTC", "WEWS", "LOL" , "LOL1" }; for (int i = 0; i <
		 * names.length; i++) addNewTab(names[i]);
		 * 
		 * arrange();
		 */
	}

	private void addListeners() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				arrange();
			}
		});
		nav.getRight().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				normalizeAll();
				ExtensionPanel.getPanel().hideAll();
				if (flag != 0) {
					flag--;
					nav.getLeft().setClickable(true);
				}

				arrange();
			}
		});
		nav.getLeft().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				normalizeAll();
				ExtensionPanel.getPanel().hideAll();
				if (flag < list.size() - 1 - ctr) {
					flag++;
					nav.getRight().setClickable(true);
				}

				arrange();
			}
		});
	}

	private int countMaxTabs() {
		int length, buff, tempCtr = 0;
		length = nav.getPreferredSize().width + layout.getHgap();

		for (int i = flag; i < list.size(); i++) {
			length += i == flag ? FriendTab.widthExt : FriendTab.widthNorm
					+ layout.getHgap();
			buff = Measures.WIDTH - length;
			if (buff > 0) {
				tempCtr++;
			} else
				break;
		}
		--tempCtr;

		return tempCtr;
	}

	public void arrange() {
		if (list.size() > 0) {
			removeAll();
			ctr = countMaxTabs();
			if (ctr < (list.size() - 1))
				add(nav);
			if ((flag - 1) < 0)
				flag = 0;
			if ((ctr + flag) > (list.size() - 1))
				flag = 0;
			
			FriendTab tab, alt;
			for (int i = ctr + flag, j = flag, x = Measures.WIDTH - 5; i > flag - 1; i--, x -= 5, j++) {
				tab = list.elementAt(i);
				alt = list.elementAt(j);

				x -= alt.getPreferredSize().width;
				alt.changePos(x);

				add(tab);
			}

			nav.getRight().setClickable(flag > 0);
			nav.getLeft().setClickable(flag < list.size() - 1 - ctr);
			updateUI();
		}
	}

	public void normalizeAll() {
		FriendTab t;
		for (int i = 0; i < list.size(); i++) {
			t = list.elementAt(i);
			t.setPreferredSize(new Dimension(FriendTab.widthNorm, 30));
		}
		updateUI();
	}

	public void addNewTab(String name) {
		FriendTab tempTab = null;
		boolean canAdd = true;
		int temp = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.elementAt(i).getName().equals(name)) {
				tempTab = list.elementAt(i);
				canAdd = false;
				temp = i;
				break;
			}
		}

		if (canAdd) {
			final FriendTab friend = new FriendTab(name);
			friend.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					normalizeAll();
					friend.setPreferredSize(new Dimension(FriendTab.widthExt,
							30));
					arrange();
					ExtensionPanel.getPanel().showFriendExtension(
							friend.getName());
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					friend.getClose().setVisible(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					friend.getClose().setVisible(false);
				}
				
			});
			list.add(0, friend);
			arrange();
		} else {
			int tempMax = countMaxTabs();
			if (temp > tempMax && temp - tempMax > flag) {
				flag = temp - tempMax;
			}
			if (temp < flag)
				flag = temp;
			normalizeAll();
			tempTab.setPreferredSize(new Dimension(FriendTab.widthExt, 30));
			arrange();
			ExtensionPanel.getPanel().showFriendExtension(name);
		}
	}
	
	public void removeTab(String name){
		FriendTab tempTab;
		for(int i=0;i<list.size();i++){
			tempTab = list.get(i);
			if(tempTab.getName().equals(name)){
				tempTab.setVisible(false);
				list.removeElement(tempTab);
				if (list.size() != 0 && flag > list.size() - 1)
					flag--;
				arrange();
				break;
			}
		}		
	}
	
	public boolean hasTab(String name){
		FriendTab tempTab;
		for(int i=0;i<list.size();i++){
			tempTab = list.get(i);
			if(tempTab.getName().equals(name))
				return true;
		}
		
		return false;
	}
}

package openhaus.ui.chat;

import java.util.Vector;

import openhaus.ui.chat.main.MainTabExtension;
import openhaus.ui.chat.side.FriendTabExtension;
import openhaus.ui.chat.side.MultiChatPanel;
import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class ExtensionPanel extends OHPanel{
	private static ExtensionPanel ins;
	private MainTabExtension mainTabExtension;
	private Vector<FriendTabExtension> extensions;
	
	public static synchronized ExtensionPanel getPanel(){
		if(ins == null)
			ins = new ExtensionPanel();
		return ins;
	}
	
	private ExtensionPanel(){
		init();
		hideAll();
		reconstruct();
	}
	
	private void init() {
		setLayout(null);
		
		mainTabExtension = new MainTabExtension();
		extensions = new Vector<FriendTabExtension>();
	}
	
	private void reconstruct(){
		removeAll();
		add(mainTabExtension);
		for(int i=0;i<extensions.size();i++)
			add(extensions.elementAt(i));
		updateUI();
	}
	
	public void hideAll(){
		for(int i=0;i<getComponentCount();i++)
			getComponent(i).setVisible(false);
		updateUI();
	}
	
	public void showChatList(){
		hideAll();
		MultiChatPanel.getPanel().normalizeAll();
		MultiChatPanel.getPanel().arrange();
		mainTabExtension.showExtension();
		mainTabExtension.setVisible(true);
		updateUI();
	}

	public void addFriendExtension(FriendTabExtension extension){
		extension.setVisible(false);
		extensions.add(extension);
		reconstruct();
	}
	
	public void removeFriendExtension(String name){
		FriendTabExtension ex;
		for(int i=0;i<extensions.size();i++){
			ex = extensions.elementAt(i);
			if(ex.getName().equals(name)){
				extensions.remove(i);
				break;
			}
		}
		reconstruct();
	}
	
	public void showFriendExtension(String name){
		FriendTabExtension ex;
		hideAll();
		for(int i=0;i<extensions.size();i++){ 
			ex = extensions.elementAt(i);
			if(ex.getName().equals(name)){
				ex.setVisible(true);
				break;
			}
		}
		updateUI();
	}
	
	public FriendTabExtension getFriendExtension(String name){
		FriendTabExtension ex;
		for(int i=0;i<extensions.size();i++){
			ex = extensions.elementAt(i);
			if(ex.getName().equals(name)){
				return ex;
			}
		}
		return null;
	}
	
	public MainTabExtension getTabExtension(){
		return mainTabExtension;
	}

}

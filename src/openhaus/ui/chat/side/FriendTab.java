package openhaus.ui.chat.side;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import openhaus.ui.chat.ExtensionPanel;
import openhaus.ui.ohcomponents.OHLabel;


@SuppressWarnings("serial")
public class FriendTab extends JPanel{
	public static int widthNorm, widthExt;
	private OHLabel close, title;
	private String name;
	private FriendTabExtension ext;
	
	public FriendTab(String name) {
		this.name = name;
		init();
		addComponents();
		addListeners();
	}

	private void init() {
		widthExt = 250;
		widthNorm = 160;
		setPreferredSize(new Dimension(widthNorm,30));
		setLayout(new BorderLayout());
		
		close = new OHLabel("X");
		close.setForeground(new Color(200,100,100));
		close.setPreferredSize(new Dimension(close.getPreferredSize().width+2,0));
		close.setVisible(false);
		
		title = new OHLabel(" " +name);
		title.setForeground(Color.WHITE);
		title.setPreferredSize(new Dimension(title.getPreferredSize().width+2,0));
		
		ext = new FriendTabExtension(name);
		ExtensionPanel.getPanel().addFriendExtension(ext);
	}
	
	private void addComponents(){
		add(close,BorderLayout.EAST);
		add(title,BorderLayout.CENTER);
	}
	
	private void addListeners(){
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				close();
			}
			@Override
			public void mouseEntered(MouseEvent e){
				close.setFont(new Font(close.getFont().getFamily(),1,close.getFont().getSize()));
				close.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e){
				close.setFont(new Font(close.getFont().getFamily(),0,close.getFont().getSize()));
				close.setVisible(false);
				
			}	
		});
	}
	
	private void close(){
		MultiChatPanel.getPanel().removeTab(name);
		ExtensionPanel.getPanel().removeFriendExtension(name);
		updateUI();
	}
	
	public JLabel getClose(){
		return close;
	}
	
	public void changePos(int start){
		ext.setPosition(start, getPreferredSize().width);
	}
	
	public FriendTabExtensionCommands getCommandSection(){
		return ext.getCommandSection();
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(64,64,64));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
	}
}

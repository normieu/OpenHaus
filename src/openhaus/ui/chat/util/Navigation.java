package openhaus.ui.chat.util;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import openhaus.ui.ohcomponents.OHLabel;
import openhaus.ui.ohcomponents.OHPanel;




@SuppressWarnings("serial")
public class Navigation extends OHPanel{
	private Arrow left, right;
	
	public Navigation() {
		init();
		addComponents();
	}
	
	private void init() {
		setPreferredSize(new Dimension(30,30));
		setLayout(new BorderLayout());
		
		left = new Arrow("<");
		right = new Arrow(">");
		right.setClickable(false);
	}
	
	private void addComponents(){
		add(left,BorderLayout.WEST);
		add(right,BorderLayout.EAST);
		
	}
	
	public class Arrow extends OHLabel{
		boolean isClickable = true;
		
		public Arrow(String s){
			super(s.equals("<")?" "+s:s +" ");
			init();
			addListeners();
		}
		
		private void init(){
			setFont(new Font("Calibri",0,16));
		}
		
		private void addListeners(){
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					if(isClickable)
						setForeground(Color.WHITE);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					if(isClickable)
						setForeground(Color.BLACK);
				}
			});
		}

		public boolean isClickable() {
			return isClickable;
		}

		public void setClickable(boolean isClickable) {
			if(isClickable)
				setForeground(Color.BLACK);
			else
				setForeground(Color.GRAY);
			this.isClickable = isClickable;
		}
		
		
	}
	
	public Arrow getRight(){
		return right;
	}
	
	public Arrow getLeft(){
		return left;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(150,150,150));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}

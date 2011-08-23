package openhaus.ui.chat.util;
import java.awt.Dimension;

import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class Space extends OHPanel{
	private int width = 5, height = 5;
	
	public Space(){
		init();
	}
	
	public Space(int width){
		this.width = width;
		init();
	}
	
	public Space(int width, int height){
		this.width = width;
		this.height = height;
		init();
	}
	
	public void set(int width){
		this.width = width;
		init();
		updateUI();
	}
	
	public void set(int width, int height){
		this.width = width;
		this.height = height;
		init();
		updateUI();
	}
	
	private void init(){
		setPreferredSize(new Dimension(width,height));
	}
}

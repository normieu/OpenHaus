package openhaus.ui.ohcomponents;

import java.awt.Graphics;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class OHScrollPanel extends JScrollPane{
	public OHScrollPanel(){
		getViewport().setOpaque(false);
		setOpaque(false);
		setBorder(null);
	}

	@Override
	public void paintComponent(Graphics g){
		paintChildren(g);
		g.dispose();
	}
}

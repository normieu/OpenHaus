package openhaus.ui.chat.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import openhaus.ui.ohcomponents.OHLabel;
import openhaus.ui.ohcomponents.OHPanel;


public class PicPanel extends OHPanel{
	private int width;
	private OHLabel userPic;
	private BufferedImage image;
	private String user;
	
	public PicPanel(int width, String user) {
		this.width = width;
		this.user = user;
		
		init();
		addComponents();
	}
	
	private void init(){		
		setPreferredSize(new Dimension(width,width));	
		setLayout(new BorderLayout());	
		
		try {
			image = ImageIO.read(new File("school.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		userPic = new OHLabel();
		userPic.setVerticalAlignment(SwingConstants.NORTH);
		userPic.setIcon(new ImageIcon(image));
	}
	
	private void addComponents() {
		add(new Space(),BorderLayout.NORTH);
		add(new Space(),BorderLayout.SOUTH);
		add(new Space(),BorderLayout.EAST);
		add(new Space(),BorderLayout.WEST);
		add(userPic,BorderLayout.CENTER);

	}
}

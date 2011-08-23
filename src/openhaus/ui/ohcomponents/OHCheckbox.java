package openhaus.ui.ohcomponents;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class OHCheckbox extends JCheckBox{
	
	public OHCheckbox() {
		super();
		setOpaque(false);
	}
	
	public OHCheckbox(String title) {
		super(title);
		setOpaque(false);
	}
	
}

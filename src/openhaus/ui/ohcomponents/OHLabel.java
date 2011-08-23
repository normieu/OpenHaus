package openhaus.ui.ohcomponents;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class OHLabel extends JLabel {

	public OHLabel() {
		super();
		setOpaque(false);
	}

	public OHLabel(String name) {
		super(name);
		setOpaque(false);
	}
}

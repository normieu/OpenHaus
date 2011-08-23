package openhaus.ui.ohcomponents;

import java.awt.LayoutManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OHPanel extends JPanel {

	public OHPanel() {
		super();
		setOpaque(false);
	}

	public OHPanel(LayoutManager layoutManager) {
		super(layoutManager);
		setOpaque(false);
	}

}

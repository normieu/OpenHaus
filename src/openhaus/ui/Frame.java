package openhaus.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class Frame extends JFrame {

	private OHPanel contentPane;

	Frame() {
		init();
		initComponents();
		addComponents();
		addListeners();
	}

	private void init() {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents() {
		contentPane = new OHPanel(new BorderLayout());
	}

	private void addComponents() {
		setContentPane(contentPane);
	}

	private void addListeners() {
		addWindowListener(new FrameClosingListener());
	}

	public void setView(OHPanel panel) {
		if (contentPane.getComponentCount() > 0)
			contentPane.removeAll();
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.updateUI();
	}

	private class FrameClosingListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			int option = JOptionPane.showConfirmDialog(Frame.this,
					"Exiting Program. Confirm?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				System.exit(1);
			}
		}
	}

}

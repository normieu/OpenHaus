package openhaus.ui.chat.util;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextPane;

import openhaus.ui.chat.side.FriendTab;
import openhaus.ui.ohcomponents.OHPanel;


@SuppressWarnings("serial")
public class MessageSegment extends OHPanel {
	private PicPanel picPanel;
	private JTextPane mesPane;
	private String user;

	public MessageSegment(String user) {
		this.user = user;

		init();
		addComponents();
	}

	private void init() {
		setLayout(new BorderLayout());

		picPanel = new PicPanel(50, user);
		mesPane = new JTextPane();
	}

	private void addComponents() {
		add(picPanel, BorderLayout.WEST);
		add(mesPane, BorderLayout.CENTER);
	}

	public void newMessage(String mes) {
		mesPane.setText(mesPane.getText() + mes);
		updateUI();

		int max = (int) (picPanel.getPreferredSize().getHeight() > mesPane
				.getPreferredSize().getHeight() ? picPanel.getPreferredSize()
				.getHeight() : mesPane.getPreferredSize().getHeight());
		System.out.println(max);
		setPreferredSize(new Dimension(0, max));
		setMaximumSize(new Dimension(FriendTab.widthExt, max));
	}

	public String getUser() {
		return user;
	}
}

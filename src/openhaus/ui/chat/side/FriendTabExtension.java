package openhaus.ui.chat.side;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import openhaus.client.Client;
import openhaus.common.actions.ChatAction;
import openhaus.ui.chat.ExtensionPanel;
import openhaus.ui.chat.util.Measures;
import openhaus.ui.ohcomponents.OHPanel;

@SuppressWarnings("serial")
public class FriendTabExtension extends OHPanel {
	private FriendTabExtensionCommands commands;
	private FriendTabExtensionChatHistory history;
	private FriendTabExtensionMessage message;
	private String name;

	public FriendTabExtension(String name) {
		this.name = name;
		init();
		addComponents();
		addListeners();
	}

	private void init() {
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.GRAY, 1));

		commands = new FriendTabExtensionCommands();
		message = new FriendTabExtensionMessage();
		history = new FriendTabExtensionChatHistory();
	}

	private void addComponents() {
		add(commands, BorderLayout.NORTH);
		add(message, BorderLayout.SOUTH);
		add(history, BorderLayout.CENTER);
	}

	private void addListeners() {
		commands.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ExtensionPanel.getPanel().hideAll();
				MultiChatPanel.getPanel().normalizeAll();
				MultiChatPanel.getPanel().arrange();
			}
		});
		message.getMessage().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					invokeSend();
				}
			}
		});
	}

	private void invokeSend() {
		// name = nag log in na user
		history.addMessage(name, message.getMessage().getText());
		Client.getInstance().
				send(
				new ChatAction(Client.username, name, "CHAT_ACTION", message.getMessage()
						.getText()));
		message.getMessage().setText("");
	}

	public void setPosition(int start, int width) {
		int x = start;
		int x1 = width;
		int y = Measures.HEIGHT - 300 + 30;
		int y1 = 300;

		setBounds(x, y, x1, y1);
	}

	public FriendTabExtensionCommands getCommandSection() {
		return commands;
	}

	public FriendTabExtensionMessage getMessageSection() {
		return message;
	}

	public void addMessage(String user, String s) {
		// user=sender
		history.addMessage(user, s);
	}

	@Override
	public String getName() {
		return name;
	}

}

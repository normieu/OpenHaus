package openhaus.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JOptionPane;

import openhaus.client.Client;
import openhaus.common.actions.AuthenticationAction;
import openhaus.common.logger.Log;
import openhaus.ui.ohcomponents.OHButton;
import openhaus.ui.ohcomponents.OHLabel;
import openhaus.ui.ohcomponents.OHPanel;
import openhaus.ui.ohcomponents.OHPasswordField;
import openhaus.ui.ohcomponents.OHTextField;


@SuppressWarnings("serial")
public class LoginScreen extends OHPanel {

	private OHTextField usernameField;
	private OHPasswordField passwordField;
	private OHLabel usernameLabel, passwordLabel;
	private OHButton loginButton, signupButton;
	private OHPanel loginPanel;

	LoginScreen() {
		init();
		initComponents();
		addComponents();
		addListeners();
	}

	private void init() {
		setLayout(new GridBagLayout());
	}

	private void initComponents() {
		loginPanel = new OHPanel(new GridBagLayout());

		usernameField = new OHTextField();
		passwordField = new OHPasswordField();

		loginButton = new OHButton("Login");
		signupButton = new OHButton("Sign up");
		signupButton.setBorderPainted(false);

		usernameLabel = new OHLabel("Username");
		passwordLabel = new OHLabel("Password");
	}

	private void addComponents() {
		loginPanel.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.1,
				1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		loginPanel.add(usernameField, new GridBagConstraints(1, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		loginPanel.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 0.1,
				1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		loginPanel.add(passwordField, new GridBagConstraints(1, 1, 1, 1, 1.0,
				1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		loginPanel.add(loginButton, new GridBagConstraints(1, 2, 1, 1, 1.0,
				1.0, GridBagConstraints.LINE_END, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		loginPanel.add(signupButton, new GridBagConstraints(0, 2, 1, 1, 0.1,
				1.0, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));

		add(loginPanel, new GridBagConstraints(0, 0, 1, 1, 0.4, 0.4,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 200, 150));
	}

	private void addListeners() {
		// TODO Auto-generated method stub
		signupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Swapper.INSTANCE.swap(Swapper.SIGNUP_SCREEN);
			}
		});
		loginButton.addActionListener(new LoginAction());
		usernameField.requestFocus();
	}

	private class LoginAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			Client.getInstance().login(usernameField.getText(), new String(passwordField.getPassword()));
			
			System.out.println("login details sent!");
			
//			try {
//				Serializable serializable = Client.getClient()
//						.receiveInput();
//				if (serializable instanceof AuthenticationAction) {
//					AuthenticationAction authAction = (AuthenticationAction)serializable;
//					if(authAction.isAuthenticated()){
//					Log.print("authenticated");
//					Swapper.INSTANCE.swap(Swapper.USER_SCREEN);
//					}
//					else{
//						Log.print("login failed");
//						JOptionPane.showMessageDialog(null,"login failed");						
//					}
//				}
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			} catch (ClassNotFoundException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
		}
	}
	
	public void clear() {
		usernameField.setText("");
		passwordField.setText("");
		usernameField.requestFocus();
	}

}
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
import openhaus.common.actions.AccountCreatedAction;
import openhaus.common.actions.AccountNotCreatedAction;
import openhaus.common.actions.ActionType;
import openhaus.common.actions.CreateNewAccountAction;
import openhaus.common.logger.Log;
import openhaus.ui.ohcomponents.OHButton;
import openhaus.ui.ohcomponents.OHCheckbox;
import openhaus.ui.ohcomponents.OHComboBox;
import openhaus.ui.ohcomponents.OHLabel;
import openhaus.ui.ohcomponents.OHPanel;
import openhaus.ui.ohcomponents.OHPasswordField;
import openhaus.ui.ohcomponents.OHTextField;

@SuppressWarnings("serial")
public class SignupScreen extends OHPanel {

	private OHTextField usernameField, schoolField;
	private OHPasswordField passwordField, confirmPasswordField;
	private OHCheckbox guestBox;
	private OHComboBox yearBox, courseBox;
	private OHButton confirmButton, cancelButton;
	private OHLabel usernameLabel, passwordLabel, confirmPasswordLabel, schoolLabel,
			yearLabel, courseLabel;
	private AvatarPanel avatarPanel;
	private OHPanel signupFieldsPanel, buttonPanel;

	public SignupScreen() {
		init();
		initComponents();
		addComponents();
		addListeners();
	}

	private void init() {
		setLayout(new GridBagLayout());
	}

	private void initComponents() {
		avatarPanel = new AvatarPanel();
		signupFieldsPanel = new OHPanel(new GridBagLayout());
		buttonPanel = new OHPanel(new GridBagLayout());

		usernameField = new OHTextField();
		schoolField = new OHTextField();
		passwordField = new OHPasswordField();
		confirmPasswordField = new OHPasswordField();

		guestBox = new OHCheckbox("Guest?");
		yearBox = new OHComboBox();
		courseBox = new OHComboBox();
		confirmButton = new OHButton("Confirm");
		cancelButton = new OHButton("Cancel");

		usernameLabel = new OHLabel("Username");
		passwordLabel = new OHLabel("Password");
		confirmPasswordLabel = new OHLabel("Confirm Password");
		schoolLabel = new OHLabel("School");
		yearLabel = new OHLabel("Year");
		courseLabel = new OHLabel("Course");

	}

	private void addComponents() {
		signupFieldsPanel.add(usernameLabel, getLabelConstraints(0));
		signupFieldsPanel.add(usernameField, getFieldConstraints(0));
		signupFieldsPanel.add(passwordLabel, getLabelConstraints(1));
		signupFieldsPanel.add(passwordField, getFieldConstraints(1));
		signupFieldsPanel.add(confirmPasswordLabel, getLabelConstraints(2));
		signupFieldsPanel.add(confirmPasswordField, getFieldConstraints(2));
		/*
		 * signupFieldsPanel.add(displayName, getLabelConstraints(3));
		 * signupFieldsPanel.add(displayNameField, getFieldConstraints(3));
		 * 
		 * signupFieldsPanel.add(middleNameLabel, getLabelConstraints(4));
		 * signupFieldsPanel.add(middleNameField, getFieldConstraints(4));
		 * signupFieldsPanel.add(lastNameLabel, getLabelConstraints(5));
		 * signupFieldsPanel.add(lastNameField, getFieldConstraints(5));
		 * signupFieldsPanel.add(guestBox, getLabelConstraints(6));
		 * signupFieldsPanel.add(schoolLabel, getLabelConstraints(7));
		 * signupFieldsPanel.add(schoolField, getFieldConstraints(7));
		 * signupFieldsPanel.add(courseLabel, getLabelConstraints(8));
		 * signupFieldsPanel.add(courseBox, getFieldConstraints(8));
		 * signupFieldsPanel.add(yearLabel, getLabelConstraints(9));
		 * signupFieldsPanel.add(yearBox, getFieldConstraints(9));
		 */
		buttonPanel.add(confirmButton, new GridBagConstraints(0, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.LINE_END, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		buttonPanel.add(cancelButton, new GridBagConstraints(1, 0, 1, 1, 0.0,
				1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 10, 0, 0), 0, 0));
		signupFieldsPanel.add(buttonPanel, getFieldConstraints(10));

		add(signupFieldsPanel, new GridBagConstraints(0, 0, 1, 1, 0.5, 1.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(20, 20, 20, 20), 0, 0));
		add(avatarPanel, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(10, 10, 10, 10), 0, 0));
		swapFields(false);
	}

	private GridBagConstraints getLabelConstraints(int y) {
		return new GridBagConstraints(0, y, 1, 1, 0.1, 1.0,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
				new Insets(0, 10, 0, 0), 0, 0);
	}

	private GridBagConstraints getFieldConstraints(int y) {
		return new GridBagConstraints(1, y, 1, 1, 1.0, 1.0,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0);
	}

	private void addListeners() {
		guestBox.addActionListener(new GuestListener());
		cancelButton.addActionListener(new CancelListener());
		confirmButton.addActionListener(new ConfirmListener());
	}

	private void swapFields(boolean guest) {
		if (guest) {
			schoolLabel.setEnabled(true);
			schoolField.setEnabled(true);
			courseLabel.setEnabled(false);
			courseBox.setEnabled(false);
			yearLabel.setEnabled(false);
			yearBox.setEnabled(false);
		} else {
			schoolLabel.setEnabled(false);
			schoolField.setEnabled(false);
			courseLabel.setEnabled(true);
			courseBox.setEnabled(true);
			yearLabel.setEnabled(true);
			yearBox.setEnabled(true);
		}
		signupFieldsPanel.validate();
	}

	public void clear() {
		for (int i = 0; i < signupFieldsPanel.getComponentCount(); i++) {
			if (signupFieldsPanel.getComponent(i) instanceof OHTextField)
				((OHTextField) signupFieldsPanel.getComponent(i)).setText("");
		}
		usernameField.requestFocus();
	}

	private class GuestListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			swapFields(guestBox.isSelected());
		}

	}

	private class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Swapper.INSTANCE.swap(Swapper.LOGIN_SCREEN);
			clear();
		}

	}

	private class ConfirmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (canSignUp()) {

				CreateNewAccountAction createAccount = new CreateNewAccountAction(
						usernameField.getText(), "SERVER",
						ActionType.CREATE_NEW_ACCOUNT_ACTION,
						usernameField.getText(), new String(
								passwordField.getPassword()));
				try {
					Client.getInstance().send(createAccount);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// try {
				// Serializable serializable = Client.getClient()
				// .receiveInput();
				// if (serializable instanceof AccountCreatedAction) {
				//
				// Log.print("account created");
				// JOptionPane.showMessageDialog(null,
				// "Successfully created account");
				// Swapper.INSTANCE.swap(Swapper.LOGIN_SCREEN);
				// clear();
				// } else if (serializable instanceof AccountNotCreatedAction) {
				// Log.print("account NOT created!");
				// AccountNotCreatedAction notCreated = (AccountNotCreatedAction)
				// serializable;
				// JOptionPane.showMessageDialog(null,
				// notCreated.getCause());
				// }
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (ClassNotFoundException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }

				// AccountManager accManager = new AccountManagerSession();
				// try {
				// accManager.addAccount(new Account(usernameField.getText(),
				// new String(passwordField.getPassword())));
				// JOptionPane.showMessageDialog(null,
				// "Successfully created account");
				// Swapper.INSTANCE.swap(Swapper.LOGIN_SCREEN);
				// clearAllFields();
				// } catch (TransactionException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } catch (NonUniqueException e) {
				// // TODO Auto-generated catch block
				// Log.println("Username already exists!");
				// JOptionPane.showMessageDialog(null,
				// "Username already exists!");
				// e.printStackTrace();
				// }
			}
		}
	}

	private boolean canSignUp() {
		if (usernameField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Username must not be empty");
			return false;
		}
		if (!new String(passwordField.getPassword()).equals(new String(
				confirmPasswordField.getPassword()))) {
			JOptionPane.showMessageDialog(null, "Password mismatch");
			return false;
		}
		return true;
	}
}

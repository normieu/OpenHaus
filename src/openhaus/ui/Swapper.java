package openhaus.ui;

import openhaus.ui.chat.ExtensionPanel;

public class Swapper {

	public static final Swapper INSTANCE = new Swapper();
	private Frame frame;
	private LoginScreen loginScreen;
	private SignupScreen signupScreen;
	private UserScreen  userScreen;
	public static final int LOGIN_SCREEN = 0, SIGNUP_SCREEN = 1, USER_SCREEN = 2;

	private Swapper() {
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
		this.frame.setGlassPane(ExtensionPanel.getPanel());
		this.frame.getGlassPane().setVisible(true);
	}

	public void init() {
		loginScreen = new LoginScreen();
		signupScreen = new SignupScreen();
		userScreen = new UserScreen();
	}

	public void swap(int selection) {
		switch (selection) {
		case LOGIN_SCREEN:
			frame.setView(loginScreen);
			loginScreen.clear();
			break;
		case SIGNUP_SCREEN:
			frame.setView(signupScreen);
			signupScreen.clear();
			break;
		case USER_SCREEN:
			frame.setView(userScreen);
			break;
		default:
			break;
		}
	}

}

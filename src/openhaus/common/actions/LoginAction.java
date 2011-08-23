package openhaus.common.actions;

import java.io.Serializable;

public class LoginAction extends Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 870292317415298271L;
	
	private String username;
	private String password;

	public LoginAction() {
		super();
	}

	public LoginAction(String source, String destination, String type, String username, String password) {
		super(source, destination, type);
		setUsername(username);
		setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

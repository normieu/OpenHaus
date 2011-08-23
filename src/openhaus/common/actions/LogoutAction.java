package openhaus.common.actions;

import java.io.Serializable;

public class LogoutAction extends Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1477256996631358964L;
	
	private String username;

	public LogoutAction() {
		super();
	}

	public LogoutAction(String source, String destination, String type, String username) {
		super(source, destination, type);
		setUsername(username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

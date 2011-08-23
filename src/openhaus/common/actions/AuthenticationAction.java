package openhaus.common.actions;

import java.io.Serializable;

public class AuthenticationAction extends Action implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4227675420964598035L;
	
	private boolean authenticated;

	public AuthenticationAction() {
		super();
	}

	public AuthenticationAction(String source, String destination, String type, boolean authenticated) {
		super(source, destination, type);
		this.authenticated = authenticated;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}
	
}

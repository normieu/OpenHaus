package openhaus.common.actions;

import java.io.Serializable;

public class AccountNotCreatedAction extends Action implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1431779273558458991L;
	
	private String cause;

	public AccountNotCreatedAction() {
		super();
	}

	public AccountNotCreatedAction(String source, String destination, String type, String cause) {
		super(source, destination, type);
		setCause(cause);
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
}
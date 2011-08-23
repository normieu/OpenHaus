package openhaus.common.actions;

import java.io.Serializable;

public class ChatAction extends Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 870292317415298271L;
	
	private String message;

	public ChatAction() {
		super();
	}
	
	public ChatAction(String source, String destination, String type, String message) {
		super(source, destination, type);
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
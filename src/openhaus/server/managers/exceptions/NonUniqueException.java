package openhaus.server.managers.exceptions;

public class NonUniqueException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8184781063182268073L;

	public NonUniqueException() {
		super();
	}
	
	public NonUniqueException(String msg) {
		super(msg);
	}

}

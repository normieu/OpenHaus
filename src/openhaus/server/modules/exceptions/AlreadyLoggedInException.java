package openhaus.server.modules.exceptions;

public class AlreadyLoggedInException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5077718955332241981L;

	public AlreadyLoggedInException() {
		super();
	}

	public AlreadyLoggedInException(String msg) {
		super(msg);
	}

}

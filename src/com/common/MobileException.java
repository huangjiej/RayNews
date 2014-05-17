package common;

/**
 * @author Ben
 * 
 */
public class MobileException extends Exception {
	private static final long serialVersionUID = -7636187668503194982L;
	private int statusCode = -1;

	public MobileException(String msg) {
		super(msg);
	}

	public MobileException(Exception cause) {
		super(cause);
	}

	public MobileException(String msg, int statusCode) {
		super(msg);
		this.statusCode = statusCode;

	}

	public MobileException(String msg, Exception cause) {
		super(msg, cause);
	}

	public MobileException(String msg, Exception cause, int statusCode) {
		super(msg, cause);
		this.statusCode = statusCode;

	}

	public int getStatusCode() {
		return this.statusCode;
	}
}

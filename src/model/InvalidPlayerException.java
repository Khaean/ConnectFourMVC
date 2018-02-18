package model;

public class InvalidPlayerException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPlayerException() {
		
	}
	
	public InvalidPlayerException(String msg) {
		super(msg);
	}

}

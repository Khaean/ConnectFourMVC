package model;

public class InvalidColumnException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidColumnException() {
		
	}
	
	public InvalidColumnException(String msg) {
		super(msg);
	}

}

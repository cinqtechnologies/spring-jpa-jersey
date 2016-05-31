package br.com.cinq.spring.data.sample.exception;

/**
 * 
 * @author Douglas Cardoso
 *
 */
public class RestServiceException extends Exception {
	
	/**
	 * 
	 * @param message
	 */
	public RestServiceException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

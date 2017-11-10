package br.com.cinq.spring.data.sample.error;

/**
 * This class is responsible for supporting Validation's Exception
 * @author Luiz Felipe
 *
 */
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4444746322276117591L;

	/**
	 * Constructor
	 * 
	 * @param String - Message exception information.
	 */
	public ValidationException(String message) {
		super(message);
	}
}

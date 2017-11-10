package br.com.cinq.spring.data.sample.error;

/**
 * This class is responsible for supporting Not Found Entity's Exception
 * @author Luiz Felipe
 *
 */
public class EntityNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4444746322276117591L;

	/**
	 * Constructor
	 * 
	 * @param String - Message exception information.
	 */
	public EntityNotFoundException(String message) {
		super(message);
	}
}

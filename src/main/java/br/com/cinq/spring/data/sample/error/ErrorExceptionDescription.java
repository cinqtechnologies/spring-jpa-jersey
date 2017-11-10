package br.com.cinq.spring.data.sample.error;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * This class is responsible to support exceptions error messages.
 * @author Luiz Felipe
 *
 */
public class ErrorExceptionDescription {

	/**
	 * HttpStatus property
	 */
	@JsonProperty("status_error_code")
	private HttpStatus status;
	
	/**
	 * Message error property
	 */
	@JsonProperty("message_error")
	private String message;

	/**
	 * Constructor
	 */
	private ErrorExceptionDescription() {

	}

	/**
	 * Constructor
	 */
	ErrorExceptionDescription(HttpStatus status) {
		this();
		this.status = status;
	}

	/**
	 * Constructor
	 */
	ErrorExceptionDescription(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";

	}

	/**
	 * Constructor
	 */
	ErrorExceptionDescription(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;

	}

	/**
	 * This methos returns HTTPStatus.
	 * @return
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * This method sets HTTPStatus
	 * @param HTTPStatus.class - HTTP status information.
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * This method returns exception error message.
	 * @return String - Exception error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * This method sets exception error message.
	 * @param String - Exception error message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}

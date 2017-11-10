package br.com.cinq.spring.data.sample.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.http.HttpStatus;

/**
 * This class is responsible for handle ValidationException as a Json message response.
 * @author Luiz Felipe
 *
 */
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {

	/**
	 * This method is responsible for responding a ValidationException with a specific status code. 
	 */
	public Response toResponse(ValidationException err) {

		ErrorExceptionDescription error = new ErrorExceptionDescription(HttpStatus.UNPROCESSABLE_ENTITY, err.getMessage(), err);
		
		return Response.status(Status.OK).entity(error).build();
	}

}

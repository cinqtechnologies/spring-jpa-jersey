package br.com.cinq.spring.data.sample.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.http.HttpStatus;

/**
 * This class is responsible for handle EntityNotFoundException as a Json message response.
 * @author Luiz Felipe
 *
 */
@Provider
public class EntityNotFoundExceptionHandler implements ExceptionMapper<EntityNotFoundException> {

	/**
	 * This method is responsible for responding a EntityNotFoundException with a specific status code. 
	 */
	public Response toResponse(EntityNotFoundException err) {

		ErrorExceptionDescription error = new ErrorExceptionDescription(HttpStatus.NOT_FOUND, err.getMessage(), err);
		
		return Response.status(Status.OK).entity(error).build();
	}

}

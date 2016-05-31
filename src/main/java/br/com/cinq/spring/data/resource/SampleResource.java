package br.com.cinq.spring.data.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.exception.RestServiceException;
import br.com.cinq.spring.data.sample.service.CityService;

/**
 * Greet Service
 *
 * @author Adriano Kretschmer
 */
@Path("/")
public class SampleResource {
	Logger logger = LoggerFactory.getLogger(SampleResource.class);

	@Autowired
	private CityService cityService; //domain's service

	@Path("/cities")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCities(@QueryParam("country") String countryName) {
		try {
			logger.info("Retrieving cities for {}", countryName);

			if (countryName == null)
				throw new RestServiceException("Country name is requested.");

			List<City> cities = cityService.getCities(countryName);
			return Response.ok().entity(cities).build();

		} catch (RestServiceException exception) {
			logger.error("Bad request to the server: {}", exception.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
		}
	}
}

package br.com.cinq.spring.data.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cinq.spring.data.sample.repository.CityRepository;


/**
 * Greet Service
 *
 * @author Adriano Kretschmer
 */
@Path("/")
public class SampleResource {
    Logger logger = LoggerFactory.getLogger(SampleResource.class);

    private final CityRepository cityDao;
    
    @Inject
    public SampleResource(CityRepository cityDao) {
        this.cityDao = cityDao;
    }

    @Path("/cities")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCities(@QueryParam("country") String name) {
        logger.info("Retrieving cities for {}", name);

        return Response.ok().entity(cityDao.findByCountryName(name)).build();
    }
}

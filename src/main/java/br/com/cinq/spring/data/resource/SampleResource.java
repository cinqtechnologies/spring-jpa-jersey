package br.com.cinq.spring.data.resource;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import br.com.cinq.spring.data.sample.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * Greet Service
 *
 * @author Adriano Kretschmer
 */
@Path("/")
public class SampleResource {
    Logger logger = LoggerFactory.getLogger(SampleResource.class);
    @Autowired
    private CityService cityService;


    @Path("/cities")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCities(@QueryParam("country") String name) {
        logger.info("Retrieving cities for {}", name);

        List<City> all = cityService.getCities(name);

        return Response.ok().entity(all).build();
    }


}

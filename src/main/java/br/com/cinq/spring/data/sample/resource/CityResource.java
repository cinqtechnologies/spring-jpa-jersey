package br.com.cinq.spring.data.sample.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.service.CityService;

@Path("/cities")
public class CityResource {
	
	@Autowired
	private CityService cityService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response getCities(@QueryParam("country") String country){
		List<City> list = null;
		if (StringUtils.isEmpty(country))
			list = cityService.getCities();
		else
			list = cityService.getCities(country);
		return Response.ok().entity(list).build();
	}
}

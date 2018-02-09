package br.com.cinq.spring.data.sample.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.service.CityService;

@Path("/cities")
public class CityResource {

	@Autowired
	private CityService cityService;
	
	private Logger logger = LoggerFactory.getLogger("br.com.cinq.spring.data.sample.resource.CityResource");

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCities(@QueryParam("country") String country) {
		List<City> list = null;
		if (StringUtils.isEmpty(country))
			list = cityService.getCities();
		else
			list = cityService.getCities(country);
		return Response.ok().entity(list).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCity(CityCreationRequest data) {
		try {
			cityService.add(data.getName(), data.getCountryName());
			return Response.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.serverError().build();
		}
	}
}

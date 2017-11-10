package br.com.cinq.spring.data.sample;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.error.EntityNotFoundException;
import br.com.cinq.spring.data.sample.error.ValidationException;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

/**
 * This class represents all the resource rest service available for City.
 * @author Luiz Felipe
 *
 */
@Component
@Path("/cities")
public class CityResource {

	/**
	 * City Repository database.
	 */
	@Autowired
	private CityRepository cityRepository;

	/**
	 * Country Respository database.
	 */
	@Autowired
	private CountryRepository countryRepository;

	/**
	 * This rest service method recovers all the informations about city. 
	 * @param String - Name of the country which will be searched for. 
	 * @return List of cities which matches with the informations searched in Json format.
	 */
	@GET
	@Produces("application/json")
	public List<City> getCities(@QueryParam("country") @DefaultValue("France") String country) {
		List<City> cities = cityRepository.findByCountry(country);
		return cities;
	}

	/**
	 * This rest service method include a new city informed by the user. It is required country, which is informed by user,  exist in database.
	 * @param City.class - City object data with country object associated.
	 * @return City.class - Returns the city object with all informations stored and city's id generated.
	 * @throws ValidationException - Validation exception for the data informed by the user.
	 * @throws EntityNotFoundException - Entity not found exception for the data informed by the user.
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createCity(City city) throws ValidationException, EntityNotFoundException {
		if ((city.getName() == null) || ((city.getCountry() == null)
				|| (city.getCountry().getId() == 0 || city.getCountry().getName() == null))) {
			throw new ValidationException("Please, check for all mandatories fields.");
		}

		if (countryRepository.findOne(city.getCountry().getId()) == null) {
			throw new EntityNotFoundException("Please, check if this country is registred first.");

		}

		cityRepository.save(city);
		return Response.status(200).entity(city).build();
	}

}

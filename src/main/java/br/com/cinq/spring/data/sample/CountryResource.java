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

import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.error.ValidationException;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

/**
 * This class represents all the resource rest service available for Country.
 * @author Luiz Felipe
 *
 */
@Component
@Path("/countries")
public class CountryResource {

	/**
	 * Country repository database.
	 */
	@Autowired
	private CountryRepository countryRepository;
	
	/**
	 * This rest service method recovers all the informations about country.
	 * @param Srting - Name or part of the name's country which will be searched for. Type - String.
	 * @return List<Country.class> - Return a list of countries which matches with the informations searched in Json format.
	 */
	@GET 
	@Produces("application/json")
	public List<Country> getCountries(@QueryParam("country") @DefaultValue("France") String country) {
		return countryRepository.findByCountryName(country);
	}
	
	/**
	 * This rest service method include a new country informed by the user. It is required country's name.
	 * @param Country.class - Country's informations.
	 * @return Country.class - Returns all country's informations stored with id associated.
	 * @throws ValidationException - Validation exception for the data informed by the user.
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createCountry(Country country) throws ValidationException {
		if (country.getName()==null){
			throw new ValidationException("Please, check for all mandatories fields.");
		}
		
		countryRepository.save(country);
		return Response.status(200).entity(country).build();
	}
	 
}

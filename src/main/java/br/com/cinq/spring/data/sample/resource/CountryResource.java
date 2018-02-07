package br.com.cinq.spring.data.sample.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

/**
* Resource controller for Country 
* 
* @author luizlucasi
*/


    
@Path("countries")
public class CountryResource {
        	
    	Logger logger = LoggerFactory.getLogger(CountryResource.class);
		
	@Autowired
	CountryRepository countryRepository;

     
	/**
	 * Get Country by ID 
	 *
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountryById(@PathParam("id") Long id) {
		logger.info("Getting country", id); 	
	    Country country = countryRepository.getOne(id);
	    if(country == null  ) {
	    		return  Response.status(Status.NOT_FOUND).build();
	    }
	    return Response.status(201).entity(country).build(); 
	    
	}
	
	/**
	 * Update a Country
	 *  
	 * @param country
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCountry(Country country) {
		logger.info("Updating country"); 
		if (country == null || country.getName() == null) {
			return Response.status(Status.NOT_FOUND).build();
        }
		
		Country countryCheck = countryRepository.getOne(country.getId());
	    if(countryCheck == null  ) {
	    		return  Response.status(Status.NOT_FOUND).build();
	    }
				
		Country result = countryRepository.save(country);
		return Response.status(201).entity(result).build();

	}
	
	
	/**
	 * Delete Country by ID 
	 *
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)		
	public Response deleteCountry(@PathParam("id") Long id) {
		logger.info("Deleting country", id); 
		//check if exists before delete
		Country country = countryRepository.getOne(id);
		if(country == null  ) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		countryRepository.delete(id);
		return Response.noContent().build();
	} 
	

	/**
	 * Create Country
	 *  
	 * @param country
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCountry(Country country) {
		
		if (country == null || country.getName() == null ) {
			return Response.status(Status.BAD_REQUEST).build();
        }
		
		Country result = countryRepository.save(country);
		return Response.status(Status.CREATED).entity(result).build();

	}
	
	/**
	 * Find countries like the @param name
	 * 
	 * @param name
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCountries(@QueryParam("country") String name) {
	
	       List<Country> countries = null; 
	       if(name==null) {
	    	   		logger.info("Retrieving All countries for {}", name); 
	    	   	    countries = countryRepository.findAll();
	       } else {
	    	   		logger.info("Retrieving countries for {}", name);
	    	   		countries = countryRepository.findLikeName(name);
	       }
	      	      	       
	       return Response.ok().entity(countries).build();
	}
	
	
	}
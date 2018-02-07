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

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.repository.CityRepository;



/**
* Resource controller for City 
* 
* @author luizlucasi
*/


    
@Path("cities")
public class CitiesResource {
        	
    	Logger logger = LoggerFactory.getLogger(CitiesResource.class);
		
	@Autowired
	CityRepository cityRepository;

     
	/**
	 * Get city by ID 
	 *
	 * @param id
	 * @return
	 * @throws CityException 
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCityById(@PathParam("id") Long id) {
		logger.info("Getting city ID:" + id); 	
	    City city = cityRepository.findById(id);
	    if(city == null  ) {
	       	return Response.status(Status.NOT_FOUND).build();
	    	}
	    city.getCountry().getName();
	    return Response.ok().entity(city).build();
	    
	}
	
	/**
	 * Update a city
	 *  
	 * @param city
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCity(City city) {
		logger.info("Updating city"); 
		if (city == null || city.getName() == null || city.getCountry() == null) {
			return Response.status(Status.BAD_REQUEST).build();
        }
		
		City cityCheck = cityRepository.getOne(city.getId());
	    if(cityCheck == null  ) {
	       	return Response.status(Status.NOT_FOUND).build();
	    }
				
		City result = cityRepository.save(city);
		return Response.status(Status.CREATED).entity(result).build();

	}
	
	
	/**
	 * Delete city by ID 
	 *
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)		
	public Response deleteCity(@PathParam("id") Long id) {
		logger.info("Deleting city", id); 
		//check if exists before delete
		City city = cityRepository.getOne(id);
		if(city == null  ) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		cityRepository.delete(id);
		return Response.noContent().build();
	} 
	

	/**
	 * Create city
	 *  
	 * @param city
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCity(City city) {
		
		if (city == null || city.getName() == null || city.getCountry() == null) {
			return Response.status(Status.NOT_FOUND).build();
        }
		
		City result = cityRepository.save(city);
		return Response.status(Status.CREATED).entity(result).build();

	}
	
	/**
	 * Find cities like the @param name
	 * 
	 * @param name
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCities(@QueryParam("country") String name) {
	
	       List<City> cities = null; 
	       if(name==null) {
	    	   		return Response.status(Status.BAD_REQUEST).build();
	       }
	    	   
	       logger.info("Retrieving cities for {}", name);
	    	   cities = cityRepository.findLikeCountryName(name);
	       
	       
	       
	       return Response.ok().entity(cities).build();
	}
	
	
	
	
	}
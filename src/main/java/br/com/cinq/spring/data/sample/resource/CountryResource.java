/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cinq.spring.data.sample.resource;

import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mitsui
 */
@Path("/countries")
public class CountryResource {
    
    @Autowired 
    CountryRepository countryRepository;
    
    // Get all countries
    // No paramateters? No problem! We show all the countries available! 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCountries(@QueryParam("name") @DefaultValue("") String country) {
        List<Country> result = countryRepository.findByNameContaining(country);
        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(result).build();
    }

    // Get country by id /countries/id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCountryById(@PathParam("id") Long id) {
        Country country = countryRepository.findOne(id);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(country).build();
    }

    // Update given country
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCountry(Country country) {
        if (country.getId() == null || country.getName() == null) {
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        Country countryFromDb = countryRepository.findOne(country.getId());
        if (countryFromDb == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        
        countryFromDb = countryRepository.save(country);
        return Response.status(Response.Status.CREATED).entity(countryFromDb).build();
    }

    // Delete given country
    @DELETE
    @Path("/id")
    public Response deleteCountry(@PathParam("id") Long id) {
        Country countryFromDb = countryRepository.findOne(id);
        if (countryFromDb == null) 
            return Response.status(Response.Status.NOT_FOUND).build();
        
        countryRepository.delete(countryFromDb);
        return Response.noContent().build();
        
    }
}

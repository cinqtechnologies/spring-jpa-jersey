/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cinq.spring.data.sample.resource;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
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
@Path("/cities")
public class CityResource {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;

    // Get all cities
    // No paramateters? No problem! We show all the cities available! 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCities(@QueryParam("country") @DefaultValue("") String country) {
        List<City> result = cityRepository.findByCountryLike(country);
        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(result).build();
    }

    // Get city by id /cities/id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCityById(@PathParam("id") Long id) {
        City city = cityRepository.findById(id);
        if (city == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(city).build();
    }

    // Update given city
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUpdateCity(City city) {
        City cityFromDb = null;
        if (city.getName() == null || city.getCountry() == null || city.getCountry().getName() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (city.getId() != null) {
            cityFromDb = cityRepository.findById(city.getId());
            if (cityFromDb == null) {
                Country country = countryRepository.findByName(city.getName());
                if (country == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                city.setCountry(country);
            }

        }
       
        cityFromDb = cityRepository.save(city);
        return Response.status(Response.Status.CREATED).entity(cityFromDb).build();
    }

    // Delete given city
    @DELETE
    @Path("/id")
    public Response deleteCity(@PathParam("id") Long id) {
        City cityFromDb = cityRepository.findById(id);
        if (cityFromDb == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cityRepository.delete(cityFromDb);
        return Response.noContent().build();

    }

}

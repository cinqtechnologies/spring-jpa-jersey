package br.com.cinq.spring.data.sample.application;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import br.com.cinq.spring.data.sample.CityResource;
import br.com.cinq.spring.data.sample.CountryResource;
import br.com.cinq.spring.data.sample.error.EntityNotFoundException;
import br.com.cinq.spring.data.sample.error.EntityNotFoundExceptionHandler;
import br.com.cinq.spring.data.sample.error.ValidationExceptionHandler;



/**
 * This class is responsible for Jersey's configuration.
 * @author Luiz Felipe
 *
 */
@Configuration
@ApplicationPath("rest")
public class Config extends ResourceConfig {

	/**
	 * This is the constructor of Jersey's configuration class.
	 */
    public Config() {
        register(CityResource.class);
        register(CountryResource.class);
        register(ValidationExceptionHandler.class);
        register(EntityNotFoundExceptionHandler.class);
        //		packages("br.com.cinq.greet.resource");
        //		property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }


}
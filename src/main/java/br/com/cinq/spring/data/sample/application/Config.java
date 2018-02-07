package br.com.cinq.spring.data.sample.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;

import br.com.cinq.spring.data.sample.resource.CitiesResource;



/**
 * Register Jersey modules
 * @author luizlucasi
 */
@Configuration
@ApplicationPath("rest")
public class Config extends ResourceConfig {

    public Config() {
        register(CitiesResource.class);
        property("jersey.config.beanValidation.enableOutputValidationErrorEntity.server", "true");
        packages("br.com.cinq.spring.data.sample.resource");
        	property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }


}
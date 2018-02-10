package br.com.cinq.spring.data.sample.application;

import br.com.cinq.spring.data.sample.resource.CityResource;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;

//import br.com.cinq.spring.data.resource.SampleResource;
/**
 * Register Jersey modules
 *
 * @author Adriano Kretschmer
 */
@Configuration
@ApplicationPath("rest")
public class Config extends ResourceConfig {

    public Config() {
        register(CityResource.class);
                   // Dear friend applicant(I'm assuming you are doing this test yourself).
                   // If you are reading this beacause you took all of your
                   // hair out and lost your will to live after you upgraded springboot to 1.5
                   // just be happy and NEVER uncoment the line bellow. You'll thank me.
                   // packages("br.com.cinq.spring.data.sample.resource"); -> This single line of code was made by the devil. 
                    property(ServletProperties.FILTER_FORWARD_ON_404, true);
               
    }

}

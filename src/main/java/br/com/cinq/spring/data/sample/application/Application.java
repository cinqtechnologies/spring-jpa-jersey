package br.com.cinq.spring.data.sample.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This class is responsible for launching the application with components mapped.
 * @author Luiz Felipe
 *
 */
@SpringBootApplication
@ComponentScan("br.com.cinq.spring.data.sample")
@EntityScan(basePackages = { "br.com.cinq.spring.data.sample.entity" })
@EnableJpaRepositories("br.com.cinq.spring.data.sample.repository")
public class Application extends SpringBootServletInitializer {

	/**
	 * This method configures the application. 
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /**
     * This method starts the application
     * @param String[] - Arguments parameter.
     */    		
    public static void main(String[] args) {
        new Application().configure(new SpringApplicationBuilder(Application.class)).run(args);
    }
    
    
}

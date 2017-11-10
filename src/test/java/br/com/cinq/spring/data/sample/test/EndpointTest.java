package br.com.cinq.spring.data.sample.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cinq.spring.data.sample.application.Application;
//import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.City;

/**
 * This class test the endpoint.
 * @author Luiz Felipe
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointTest {
	
	/**
	 * Logger global property
	 */
    Logger logger = LoggerFactory.getLogger(EndpointTest.class);

    /**
	 * URL localhost global property
	 */
    private final String localhost = "http://localhost:";

    /**
	 * Port global property
	 */
    @LocalServerPort
    private int port;

    /**
	 * RestTemplate property
	 */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
	 * REST method get service that tests the endpoint. 
	 */
    @Test
    public void testGetCities() throws InterruptedException {
        String country = "France";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.localhost + this.port + "/rest/cities/")
                .queryParam("country", country);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<City[]> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                entity, City[].class);

        		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        Thread.sleep(2000L);

        City[] cities = response.getBody();

        Assert.assertEquals(2, cities.length);

    }
    
    
}

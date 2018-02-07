package br.com.cinq.spring.data.sample.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.City;

/**
 * @author luizlucasi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes =  Application.class, webEnvironment = 
SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointIT {
    Logger logger = LoggerFactory.getLogger(EndpointIT.class);

    private final String localhost = "http://localhost:";

    @Value("${local.server.port}")
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    
  
    
    @Test
    public void testGetCuritibaCity() throws InterruptedException {
      

          HttpHeaders headers = new HttpHeaders();
          headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

         UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.localhost + this.port + "/rest/cities/82");
                

         HttpEntity<?> entity = new HttpEntity<>(headers);

         ResponseEntity<City> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                 entity, City.class);

         Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

         Thread.sleep(2000L);

         City city = response.getBody();

         Assert.assertEquals("Curitiba", city.getName());

    }
    
    @Test
    public void testShouldNotReturnCity() throws InterruptedException {
      

          HttpHeaders headers = new HttpHeaders();
          headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

         UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.localhost + this.port + "/rest/cities/666");
                

         HttpEntity<?> entity = new HttpEntity<>(headers);

         ResponseEntity<City> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                 entity, City.class);

         Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

         

    }
    
}

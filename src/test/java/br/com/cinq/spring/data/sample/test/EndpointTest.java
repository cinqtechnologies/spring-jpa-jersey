package br.com.cinq.spring.data.sample.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.application.model.City;
import br.com.cinq.spring.data.sample.application.model.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit")
public class EndpointTest {
    Logger logger = LoggerFactory.getLogger(EndpointTest.class);

    private final String localhost = "http://localhost:";

    @Value("${local.server.port}")
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Test
    public void testGetCountries() throws InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.localhost + this.port + "/rest/countries/");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Country[]> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                entity, Country[].class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        Thread.sleep(2000L);

        Country[] cities = response.getBody();

        Assert.assertTrue(cities.length > 0);
    }

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
    
    @Test
    public void testCreateCity() throws InterruptedException {
    	City city = new City();
    	city.setName("Blumenau");
    	
    	Integer countryId = 1; //Brazil
 
        String targetURL = this.localhost + this.port + "/rest/country/" + countryId + "/city";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(targetURL);

        HttpEntity<City> entity = new HttpEntity<>(city);

        ResponseEntity<City> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST,
                entity, City.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        Thread.sleep(2000L);

        City newCity = response.getBody();

        Assert.assertEquals("Blumenau", newCity.getName());

    }
    
    @Test
    public void testCreateCityInvalidCountry() throws InterruptedException {
    	City city = new City();
    	city.setName("Frankfurt");
    	
    	Integer countryId = 463252; //Id inexistente
 
        String targetURL = this.localhost + this.port + "/rest/country/" + countryId + "/city";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(targetURL);

        HttpEntity<City> entity = new HttpEntity<>(city);

        ResponseEntity<City> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST,
                entity, City.class);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
    
    @Test
    public void testCreateCountry() throws InterruptedException {
    	Country country = new Country();
    	country.setName("Germany");
    	
        String targetURL = this.localhost + this.port + "/rest/country";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(targetURL);

        HttpEntity<Country> entity = new HttpEntity<>(country);

        ResponseEntity<Country> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST,
                entity, Country.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        Thread.sleep(2000L);

        Country newCountry = response.getBody();

        Assert.assertEquals("Germany", newCountry.getName());

    }
    
}

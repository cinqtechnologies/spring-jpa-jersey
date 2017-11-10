package br.com.cinq.spring.data.sample.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
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
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
/**
 * This class test rest service for City
 * @author Luiz Felipe
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityResourceTest {

	/**
	 * URL localhot global property 
	 */
	private final String localhost = "http://localhost:";

	/**
	 * URL port global property
	 */
	@LocalServerPort
	private int port;
	
	/**
	 * URL path global property.
	 */
	private String path="/rest/cities/"; 

	/**
	 * Test rest template global property
	 */
	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * URL global property
	 */
	private StringBuffer url;
	
	/**
	 * City's repository global property
	 */
	@Autowired
	private CityRepository cityRepository;
	
	/**
	 * Method that will be executed before of each test method.
	 */
	@Before
	public void setUp() {
		this.url=new StringBuffer();
		url.append(localhost).append(port).append(path);
		
	}
	
	/**
	 * REST method post service that saves city information 
	 */
	@Test
	public void postCities(){

		Country country = new Country();
		country.setId(3L);
		country.setName("France");
		City city = new City();
		city.setCountry(country);
		city.setName("Marselha");

		ResponseEntity<City> response = restTemplate.postForEntity(url.toString(), city, City.class);
		City cityResponse = response.getBody();

		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(cityResponse.getId());
		Assert.assertTrue(cityResponse.getId() > 0);
		
	}
	
	/**
	 * REST method get service that recoveries city information or part of it.
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
        Assert.assertNotNull(cities[0].getId());
		Assert.assertTrue(cities[0].getId() > 0);
		Assert.assertNotNull(cities[1].getId());
		Assert.assertTrue(cities[1].getId() > 0);
		Assert.assertEquals("Paris", cities[0].getName());
		Assert.assertEquals("Lyon", cities[1].getName());
        

    }
	
	/**
	 * REST method get service that recoveries city information which is not found 
	 */
	@Test
    public void testGetCitiesNotFound() throws InterruptedException {
        String country = "Germany";

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

        Assert.assertEquals(0, cities.length);
        

    }
}

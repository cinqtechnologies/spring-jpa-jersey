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
import br.com.cinq.spring.data.sample.entity.Country;

/**
 * This class test rest service for Country
 * @author Luiz Felipe
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryResourceTest {

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
	private String path="/rest/countries/";
	
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
	 * Method that will be executed before of each test method.
	 */
	@Before
	public void setUp() {
		this.url=new StringBuffer();
		url.append(localhost).append(port).append(path);
		
	}
	
	/**
	 * REST method post service that saves country information 
	 */
	@Test
	public void postCountries() throws Exception {

		Country country = new Country();
		country.setName("France");
		
		ResponseEntity<Country> response = restTemplate.postForEntity(url.toString(), country,
				Country.class);
		Country countryResponse = response.getBody();

		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(countryResponse.getId());
		Assert.assertTrue(countryResponse.getId() > 0);

	}
	
	/**
	 * REST method get service that recoveries country information 
	 */
	@Test
    public void testGetCountries() throws InterruptedException {
        String country = "France";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url.toString())
                .queryParam("country", country);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Country[]> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                entity, Country[].class);

        		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        Thread.sleep(2000L);

        Country[] countries = response.getBody();

        Assert.assertEquals(1, countries.length);
        Assert.assertNotNull(countries[0].getId());
		Assert.assertTrue(countries[0].getId() > 0);
		Assert.assertEquals(country, countries[0].getName());
        

    }
}

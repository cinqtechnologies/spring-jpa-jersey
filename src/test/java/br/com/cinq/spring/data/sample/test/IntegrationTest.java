package br.com.cinq.spring.data.sample.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit")
@EnableJpaRepositories("br.com.cinq.spring.data.sample.repository")
@EntityScan("br.com.cinq.spring.data.sample.entity")
public class IntegrationTest {
	
    Logger logger = LoggerFactory.getLogger(IntegrationTest.class);
    
    @Autowired
    private CountryRepository dao;

    @Autowired
    private CityRepository cityDao;

    private final String localhost = "http://localhost:";

    @Value("${local.server.port}")
    private int port;
    
    private TestRestTemplate restTemplate = new TestRestTemplate();
    
    String urlGetCities;
    String urlPutLocation;
    
	@Before
	public void setUp() throws Exception {
		this.urlGetCities = this.localhost + this.port + "/rest/cities/";
		this.urlPutLocation = this.localhost + this.port + "/rest/newLocation/";
	}

	@Test
	public void testCRD() throws InterruptedException {
		
		//Get how many cities there are 
		Assert.assertNotNull(dao);

        long beforeList = dao.count();
        
        logger.info("Quantidade de países: " + beforeList);
		
        Country country = new Country("Chile");
        country.setId(17);
        City city = new City("Santiago", country);
        city.setId(17);
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.urlPutLocation);

        HttpEntity<?> entity = new HttpEntity<>(city);

        logger.info("Chamada: " + builder.build().encode().toUri());
        ResponseEntity<City> responsePut = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.PUT,
                entity, City.class);

        Assert.assertEquals(HttpStatus.OK, responsePut.getStatusCode());

        Thread.sleep(2000L);

        city = responsePut.getBody();

        Assert.assertTrue(beforeList < dao.count());
        Assert.assertEquals("Santiago", city.getName());
        
        //getCities()
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        builder = UriComponentsBuilder.fromHttpUrl(urlGetCities).queryParam("country", country.getName());

        entity = new HttpEntity<>(headers);

        logger.info("Chamada: " + builder.build().encode().toUri());
        ResponseEntity<City[]> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                entity, City[].class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        Thread.sleep(2000L);

        City[] cities = response.getBody();

        Assert.assertEquals(1, cities.length);

        cityDao.delete(city);
        dao.delete(country);
        
        response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                entity, City[].class);
        
        Assert.assertEquals(beforeList, dao.count());
		
	}

}

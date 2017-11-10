package br.com.cinq.spring.data.repository.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

/**
 * This class is responsible to execute tests for City's repository.
 * @author Luiz Felipe
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityRepositoryTest {

	/**
	 * City's repository property.
	 */
	@Autowired
	private CityRepository cityRepository;

	/**
	 * Country's repository property.
	 */
	@Autowired
	private CountryRepository countryRepository;

	/**
	 * City's global property
	 */
	private City city;

	/**
	 * Country's global property
	 */
	private Country country;

	/**
	 * Method that will be executed before of each test method.
	 */
	@Before
	public void setUp() {
		Assert.assertNotNull(countryRepository);

		Assert.assertTrue(countryRepository.count() > 0);

		country = countryRepository.findOne(1L);

		Assert.assertNotNull(country);

		city = new City();

		city.setCountry(country);
		
		Assert.assertNotNull(cityRepository);

		Assert.assertTrue(cityRepository.count() > 0);
	}

	/**
	 * Method which will be executed after of each test method.
	 */
	@After
	public void destroy() {
		Assert.assertNotNull(country);

		country = null;

		Assert.assertNull(country);

		Assert.assertNotNull(city);

		city = null;

		Assert.assertNull(city);
	}

	/**
	 * This method test successful city information storing.
	 */
	@Test
	public void testSaveCity() {

		city.setName("Amapa");

		cityRepository.save(city);

		Assert.assertNotNull(city.getId());

		Assert.assertTrue(city.getId() > 0);
		
		cityRepository.delete(city);
	}

	/**
	 * This method test successful city information updating
	 */
	@Test
	public void testUpdateCity() {

		city.setName("Amapa");

		cityRepository.save(city);

		Assert.assertNotNull(city.getId());

		Assert.assertTrue(city.getId() > 0);

		long id = city.getId();

		city.setName("Toulouse");

		cityRepository.save(city);

		Assert.assertNotNull(city.getId());

		Assert.assertTrue(city.getId() > 0);

		Assert.assertTrue(city.getId() == id);

		Assert.assertEquals("Toulouse", city.getName());
		
		cityRepository.delete(city);

	}
	
	/**
	 * This method test successful city information deleting.
	 */
	@Test
	public void testDeleteCity() {

		city.setName("Amapa");

		cityRepository.save(city);

		Assert.assertNotNull(city.getId());

		Assert.assertTrue(city.getId() > 0);

		cityRepository.delete(city);
	}

	/**
	 * This method test successful city information searching.
	 */
	@Test
	public void testSearchCity() {

		city.setName("Amapa");

		cityRepository.save(city);

		Assert.assertNotNull(city.getId());

		Assert.assertTrue(city.getId() > 0);

		long id = city.getId();

		City city_2 = cityRepository.findOne(id);

		Assert.assertNotNull(city_2.getId());

		Assert.assertTrue(city_2.getId() > 0);

		Assert.assertTrue(city_2.getId() == id);

		Assert.assertEquals("Amapa", city_2.getName());
		
		cityRepository.delete(city);

	}

	/**
	 * This method test city information searching by country
	 */
	@Test
	public void testSearchCitiesByCountry() {


		List<City> cities = cityRepository.findByCountry(country.getName()); 

		Assert.assertNotNull(cities);
 
		Assert.assertTrue(cities.size() == 4);

		city.setName("Amapa");

		cityRepository.save(city); 
 
		Assert.assertNotNull(city.getId());

		Assert.assertTrue(city.getId() > 0);

		cities = cityRepository.findByCountry(country.getName());

		Assert.assertNotNull(cities);

		Assert.assertTrue(cities.size() == 5);
		
		cityRepository.delete(city);
	}
}

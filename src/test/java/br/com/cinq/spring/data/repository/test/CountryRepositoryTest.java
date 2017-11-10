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
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

/**
 * This class is responsible to execute tests for Country's repository.
 * @author Luiz Felipe
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryRepositoryTest {

	/**
	 * Country's repository property
	 */
	@Autowired
	private CountryRepository countryRepository;

	/**
	 * Country global property
	 */
	private Country country;

	/**
	 * Method that will be executed before of each test method.
	 */
	@Before
	public void setUp() {
		Assert.assertNull(country);

		country = new Country();

		Assert.assertNotNull(country);

		Assert.assertNotNull(countryRepository);

		Assert.assertTrue(countryRepository.count() > 0);

	}

	/**
	 * Method that will be executed after of each test method.
	 */
	@After
	public void destroy() {
		Assert.assertNotNull(country);
		country = null;
		Assert.assertNull(country);
	}

	/**
	 * This method test successful country information storing.
	 */
	@Test
	public void testSaveCountry() {

		country.setName("Germany");

		Assert.assertNull(country.getId());

		countryRepository.save(country);

		Assert.assertNotNull(country.getId());

		Assert.assertTrue(country.getId() > 0);
	}

	/**
	 * This method test successful country information updating
	 */
	@Test
	public void testUpdateCountry() {

		country.setName("Germany");

		Assert.assertNull(country.getId());

		countryRepository.save(country);

		Assert.assertNotNull(country.getId());

		long id = country.getId();

		country.setName("Japan");

		countryRepository.save(country);

		Assert.assertNotNull(country.getId());

		Assert.assertTrue(country.getId() > 0);

		Assert.assertTrue(country.getId() == id);

		Assert.assertEquals("Japan", country.getName());

	}
	
	/**
	 * This method test successful country information deleting.
	 */
	@Test
	public void testDeleteCountry() {

		country.setName("Germany");

		countryRepository.save(country);

		Assert.assertNotNull(country.getId());

		Assert.assertTrue(country.getId() > 0);

		countryRepository.delete(country);
	}

	/**
	 * This method test successful country information searching.
	 */
	@Test
	public void testSearchCountry() {

		country.setName("Germany");

		countryRepository.save(country);

		Assert.assertNotNull(country.getId());

		Assert.assertTrue(country.getId() > 0);

		long id = country.getId();

		Country country_2 = countryRepository.findOne(id);

		Assert.assertNotNull(country_2.getId());

		Assert.assertTrue(country_2.getId() > 0);

		Assert.assertTrue(country_2.getId() == id);

		Assert.assertEquals("Germany", country_2.getName());

		countryRepository.delete(country);
	}
	
	/**
	 * This method test successful country information searching by name.
	 */
	@Test
	public void testSearchCountryByName() {

		List<Country> countries = countryRepository.findByCountryName("a");

		Assert.assertNotNull(countries);

		Assert.assertTrue(countries.size() == 3);

		country.setName("Germany");

		countryRepository.save(country);

		Assert.assertNotNull(country.getId());

		Assert.assertTrue(country.getId() > 0);

		countries = countryRepository.findByCountryName("a");

		Assert.assertNotNull(countries);

		Assert.assertTrue(countries.size() == 4);
 
	}
	

}
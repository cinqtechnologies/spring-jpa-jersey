package br.com.cinq.spring.data.repository.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=WebEnvironment.RANDOM_PORT)
//@IntegrationTest("server.port=9000")
@ActiveProfiles("unit")
@EnableJpaRepositories("br.com.cinq.spring.data.sample.repository")
@EntityScan("br.com.cinq.spring.data.sample.entity")
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository dao;

    @Test
    public void testGelAllCountries() {

        Assert.assertNotNull(dao);

        long count = dao.count();

        Assert.assertTrue(count > 0);

        List<Country> countries = (List<Country>) dao.findAll();

        Assert.assertEquals((int) count, countries.size());
    }

    @Test
    public void testFindOneCountry() {

        Assert.assertNotNull(dao);

        List<Country> countries = dao.findLikeName("Fra");

        Assert.assertEquals(1, countries.size());

    }

    @Test
    public void testSaveCountryWithCities() {
        Assert.assertNotNull(dao);
    	
    	Country country = new Country("Inglaterra");
    	List<City> cities = new ArrayList<City>();
    	cities.add(new City("Londres", null));
    	
    	country.setCities(cities);
    	
    	dao.save(country);
    	List<Country> countries = dao.findLikeName("Ing");

    	Assert.assertEquals(1, countries.size());
    }
}

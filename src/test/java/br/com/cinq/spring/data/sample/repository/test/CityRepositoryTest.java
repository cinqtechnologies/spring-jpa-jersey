package br.com.cinq.spring.data.sample.repository.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;


/**
 * @author luizlucasi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes =  Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityRepositoryTest {

      @Autowired
      private CityRepository dao;
      
      
    @Test
    public void testFindById() {

          Assert.assertNotNull(dao);
        
          Assert.assertTrue(dao.count()>0);

          Country country = new Country();
          country.setId(3L); // Should be France
          

          List<City> list = dao.findByCountry(country);

          Assert.assertEquals(2, list.size());
    }
    
    @Test
    public void testFindByCountry() {

        Assert.assertNotNull(dao);
      
        Assert.assertTrue(dao.count()>0);
       
        List<City> list = dao.findLikeCountryName("Uni");

        Assert.assertEquals(3, list.size());
  }
}

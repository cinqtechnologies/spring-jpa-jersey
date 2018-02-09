package br.com.cinq.spring.data.service.test;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import br.com.cinq.spring.data.sample.service.CityService;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @InjectMocks
    private CityService service;
    
    @Mock
    private CityRepository repository;
    
    @Mock
    private CountryRepository countryRepository;

    @Test
    public void getCities() {
        Assert.assertNotNull(service);
        
        List<City> c = getList();
        when(repository.findAll()).thenReturn(c);
        List<City> list = service.getCities();

        Assert.assertEquals(c.size(), list.size());
    }
    
    @Test
    public void createCity() {

        Assert.assertNotNull(service);
        String name = "name", countryName = "country";
        Country c = new Country();
        
        when(countryRepository.findByName(Mockito.eq(countryName))).thenReturn(c);
        service.add(name, countryName);

        verify(repository).save(any(City.class));
    }

	private List<City> getList() {
		List<City> l = new ArrayList<>();
		l.add(new City());
		l.add(new City());		
		return l;
	}
}

package br.com.cinq.spring.data.service.test;

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
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.service.CityService;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @InjectMocks
    private CityService service;
    
    @Mock
    private CityRepository repository;

    @Test
    public void getCities() {

        Assert.assertNotNull(service);
        
        List<City> c = getList();
        Mockito.when(repository.findAll()).thenReturn(c);
        List<City> list = service.getCities();

        Assert.assertEquals(c.size(), list.size());
    }

	private List<City> getList() {
		List<City> l = new ArrayList<>();
		l.add(new City());
		l.add(new City());		
		return l;
	}
}

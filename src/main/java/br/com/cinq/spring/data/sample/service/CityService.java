package br.com.cinq.spring.data.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<City> getCities(){
		return cityRepository.findAll();
	}
	
	public List<City> getCities(String country){
		return cityRepository.findLikeCountryName(country);
	}

	
	public void add(String name, String countryName) {
		Country c = countryRepository.findByName(countryName);
		City city = new City();
		city.setCountry(c);
		city.setName(name);
		cityRepository.save(city);
	}
	
}

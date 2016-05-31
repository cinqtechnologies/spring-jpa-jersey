package br.com.cinq.spring.data.sample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import br.com.cinq.spring.data.sample.service.CityService;

/**
 * 
 * @author Douglas Cardoso
 *
 */
@Component
public class CityServiceImpl implements CityService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> getCities(String countryName) {
		List<City> cities = new ArrayList<>();
		List<Country> countries = countryRepository.findLikeName(countryName);

		for (Country country : countries) {
			cities.addAll(cityRepository.findByCountry(country));
		}

		return cities;
	}

}

package br.com.cinq.spring.data.sample.service;

import java.util.List;

import br.com.cinq.spring.data.sample.entity.City;

public interface CityService {
	public List<City> getByCountry(String countryName);
}

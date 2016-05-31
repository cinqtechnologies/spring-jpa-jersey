package br.com.cinq.spring.data.sample.service;

import java.util.List;

import br.com.cinq.spring.data.sample.entity.City;

/**
 * 
 * @author Douglas Cardoso
 *
 */
public interface CityService {

	/**
	 * 
	 * @param countryName
	 * @return {@link List}
	 */
	public List<City> getCities(String countryName);

}

package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

/**
 * 
 * @author Douglas Cardoso
 *
 */
public interface CityRepository extends CrudRepository<City, Long> {

	/**
	 * 
	 * @param country
	 * @return {@link List}
	 */
	public List<City> findByCountry(Country country);

}

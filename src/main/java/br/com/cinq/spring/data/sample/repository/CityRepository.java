package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

public interface CityRepository extends CrudRepository<City, Long> {

	@Query(value = "select city.* from City city join Country country on country.id=city.country_id and country.name like %?1% ", nativeQuery = true)
	List<City> findByCountryNameLike(String name);

	List<City> findByCountry(Country country);
}
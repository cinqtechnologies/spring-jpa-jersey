package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

public interface CityRepository extends CrudRepository<City, Long>{

	List<City> findByCountry(Country country);
	
	@Query("Select c from City c where c.country.name like %:name%")
	List<City> findByCountryName(@Param("name") String name);
}

package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

	List<City> findByCountry(Country country);
	
	@Query("SELECT ct, co FROM City ct "
			+ "INNER JOIN ct.country co ON co.id = ct.country.id "
			+ "WHERE ct.country = :cy")
    List<City> findCityByCountry(@Param("cy") Country country);
	
}
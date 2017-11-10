package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cinq.spring.data.sample.entity.City;

/**
 * This interface represents City's repository.
 * @author Luiz Felipe
 *
 */
public interface CityRepository extends CrudRepository<City, Long> {

	/**
	 * This method specifies a searching for cities by country's name or part of it
	 * @param String - Country's name.
	 * @return List<City> - List of cities associated to the country informed by user.
	 */
   @Query("select c from City c left join fetch c.country co where co.name like %:name%")
   List<City> findByCountry(@Param("name") String name);


}

package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cinq.spring.data.sample.entity.Country;

/**
 * This interface represents Country's repository.
 * @author Luiz Felipe
 *
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

	/**
	 * This method specifies a searching for countries by name or part of the name
	 * @param String - Country's name or part of the name.
	 * @return List<Country> - List of countries.
	 */
	@Query("select c from Country c  where c.name like %:name%")
	List<Country> findByCountryName(@Param("name") String name);

}

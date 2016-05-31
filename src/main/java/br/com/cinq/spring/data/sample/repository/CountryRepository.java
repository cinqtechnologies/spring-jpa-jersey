package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cinq.spring.data.sample.entity.Country;

/**
 * 
 * @author Douglas Cardoso
 *
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

	public List<Country> findAll();

	/**
	 * 
	 * @param like
	 * @return {@link List}
	 */
	@Query(value = "SELECT c FROM Country c where c.name like %?1%")
	public List<Country> findLikeName(String like);

}

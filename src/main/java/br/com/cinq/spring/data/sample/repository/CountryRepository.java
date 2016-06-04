package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cinq.spring.data.sample.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{

	List<Country> findByName(String name);

	@Query("Select c from Country c where c.name like %:name%")
	List<Country> findLikeName(@Param("name") String name);

	List<Country> findAll();
}

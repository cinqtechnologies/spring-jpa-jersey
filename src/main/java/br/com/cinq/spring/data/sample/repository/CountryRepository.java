package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cinq.spring.data.sample.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {

	@Query("select c from Country c where c.name like %?1%")
	List<Country> findByNameLike(String name);
}
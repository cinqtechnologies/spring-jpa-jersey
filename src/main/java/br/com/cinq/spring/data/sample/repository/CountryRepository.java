package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.entity.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

	@Query("SELECT co FROM Country co"
			+ " WHERE co.name like %:name%")
    List<Country> findLikeName(@Param("name") String name);
}
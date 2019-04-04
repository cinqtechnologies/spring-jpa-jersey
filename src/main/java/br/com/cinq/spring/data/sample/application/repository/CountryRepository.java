package br.com.cinq.spring.data.sample.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.application.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	@Query("SELECT co FROM Country co WHERE co.name like %:name%")
	public List<Country> findLikeName(@Param("name") String name);

}

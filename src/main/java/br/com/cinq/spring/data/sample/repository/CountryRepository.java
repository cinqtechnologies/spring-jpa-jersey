package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	public Country findByName(String name);

	@Query("select c from Country c where c.name like %:name%")
	public List<Country> findLikeName(@Param("name") String name);
}

package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	public List<City> findByCountry(Country c);
	
	@Query("select c from City c where c.country in (select co from Country co where co.name like %:name%)")
	public List<City> findLikeCountryName(@Param("name") String countryName);
}

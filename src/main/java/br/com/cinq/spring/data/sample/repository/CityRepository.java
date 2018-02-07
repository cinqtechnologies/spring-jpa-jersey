package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

/**
 * @author luizlucasi
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
		
	  /**
	 *  Find cities by Country
	 * @param country
	 * @return
	 */
	//@Query("Select c from City c where c.country.id = :#{#country.id}")
	@Query("Select c from City c JOIN FETCH c.country co WHERE c.country.id = :#{#country.id}")
	List<City> findByCountry(@Param("country") Country country);
	
	
	@Query("Select c from City c JOIN FETCH c.country co WHERE c.country.name like %:country_name%")
    List<City> findLikeCountryName(@Param("country_name")String countryName);
	
	@Query("Select c from City c JOIN FETCH c.country co WHERE c.id = :#{#id}")
	City findById(@Param("id") Long id);
		  
}

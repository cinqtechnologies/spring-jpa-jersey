package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.entity.Country;


/**
 * @author luizlucasi
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	
	
	/**
	 * 
	 *  Get a list of city like the cityName param
	 * 
	 * @param cityName
	 * @return
	 */
	@Query("Select c from Country c where c.name like %:city_name%")
    List<Country> findLikeName(@Param("city_name")String cityName);
	
	

}

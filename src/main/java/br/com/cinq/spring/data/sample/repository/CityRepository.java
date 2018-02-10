/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cinq.spring.data.sample.repository;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mitsui
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
 
    @Query("select c from City c left join fetch c.country ct where ct.name like %:countryName%")
    List<City> findByCountryLike(@Param("countryName") String countryName);
    
    @Query("select c from City c left join fetch c.country ct where ct.id = :#{#country.id}")
    List<City> findByCountry(@Param("country") Country country );
    
    @Query("select c from City c left join fetch c.country ct where c.id = :id")
    City findById(@Param("id")Long id);
}

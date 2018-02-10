/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cinq.spring.data.sample.repository;

import br.com.cinq.spring.data.sample.entity.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author mitsui
 */
@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    
    //%containing%
    List<Country> findByNameContaining(String name);
    Country findByName(String name);
    
}
 

package br.com.cinq.spring.data.sample.repository;

import br.com.cinq.spring.data.sample.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gustavo on 6/8/16.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByNameContaining(String name);

}

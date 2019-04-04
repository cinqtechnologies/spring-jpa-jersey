package br.com.cinq.spring.data.sample.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.application.model.City;
import br.com.cinq.spring.data.sample.application.model.Country;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	public List<City> findByCountry(Country c);
}

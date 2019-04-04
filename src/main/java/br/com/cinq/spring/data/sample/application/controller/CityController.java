package br.com.cinq.spring.data.sample.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cinq.spring.data.sample.application.exception.ResourceNotFoundException;
import br.com.cinq.spring.data.sample.application.model.City;
import br.com.cinq.spring.data.sample.application.repository.CityRepository;
import br.com.cinq.spring.data.sample.application.repository.CountryRepository;

@RestController
@RequestMapping(value = "/rest")
public class CityController {

	@Inject
	private CityRepository cityRepository;

	@Inject
	private CountryRepository countryRepository;

	@GetMapping("/cities")
	public List<City> findAll(@RequestParam(value = "country", defaultValue = "", required = false) String country) {
		return cityRepository.findAll().stream().filter(city -> city.getCountry().getName().startsWith(country))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@PostMapping("/country/{countryId}/city")
	public City createCity(@PathVariable Integer countryId, @RequestBody City city) {
		return countryRepository.findById(countryId).map(country -> {
			city.setCountry(country);
			return cityRepository.save(city);
		}).orElseThrow(() -> new ResourceNotFoundException("Country with id " + countryId + " not found!"));
	}
}

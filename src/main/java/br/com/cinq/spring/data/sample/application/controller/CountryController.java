package br.com.cinq.spring.data.sample.application.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cinq.spring.data.sample.application.model.Country;
import br.com.cinq.spring.data.sample.application.repository.CountryRepository;

@RestController
@RequestMapping(value = "/rest")
public class CountryController {

	@Inject
	private CountryRepository countryRepository;

	@GetMapping("/countries")
	public List<Country> findAll() {
		return countryRepository.findAll();
	}
	
	@PostMapping("/country")
	public Country createCountry(@RequestBody Country country) {
		return countryRepository.save(country);
	}
}

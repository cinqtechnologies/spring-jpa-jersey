package br.com.cinq.spring.data.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> getCities(){
		return cityRepository.findAll();
	}
	
	public List<City> getCities(String country){
		return cityRepository.findLikeCountryName(country);
	}
	
}

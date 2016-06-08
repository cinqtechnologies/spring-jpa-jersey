package br.com.cinq.spring.data.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import jersey.repackaged.com.google.common.collect.Lists;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public List<City> getByCountry(String countryName) {
		if (countryName == null) {
			return Lists.newArrayList(cityRepository.findAll());
		} 
		return cityRepository.findByCountryNameLike(countryName);
	}

}

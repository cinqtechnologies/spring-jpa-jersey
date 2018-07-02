package br.com.cinq.spring.data.resource;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

@RestController
@EnableJpaRepositories("br.com.cinq.spring.data.sample.repository")
@EntityScan("br.com.cinq.spring.data.sample.entity")
@RequestMapping(value = "/rest")
public class SampleResource {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    @Autowired
    private CountryRepository countryDao;
    
    @Autowired
    private CityRepository cityDao;

	@RequestMapping("/cities")
	public City[] getCities(@RequestParam(value="country") String countryName) {
		List<Country> countries = countryDao.findLikeName(countryName);
		
		List<City> cities = new ArrayList<City>();
		
		for (Country country : countries) {
			List result = cityDao.findCityByCountry(country);
			for(int i = 0; i < result.size(); i++) {
				Object[] obj = (Object[]) result.get(i);
				cities.add((City) obj[0]);
			}
		}
		
		log.info("SampleResource getCities --- End" + cities);
		
		return cities.toArray(new City[cities.size()]);
	}
	
	@RequestMapping(value = "/newLocation", method = RequestMethod.PUT)
	public City newLocation(@RequestBody City cidade) {
		log.info("SampleResource saveLocation --- Start");
		log.info("Cidade Param: " + cidade.getName());
		City cidadeSalva = null;
		
		if(cidade.getCountry() != null) {
			countryDao.save(cidade.getCountry());
			cidadeSalva = cityDao.save(cidade);
		} else {
			log.info("Cidade não pertence a um país! - Cidade: " + cidade.getName());
		}
		log.info("SampleResource saveLocation --- End" + cidadeSalva);
		
		return cidadeSalva;
	}
}
package br.com.cinq.spring.data.sample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class City {	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	
	@ManyToOne
	private Country country;
	
	public City() {};
	
	public City(String name, Country country)  {
		this.name = name;
		this.country = country;
	}

	@Override
    public String toString() {
        return String.format("City[id=%d, name='%s', country='%s']", id, name,country.getName());
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}

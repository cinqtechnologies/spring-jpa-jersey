package br.com.cinq.spring.data.sample.entity;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Douglas Cardoso
 *
 */
@Entity
public class City {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	private Country country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

}

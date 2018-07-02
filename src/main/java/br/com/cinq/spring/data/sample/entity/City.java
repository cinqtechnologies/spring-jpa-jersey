package br.com.cinq.spring.data.sample.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CITY database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8046301926626094205L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch = FetchType.LAZY)
	private Country country;

	public City() {
	}
	
	public City(String name, Country country) {
		this.name = name;
		this.country = country;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
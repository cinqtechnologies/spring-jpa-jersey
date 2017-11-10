package br.com.cinq.spring.data.sample.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents Country Entity.
 * @author Luiz Felipe
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

	/**
	 * Country's id property
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("id")
	private Long id;
	
	/**
	 * Country's name property
	 */
	@Column(name="name", nullable=false)
	@JsonProperty("name")
	private String name;
	
	/**
	 * List of country's cities 
	 */
	@OneToMany(mappedBy="country", cascade={CascadeType.PERSIST})
	@JsonIgnore
	private List<City> cities;

	/**
	 * This method returns Country's id.
	 * @return Long - Country's id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method sets Country's id.
	 * @param Long - Country's id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method returns Country's name.
	 * @return String - Country's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets Country's name.
	 * @param String - Country's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns country's list of city
	 * @return List<City.class> - Country's list of city
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * This method sets country's list of city
	 * @param List<City.class> - Country's list of city.
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		 return 3 * name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)                return false;
	    if(!(obj instanceof Country)) return false;

	    Country other = (Country) obj;
	    if(this.id != other.id)      return false;
	    if(! this.name.equals(other.name)) return false;

	    return true;
	}
	
}

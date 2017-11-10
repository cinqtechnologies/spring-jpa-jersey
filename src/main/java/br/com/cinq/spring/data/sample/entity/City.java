package br.com.cinq.spring.data.sample.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * This class represents City Entity.
 * @author Luiz Felipe
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

	/**
	 * City's id property
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("id")
	private Long id;
	
	/**
	 * City's name property.
	 */
	@Column(name="name", nullable=false)
	@JsonProperty("name")
	private String name;
	
	/**
	 * Country of the city property.
	 */
	@ManyToOne(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="country_id")
	@JsonProperty("country")
	private Country country;

	/**
	 * This method returns City's id.
	 * @return Long - City's id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method sets City's id.
	 * @param Long - City's id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method returns City's name.
	 * @return String - City's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets City's name.
	 * @param String - City's name.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * This method returns City's country information.
	 * @return Country.class - Country's information
	 */
	public Country getCountry() {
		return country;
	}

	
	/**
	 * This method sets City's country information.
	 * @param Country.class - Country's information.
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + "]";
	}

		
	@Override
	public int hashCode() {
		 return 4 * name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)                return false;
	    if(!(obj instanceof City)) return false;

	    City other = (City) obj;
	    if(this.id != other.id)      return false;
	    if(! this.name.equals(other.name)) return false;

	    return true;
	}
	 	

	
}


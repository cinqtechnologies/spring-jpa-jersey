package br.com.cinq.spring.data.sample.resource;

public class CityCreationRequest {
	private String name;
	private String countryName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}

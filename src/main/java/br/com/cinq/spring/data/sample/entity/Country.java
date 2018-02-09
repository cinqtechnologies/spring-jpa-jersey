package br.com.cinq.spring.data.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "Country")
public class Country {
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof City) {
			return this.toString().equals(obj.toString());
		}
		return false;
	}
	
	public String toString(){
		return new ToStringBuilder(this).
			       append("id", id).
			       append("name", name).
			       toString();
	}
}

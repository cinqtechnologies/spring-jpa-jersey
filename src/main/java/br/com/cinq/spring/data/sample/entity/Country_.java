package br.com.cinq.spring.data.sample.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-07-02T04:42:13.978-0300")
@StaticMetamodel(Country.class)
public class Country_ {
	public static volatile SingularAttribute<Country, Integer> id;
	public static volatile SingularAttribute<Country, String> name;
	public static volatile ListAttribute<Country, City> cities;
}

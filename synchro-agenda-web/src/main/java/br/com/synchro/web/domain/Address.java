package br.com.synchro.web.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Address.java
 * Criado em Sep 21, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@XmlRootElement
public class Address{	
	
	private Long id;	
	
	private String street;	
	
	private Integer number;	
	
	private String district;
	
	private String city;	
	
	private String zipcode;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}	

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", number="
				+ number + ", district=" + district + ", city=" + city
				+ ", zipcode=" + zipcode + "]";
	}

	

			
}

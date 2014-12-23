package br.com.synchro.web.domain;

import java.io.Serializable;

/**
 * 
 * GoogleContact.java
 * Criado em Sep 27, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GoogleContact implements Serializable{

	private Long model;
	
	private String name;
	
	private String surname;
	
	private String phone;
	
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Long getModel() {
		return model;
	}

	public void setModel(Long model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "GoogleContact [model=" + model + ", name=" + name
				+ ", surname=" + surname + ", phone=" + phone + ", email="
				+ email + "]";
	}
	
	
}

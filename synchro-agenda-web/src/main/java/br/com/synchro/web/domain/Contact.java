package br.com.synchro.web.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * 
 * Contact.java
 * Criado em Sep 21, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@SuppressWarnings("serial")
@XmlRootElement
public class Contact implements Serializable {	
	
	private Long id;	
	
	private String name;
	
	private String surname;
	
	private String email;	
	
	private String phone;	
	
	private Address address;
	
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", user=" + user + "]";
	}

	

	
	

	
	
}

package br.com.synchro.web.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.synchro.web.domain.Address;
import br.com.synchro.web.domain.Contact;
import br.com.synchro.web.domain.GoogleContact;
import br.com.synchro.web.domain.User;
import br.com.synchro.web.exception.ServiceException;
import br.com.synchro.web.service.ContactService;
import br.com.synchro.web.service.GoogleContactService;
import br.com.synchro.web.util.FacesUtil;

/**
 * 
 * GoogleContactView.java
 * Criado em Sep 27, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@Component("googleContactView")
@Scope("view")
@SuppressWarnings("serial")
public class GoogleContactView implements Serializable{
	
	private static Logger logger = Logger.getLogger(GoogleContactView.class);

	@Autowired
	private GoogleContactService googleContactService;
	
	@Autowired
	private ContactService contactService;
	
	private String user;
	
	private String password;
	
	private List<GoogleContact> contacts;
	
	private List<GoogleContact> selectedContacts;
	
	private User userModel;
	
	@PostConstruct
	public void init(){
		this.contacts = new ArrayList<GoogleContact>();	
		this.userModel = (User) FacesUtil.getSession().getAttribute("loggedObjUser");
	}
	
	public void importGmailContacts(){
		try{
			this.contacts = this.googleContactService.getAllContacts(this.user, this.password);
		}catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		}
	}
	
	public String importToContacts(){
		try{
			if(this.selectedContacts != null && this.selectedContacts.size() > 0){
				for(GoogleContact var : this.selectedContacts){
					Contact contact = new Contact();
					contact.setName(var.getName());
					contact.setSurname(var.getSurname());
					contact.setEmail(var.getEmail());
					contact.setPhone(var.getPhone());
					
					Address address = new Address();
					contact.setAddress(address);
					contact.setUser(this.userModel);
					
					this.contactService.addContact(contact);
				}			
			}
		}catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		}
		return "import_success";
	}
	
	public String goToManage(){
		return "go-to-manage";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GoogleContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<GoogleContact> contacts) {
		this.contacts = contacts;
	}

	public List<GoogleContact> getSelectedContacts() {
		return selectedContacts;
	}

	public void setSelectedContacts(List<GoogleContact> selectedContacts) {
		this.selectedContacts = selectedContacts;
	}	
}

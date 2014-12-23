package br.com.synchro.web.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.synchro.web.domain.Address;
import br.com.synchro.web.domain.Cep;
import br.com.synchro.web.domain.Contact;
import br.com.synchro.web.domain.User;
import br.com.synchro.web.domain.dto.ContactDTO;
import br.com.synchro.web.exception.ServiceException;
import br.com.synchro.web.service.ContactService;
import br.com.synchro.web.util.FacesUtil;

/**
 * 
 * ContactView.java
 * Criado em Sep 25, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@Component("contactView")
@Scope("view")
@SuppressWarnings("serial")
public class ContactView implements Serializable {
	
	private static Logger logger = Logger.getLogger(ContactView.class);
	
	@Autowired
	private ContactService contactService;
	
	private Contact contact;	
	
	private User user;
	
	private Address address;
	
	private String loggedUser;
	
	private List<Contact> list;	
	
	private Long selectedContactId;
	
	private String flagEdita;
	
	private String buscaNome;
	
	private ContactDTO contactDTO;
	
	@PostConstruct
	public void init(){
		this.user = (User) FacesUtil.getSession().getAttribute("loggedObjUser");
		this.loggedUser = this.user.getName() + " " + this.user.getSurname();		
		this.initContact();
		this.buscaNome = "";
	}
	
	public String addContact(){
		try{
			this.contactService.addContact(this.contact);
			this.initContact();
		}catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		}
		return "add-contact-success";
	}
	
	public String updateContact(){
		try{
			this.initContact();
			
			if(this.contactDTO != null){
				this.contact.setId(this.contactDTO.getId());
				this.contact.setName(this.contactDTO.getName());
				this.contact.setSurname(this.contactDTO.getSurname());
				this.contact.setEmail(this.contactDTO.getEmail());
				this.contact.setPhone(this.contactDTO.getPhone());
				
				this.contact.getAddress().setCity(this.contactDTO.getCity());
				this.contact.getAddress().setDistrict(this.contactDTO.getDistrict());
				this.contact.getAddress().setNumber(this.contactDTO.getNumber());
				this.contact.getAddress().setStreet(this.contactDTO.getStreet());
				this.contact.getAddress().setZipcode(this.contactDTO.getZipcode());
				
				this.contactService.updateContact(this.contact);
			}
		}catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		}
		return "update-contact-success";
	}
	
	public List<ContactDTO> getContactsFromUser(){
		try{
			if(this.buscaNome != null && !this.buscaNome.equals("")){
				return this.contactService.searchByName(this.buscaNome, this.user.getUsername());
			}else{
				return this.contactService.loadByUsername(this.user.getUsername());				
			}
		}catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		}
		return null;
	}
	
	
	
	public void removeContact(){
		try{
			if(this.contactDTO != null){
				this.contactService.deleteContact(this.contactDTO.getId().toString());
			}
		}catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		}
	}
	
	public void searchCep(String cepStr){
		try{
			Cep cep = this.contactService.searchCep(cepStr);
			this.getContact().getAddress().setCity(cep.getLocalidade());
			this.getContact().getAddress().setDistrict(cep.getBairro());
			this.getContact().getAddress().setStreet(cep.getLogradouro());
		}catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		}
	}
	
	public void searchEditCep(String cepStr){
		try{
			Cep cep = this.contactService.searchCep(cepStr);
			this.contactDTO.setCity(cep.getLocalidade());
			this.contactDTO.setDistrict(cep.getBairro());
			this.contactDTO.setStreet(cep.getLogradouro());		
		}catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		}
	}
	
	public void initContact(){
		this.contact = new Contact();
		this.address = new Address();
		this.contact.setAddress(this.address);
		this.contact.setUser(this.user);
	}
	
	public void searchByName(){		
	}
	
	public String goToEditForm(){		
		return "go-to-edit-form";
	}
	
	public String goToFormAdd(){
		return "go-to-add-form";
	}
	
	public String goToGoogleImport(){
		return "go-to-google-import";
	}

	public String getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}

	public List<Contact> getList() {
		return list;
	}

	public void setList(List<Contact> list) {
		this.list = list;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getSelectedContactId() {
		return selectedContactId;
	}

	public void setSelectedContactId(Long selectedContactId) {
		this.selectedContactId = selectedContactId;
	}

	public String getFlagEdita() {
		return flagEdita;
	}

	public void setFlagEdita(String flagEdita) {
		this.flagEdita = flagEdita;
	}

	public String getBuscaNome() {
		return buscaNome;
	}

	public void setBuscaNome(String buscaNome) {
		this.buscaNome = buscaNome;
	}

	public ContactDTO getContactDTO() {
		return contactDTO;
	}

	public void setContactDTO(ContactDTO contactDTO) {
		this.contactDTO = contactDTO;
	}
}

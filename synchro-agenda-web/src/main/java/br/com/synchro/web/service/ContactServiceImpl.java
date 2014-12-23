package br.com.synchro.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.synchro.web.domain.Cep;
import br.com.synchro.web.domain.Contact;
import br.com.synchro.web.domain.dto.ContactDTO;
import br.com.synchro.web.exception.CepException;
import br.com.synchro.web.exception.RestException;
import br.com.synchro.web.exception.ServiceException;
import br.com.synchro.web.rest.CepRestClient;
import br.com.synchro.web.rest.ContactRestClient;

/**
 * 
 * ContactServiceImpl.java
 * Criado em Sep 25, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRestClient contactRestClient;
	
	@Autowired
	private CepRestClient cepRestClient;
	
	public List<ContactDTO> loadAll() throws ServiceException{
		try{
			return this.contactRestClient.loadAll();
		}catch(RestException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
	
	public List<ContactDTO> loadByUsername(String username) throws ServiceException{
		try{
			return this.contactRestClient.loadByUsername(username);
		}catch(RestException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
	
	public String addContact(Contact contact) throws ServiceException{
		try{
			return this.contactRestClient.addContact(contact);
		}catch(RestException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
	
	public String deleteContact(String id) throws ServiceException{
		try{
			return this.contactRestClient.deleteContact(id);		
		}catch(RestException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
	
	public String updateContact(Contact contact) throws ServiceException{
		try{
			return this.contactRestClient.updateContact(contact);
		}catch(RestException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
	
	public List<ContactDTO> searchByName(String name, String username) throws ServiceException{
		try{
			return this.contactRestClient.searchByName(name, username);
		}catch(RestException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
	
	public Cep searchCep(String cep) throws ServiceException{
		try{
			return this.cepRestClient.searchCep(cep);
		}catch(CepException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
}

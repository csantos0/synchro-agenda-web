package br.com.synchro.web.service;

import java.util.List;

import br.com.synchro.web.domain.Cep;
import br.com.synchro.web.domain.Contact;
import br.com.synchro.web.domain.dto.ContactDTO;
import br.com.synchro.web.exception.ServiceException;

/**
 * 
 * ContactService.java
 * Criado em Sep 25, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
public interface ContactService {

	public List<ContactDTO> loadAll() throws ServiceException;
	
	public List<ContactDTO> loadByUsername(String username) throws ServiceException;
	
	public String addContact(Contact contact) throws ServiceException;
	
	public String deleteContact(String id) throws ServiceException;
	
	public String updateContact(Contact contact) throws ServiceException;
	
	public List<ContactDTO> searchByName(String name, String username) throws ServiceException;
	
	public Cep searchCep(String cep) throws ServiceException;
}

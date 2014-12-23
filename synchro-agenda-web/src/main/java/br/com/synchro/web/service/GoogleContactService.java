package br.com.synchro.web.service;

import java.util.List;

import br.com.synchro.web.domain.GoogleContact;
import br.com.synchro.web.exception.ServiceException;

/**
 * 
 * GoogleContactService.java
 * Criado em Sep 27, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
public interface GoogleContactService {

	public List<GoogleContact> getAllContacts(String user, String password) throws ServiceException;
}

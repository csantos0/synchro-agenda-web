package br.com.synchro.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.synchro.web.domain.GoogleContact;
import br.com.synchro.web.exception.GoogleException;
import br.com.synchro.web.exception.ServiceException;
import br.com.synchro.web.google.GoogleContactsProvider;

/**
 * 
 * GoogleServiceImpl.java
 * Criado em Sep 27, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@Service
public class GoogleServiceImpl implements GoogleContactService{

	@Autowired
	private GoogleContactsProvider googleContactsProvider;
	
	@Override
	public List<GoogleContact> getAllContacts(String user, String password) throws ServiceException{
		try {
			return this.googleContactsProvider.getAllContacts(user, password);
		} catch (GoogleException ex) {
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}		
	}
}

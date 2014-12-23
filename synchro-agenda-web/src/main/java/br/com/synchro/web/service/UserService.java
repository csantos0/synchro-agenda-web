package br.com.synchro.web.service;

import br.com.synchro.web.domain.User;
import br.com.synchro.web.exception.ServiceException;

/**
 * 
 * UserService.java
 * Criado em Sep 25, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
public interface UserService {

	public String addContact(User user) throws ServiceException;
	
	public User findUserByUsername(String username) throws ServiceException;
}

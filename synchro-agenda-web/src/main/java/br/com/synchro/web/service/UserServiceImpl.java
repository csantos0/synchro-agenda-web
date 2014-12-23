package br.com.synchro.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.synchro.web.domain.User;
import br.com.synchro.web.exception.RestException;
import br.com.synchro.web.exception.ServiceException;
import br.com.synchro.web.rest.UserRestClient;

/**
 * 
 * UserServiceImpl.java
 * Criado em Sep 25, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRestClient userRestClient;
	
	public String addContact(User user) throws ServiceException{
		try{
			return this.userRestClient.addContact(user);
		}catch(RestException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
	
	public User findUserByUsername(String username) throws ServiceException{
		try{
			return this.userRestClient.findUserByUsername(username);
		}catch(RestException ex){
			throw new ServiceException(ex.getMethodName(), ex.getMessage(), ex.getStack());
		}
	}
}

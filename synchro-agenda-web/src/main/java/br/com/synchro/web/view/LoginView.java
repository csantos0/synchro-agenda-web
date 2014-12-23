package br.com.synchro.web.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.synchro.web.domain.User;
import br.com.synchro.web.exception.ServiceException;
import br.com.synchro.web.service.UserService;
import br.com.synchro.web.util.FacesUtil;
import br.com.synchro.web.util.StringUtil;

/**
 * 
 * LoginView.java
 * Criado em Sep 23, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@Component("loginView")
@Scope("request")
@SuppressWarnings({ "serial", "deprecation" })
public class LoginView implements Serializable{
	
	private static Logger logger = Logger.getLogger(LoginView.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	@Qualifier("authMgr") 
	private AuthenticationManager authMgr;
	
	private User model;
	
	@PostConstruct
	public void init(){
		this.model = new User();
	}	
	
	public String doLogin() throws IOException, ServletException{		
		FacesUtil.doDispatcher("/j_spring_security_check");		
		return null;
	}
	
	public String logout(){
		FacesUtil.getSession().removeAttribute("loggedObjUser");
        SecurityContextHolder.clearContext();
        return "loggedout";
    }
	
	public String register(){	
		try {		
			this.userService.addContact(this.model);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		    GrantedAuthority ga = new GrantedAuthorityImpl("ROLE_USER");
		    authorities.add(ga);        
		        
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(this.getModel().getUsername(), this.getModel().getPassword(), authorities);
					
			authMgr.authenticate(auth); 
			
			if(auth.isAuthenticated()) {
				User user = this.userService.findUserByUsername(this.getModel().getUsername());    
				if(FacesUtil.getSession().getAttribute("loggedObjUser") == null){
					FacesUtil.getSession().setAttribute("loggedObjUser", user);
			    }
				SecurityContextHolder.getContext().setAuthentication(auth);
				return "register_success";
			}
		} catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
		} catch (Exception ex) {
			logger.error("Message: " + ex.getMessage() + "|Stack: " + StringUtil.getStackTrace(ex));
		}
		return null;
	}
	
	public String goToRegister(){
		return "go-to-register";
	}

	public User getModel() {
		return model;
	}

	public void setModel(User model) {
		this.model = model;
	}

}

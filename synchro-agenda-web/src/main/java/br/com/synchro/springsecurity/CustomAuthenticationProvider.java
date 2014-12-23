package br.com.synchro.springsecurity;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

import br.com.synchro.web.domain.User;
import br.com.synchro.web.exception.ServiceException;
import br.com.synchro.web.service.UserService;
import br.com.synchro.web.util.FacesUtil;

@Component
@SuppressWarnings("deprecation")
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private static Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private UserService userService;
	
	@Override		
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
 
        User user = null;
        
        try{
        	user = this.userService.findUserByUsername(name);
        }catch(ServiceException ex){
			logger.error("Message: " + ex.getMessage() + "|Stack: " + ex.getStack());
			throw new BadCredentialsException("problems with service " + ex.getMessage());
		}
        
        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }
 
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
 
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority ga = new GrantedAuthorityImpl("ROLE_USER");
        authorities.add(ga);        
        
        if(FacesUtil.getSession().getAttribute("loggedObjUser") == null){
        	FacesUtil.getSession().setAttribute("loggedObjUser", user);
        }
        return new UsernamePasswordAuthenticationToken(name, password, authorities);        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

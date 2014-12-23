package br.com.synchro.web.rest;

import org.springframework.stereotype.Repository;

import br.com.synchro.web.domain.User;
import br.com.synchro.web.exception.RestException;
import br.com.synchro.web.util.StringUtil;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * 
 * UserRestClient.java Criado em Sep 24, 2014
 * 
 * @author Ciro S. Santos
 * @version 1.0
 */
@Repository
public class UserRestClient {

    private static final String REST_API_ADD_USER = "http://localhost:8080/synchro-agenda-services/rest/user-service/post";
    private static final String REST_API_FIND_USER = "http://localhost:8080/synchro-agenda-services/rest/user-service/find-user/";

    private Client jerseyClient;

    private UserRestClient() {
	final ClientConfig clientConfig = new DefaultClientConfig();
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	this.jerseyClient = Client.create(clientConfig);
    }

    public String addContact(final User user) throws RestException {
	try {
	    final WebResource webResource = this.jerseyClient.resource(REST_API_ADD_USER);
	    final ClientResponse response = webResource.accept("application/json").type("application/json")
		    .post(ClientResponse.class, user);
	    return response.getEntity(String.class);
	} catch (final Exception ex) {
	    throw new RestException(this.getClass().getName() + ".addContact(User user)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	}
    }

    public User findUserByUsername(final String username) throws RestException {
	try {
	    final WebResource webResource = this.jerseyClient.resource(REST_API_FIND_USER + username);
	    final User response = webResource.accept("application/json").get(User.class);
	    if (response.getId().intValue() == 0) {
		return null;
	    } else {
		return response;
	    }
	} catch (final Exception ex) {
	    throw new RestException(this.getClass().getName() + ".findUserByUsername(String username)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	}
    }
}

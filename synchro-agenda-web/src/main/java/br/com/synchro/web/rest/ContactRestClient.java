package br.com.synchro.web.rest;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.synchro.web.domain.Contact;
import br.com.synchro.web.domain.dto.ContactDTO;
import br.com.synchro.web.exception.RestException;
import br.com.synchro.web.util.StringUtil;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * 
 * ContactRestClient.java Criado em Sep 25, 2014
 * 
 * @author Ciro S. Santos
 * @version 1.0
 */
@Repository
public class ContactRestClient {

    private static final String REST_API_LOAD_ALL = "http://localhost:8080/synchro-agenda-services/rest/contact-service/get-all";
    private static final String REST_API_ADD_CONTACT = "http://localhost:8080/synchro-agenda-services/rest/contact-service/post";
    private static final String REST_API_UPDATE_CONTACT = "http://localhost:8080/synchro-agenda-services/rest/contact-service/put";
    private static final String REST_API_DELETE_CONTACT = "http://localhost:8080/synchro-agenda-services/rest/contact-service/delete/";
    private static final String REST_API_SEARCH_CONTACT = "http://localhost:8080/synchro-agenda-services/rest/contact-service/search-by-name/";
    private static final String REST_API_SEARCH_BY_USERNAME = "http://localhost:8080/synchro-agenda-services/rest/contact-service/get-by-username/";

    private Client jerseyClient;

    public ContactRestClient() {
	final ClientConfig clientConfig = new DefaultClientConfig();
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	this.jerseyClient = Client.create(clientConfig);
    }

    public String addContact(final Contact contact) throws RestException {
	try {
	    final WebResource webResource = this.jerseyClient.resource(REST_API_ADD_CONTACT);
	    final ClientResponse response = webResource.accept("application/json").type("application/json")
		    .post(ClientResponse.class, contact);
	    return response.getEntity(String.class);
	} catch (final Exception ex) {
	    throw new RestException(this.getClass().getName() + ".addContact(Contact contact)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	}
    }

    public String deleteContact(final String id) throws RestException {
	try {
	    final WebResource webResource = this.jerseyClient.resource(REST_API_DELETE_CONTACT + id);
	    final ClientResponse response = webResource.accept("application/json").type("application/json")
		    .delete(ClientResponse.class);
	    return response.getEntity(String.class);
	} catch (final Exception ex) {
	    throw new RestException(this.getClass().getName() + ".deleteContact(String id)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	}
    }

    public List<ContactDTO> loadAll() throws RestException {
	try {
	    final WebResource webResource = this.jerseyClient.resource(REST_API_LOAD_ALL);
	    final ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	    return response.getEntity(new GenericType<List<ContactDTO>>() {
	    });
	} catch (final Exception ex) {
	    throw new RestException(this.getClass().getName() + ".loadAll()", ex.getMessage(), StringUtil.getStackTrace(ex));
	}
    }

    public List<ContactDTO> loadByUsername(final String username) throws RestException {
	try {
	    final WebResource webResource = this.jerseyClient.resource(REST_API_SEARCH_BY_USERNAME + username);
	    final ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	    return response.getEntity(new GenericType<List<ContactDTO>>() {
	    });
	} catch (final Exception ex) {
	    throw new RestException(this.getClass().getName() + ".loadByUsername(String username)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	}
    }

    public List<ContactDTO> searchByName(final String name, final String username) throws RestException {
	try {
	    final WebResource webResource = this.jerseyClient.resource(REST_API_SEARCH_CONTACT + username + "/" + name);
	    final ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	    return response.getEntity(new GenericType<List<ContactDTO>>() {
	    });
	} catch (final Exception ex) {
	    throw new RestException(this.getClass().getName() + ".searchByName(String name, String username)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	}
    }

    public String updateContact(final Contact contact) throws RestException {
	try {
	    final WebResource webResource = this.jerseyClient.resource(REST_API_UPDATE_CONTACT);
	    final ClientResponse response = webResource.accept("application/json").type("application/json")
		    .put(ClientResponse.class, contact);
	    return response.getEntity(String.class);
	} catch (final Exception ex) {
	    throw new RestException(this.getClass().getName() + ".updateContact(Contact contact)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	}
    }

}

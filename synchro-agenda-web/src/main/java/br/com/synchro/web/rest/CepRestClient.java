package br.com.synchro.web.rest;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

import br.com.synchro.web.domain.Cep;
import br.com.synchro.web.exception.CepException;
import br.com.synchro.web.util.StringUtil;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * CepRestClient.java Criado em Sep 26, 2014
 * 
 * @author Ciro S. Santos
 * @version 1.0
 */
@Repository
public class CepRestClient {

    private static final String CEP_HOST_ADDRESS = "http://cep.correiocontrol.com.br/";
    private static final String CEP_SUFFIX = ".json";

    private Client jerseyClient;

    public CepRestClient() {
	this.jerseyClient = Client.create();
    }

    public Cep searchCep(final String cep) throws CepException {
	try {
	    final String url = CEP_HOST_ADDRESS + cep + CEP_SUFFIX;
	    final WebResource webResource = this.jerseyClient.resource(url);
	    final ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	    final String result = response.getEntity(String.class);
	    final ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(result, Cep.class);
	} catch (final JsonParseException ex) {
	    throw new CepException(this.getClass().getName() + ".searchCep(String cep)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	} catch (final JsonMappingException ex) {
	    throw new CepException(this.getClass().getName() + ".searchCep(String cep)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	} catch (final IOException ex) {
	    throw new CepException(this.getClass().getName() + ".searchCep(String cep)", ex.getMessage(),
		    StringUtil.getStackTrace(ex));
	}
    }
}

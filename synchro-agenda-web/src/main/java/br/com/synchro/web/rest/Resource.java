package br.com.synchro.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/synchro-web-json")
public class Resource {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String print(){
		return "message";
	}
}

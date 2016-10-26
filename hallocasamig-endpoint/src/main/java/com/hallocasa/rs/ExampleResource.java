package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;
import com.hallocasa.rs.security.Secured;
import com.hallocasa.services.example.ExampleService;
import com.hallocasa.vo.Example;

@Path("/example")
public class ExampleResource {

	@EJB
	ExampleService exampleService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Secured
	public Response sayHello(@Context SecurityContext sc) {
		return Response.status(HttpStatus.SC_OK).entity(exampleService.findById(5)).build();
	}
	
	@GET
	@Path("/free")
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	public Response sayHelloFree(@Context SecurityContext sc) {
		return Response.status(HttpStatus.SC_OK).entity(exampleService.findById(5)).build();
	}

	@POST
	@Consumes("application/json")
	public String sayHello(Example example) {
		return "haciendo post";
	}
}

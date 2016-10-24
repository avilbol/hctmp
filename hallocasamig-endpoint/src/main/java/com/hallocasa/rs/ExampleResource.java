package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.hallocasa.services.example.ExampleService;
import com.hallocasa.vo.Example;

@Path("/secured/example")
public class ExampleResource {

	@EJB
	ExampleService exampleService;

	@GET
	public Response sayHello(@Context SecurityContext sc) {
		return Response.status(Response.Status.OK).entity(new GenericEntity<Example>(exampleService.findById(5)) {
			}).header("Access-Control-Allow-Headers", "X-extra-header").build();
	}

	@POST
	@Consumes("application/json")
	public String sayHello(Example example) {
		return "haciendo post";
	}
}

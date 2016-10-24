package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.hallocasa.services.example.ExampleService;
import com.hallocasa.vo.Example;

@Path("/example")
public class ExampleResource {

	@EJB
	ExampleService exampleService;
	
	@GET
	public String sayHello(@Context SecurityContext sc){
		//OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
		return "" + exampleService.findById(5);
	}
	
	@POST
	@Consumes("application/json")
	public String sayHello(Example example){
		return "haciendo post";
	}
}

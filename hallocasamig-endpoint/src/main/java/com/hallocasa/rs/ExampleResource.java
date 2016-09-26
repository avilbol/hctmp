package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.hallocasa.services.example.ExampleService;

@Path("/example")
public class ExampleResource {

	@EJB
	ExampleService exampleService;
	
	@GET
	public String sayHello(){
		return "" + exampleService.findById(5);
	}
}

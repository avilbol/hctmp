package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hallocasa.services.example.ExampleService;
import com.hallocasa.vo.Example;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/example")
@Api(value="/exampleservice", tags = "exampleservice")
public class ExampleResource {

	@EJB
	private ExampleService exampleService;
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	@ApiOperation( 
	    value = "Genera un valor de ejemplo", 
	    notes = "Genera un valor de ejemplo"
	)
	@ApiResponses( {
	    @ApiResponse( code = 200, message = "Valor de ejemplo" ),
	    @ApiResponse( code = 500, message = "Error inesperado" )
	} )
	public Response getExampleByCode(@PathParam("id") Integer id) {
		Example example = exampleService.findById(id);
		return Response.ok(example, MediaType.APPLICATION_JSON).build();
	}	
}

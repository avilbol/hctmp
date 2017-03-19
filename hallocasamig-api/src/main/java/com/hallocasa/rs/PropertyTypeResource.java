package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;
import com.hallocasa.services.properties.PropertyTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/property_types")
@Api(value="/property_types", tags = "property keys")
public class PropertyTypeResource {

	@EJB
    PropertyTypeService propertyTypeService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of all property types")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getTypes() {
		return Response.status(HttpStatus.SC_OK).entity(propertyTypeService.find()).build();
	}
	
	@GET
	@Path("/groups")
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of all property types groups")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getGroups() {
		return Response.status(HttpStatus.SC_OK).entity(propertyTypeService.findGroups()).build();
	}
}
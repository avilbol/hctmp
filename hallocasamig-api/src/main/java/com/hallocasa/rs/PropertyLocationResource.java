package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;
import com.hallocasa.services.properties.PropertyLocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/property_locations")
@Api(value="/property_locations", tags = "property keys")
public class PropertyLocationResource {

	@EJB
    PropertyLocationService propertyLocationService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of all property locations")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getCountries() {
		return Response.status(HttpStatus.SC_OK).entity(propertyLocationService.find()).build();
	}
}
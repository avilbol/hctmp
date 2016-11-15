package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Secured;
import com.hallocasa.services.properties.PropertyFieldService;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/property_fields")
@Api(value = "/property_fields", tags = "Fields, Property fields")
public class PropertyFieldResource {

	@EJB
	PropertyFieldService propertyFieldService;

	@POST
	@Path("/filter_by_key")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Secured
	@ApiOperation(value = "Retrieves the list of property fields that will be showed when selecting the type, proposal, "
			+ "location, and country specified", notes = "All the property keys are required, otherwise system generate a bad request, because "
					+ "without all the parameters, it is not posible filter the property fields")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getPropertyFilters(@ApiParam(value = "key set of the property to filter") PropertyKey propertyKey) {
		return Response.status(HttpStatus.SC_OK).entity(propertyFieldService
				.getPropertyFieldList(propertyKey)).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Secured
	@ApiOperation(value = "Retrieves the list of all property fields")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getPropertyFilters() {
		return Response.status(HttpStatus.SC_OK).entity(propertyFieldService
				.getPropertyFieldList()).build();
	}
}

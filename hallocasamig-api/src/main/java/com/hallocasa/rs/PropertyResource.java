package com.hallocasa.rs;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Secured;
import com.hallocasa.services.properties.PropertyService;
import com.hallocasa.vo.hcfilter.properties.Property;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/properties")
@Api(value = "/properties", tags = "Properties")
public class PropertyResource {

	@EJB
	PropertyService propertyService;

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Secured
	@ApiOperation(value = "Return the property with specified id", notes = "Filter the properties existing "
					+ "in system with specified id. Returns empty if none property match the id")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getPropertyFilters(@ApiParam(value = "property id") 
			@PathParam("id") String id) {
		Optional<Property> property = propertyService.findById(id);
		if(!property.isPresent()){
			return Response.status(HttpStatus.SC_OK).entity(new Property()).build();
		}
		return Response.status(HttpStatus.SC_OK).entity(property.get()).build();
	}
}

package com.hallocasa.rs;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;
import com.hallocasa.rs.security.Secured;
import com.hallocasa.services.properties.PropertyService;
import com.hallocasa.vo.hcfilter.PropertyFilterRequest;
import com.hallocasa.vo.hcfilter.properties.Property;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	public Response findProperty(@ApiParam(value = "property id") @PathParam("id") String id) {
		Optional<Property> property = propertyService.findById(id);
		if (!property.isPresent()) {
			return Response.status(HttpStatus.SC_OK).entity(new Property()).build();
		}
		return Response.status(HttpStatus.SC_OK).entity(property.get()).build();
	}
	
	@POST
	@Path("search")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Search properties with specified filters")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response findProperties(
			@ApiParam(value = "filters") PropertyFilterRequest propertyFilterRequest) {
		return Response.status(HttpStatus.SC_OK).entity(
				propertyService.find(propertyFilterRequest)).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML})
	@Secured
	@ApiOperation(value = "Create or update the property supplied", 
		notes = "Consider that only user can save its properties")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response saveProperty(@ApiParam("property to persist") Property property) {
	    propertyService.save(property);
		return Response.status(HttpStatus.SC_OK).entity("Property saved succesfully").build();
	}
	
	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML})
	@Secured
	@ApiOperation(value = "Delete the property with id supplied", 
		notes = "This process is not reversible")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response deleteProperty(@ApiParam("propertyId") @PathParam("id") String propertyId) {
	    propertyService.delete(propertyId);
		return Response.status(HttpStatus.SC_OK).entity("Property deleted succesfully").build();
	}
	
	@POST
	@Path("fetch_random")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Fetch random list of properties, with basic data")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
		@ApiImplicitParam(name = "property_number", value = "Number of properties desired",
				required = true, dataType = "int", paramType = "query")
	})
	public Response fetchRandomProperties(@Context UriInfo uriInfo) {
		Integer propertyNumber = Integer.parseInt(uriInfo.getQueryParameters()
				.getFirst("property_number"));
		return Response.status(HttpStatus.SC_OK).entity(
				propertyService.addPropertiesToShowableList(propertyNumber)).build();
	}
}

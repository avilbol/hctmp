package com.hallocasa.rs;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Secured;
import com.hallocasa.services.properties.PropertyFieldService;
import com.hallocasa.services.properties.PropertyListerService;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;
import com.hallocasa.vo.properties.PropertyField;

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
	
	@EJB
	PropertyListerService propertyListerService;

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
	
	@POST
	@Path("/options/{field_id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Secured
	@ApiOperation(value = "Return the options to offer to the user in property field, given selected options of "
			+ "parent property fields", notes = "The property field must exist, and must have listers associated")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 403, message = "If it does not exist or do not have listers, the property field"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getPropertyFieldOptions(@ApiParam(value = "field_id") @PathParam("field_id") Integer fieldId,
			@ApiParam(value = "property field list") List<PropertyField> propertyFieldList) {
		return Response.status(HttpStatus.SC_OK).entity(propertyListerService
				.listFieldOptions(fieldId, propertyFieldList)).build();
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

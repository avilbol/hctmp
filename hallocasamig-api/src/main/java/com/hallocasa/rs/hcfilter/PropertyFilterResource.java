package com.hallocasa.rs.hcfilter;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
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
import com.hallocasa.rs.utils.ResourceUtils;
import com.hallocasa.services.hcfilters.PropertyFilterService;
import com.hallocasa.services.properties.PropertyListerService;
import com.hallocasa.vo.hcfilter.properties.PropertyFilter;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/property_filters")
@Api(value = "/property_filters", tags = "Filters, Property filters")
public class PropertyFilterResource {

	@EJB
	PropertyFilterService propertyFilterService;
	
	@EJB
	PropertyListerService propertyListerService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of property filters", notes = "Return from the property list elements, theirs that allows to property filters")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
			@ApiImplicitParam(allowMultiple = true, name = "filter_nature_id", value = "Filter nature id", required = false, dataType = "string", paramType = "query")
	})
	public Response getPropertyFilters(@Context UriInfo uriInfo) {
		List<Integer> filterNatureIdList = ResourceUtils.intList(uriInfo, "filter_nature_id");
		List<PropertyFilter> filterList = propertyFilterService.loadPropertyFilterList(!filterNatureIdList.isEmpty(),
				filterNatureIdList);
		return Response.status(HttpStatus.SC_OK).entity(filterList).build();
	}
	
	
	@POST
	@Path("by_property_keys")
	@Auth
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Show the filter list which its property field could show when "
			+ "a set of property keys have been selected", 
		notes = "In order to use this resource, it is required to use at least one filter element")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
			@ApiImplicitParam(allowMultiple = true, name = "filter_nature_id", value = "Filter nature id", required = false, dataType = "string", paramType = "query")
	})
	public Response getPropertyFilters(List<PropertyFilterSubmission> filterList) {
		List<PropertyFilter> resultList = propertyFilterService.loadPropertyFilterList(filterList);
		return Response.status(HttpStatus.SC_OK).entity(resultList).build();
	}

	@POST
	@Path("/options/{filter_id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Return the options to offer to the user in property filter, given selected options of "
			+ "parent property filters", notes = "The property filter must exist, and must have listers associated")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 403, message = "If it does not exist or do not have listers, the property filter"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getPropertyFieldOptions(@ApiParam(value = "filter_id") @PathParam("filter_id") Integer filterId,
			@ApiParam(value = "property filter submission list") List<PropertyFilterSubmission> filterSubmissionList) {
		return Response.status(HttpStatus.SC_OK).entity(propertyListerService
				.listFilterOptions(filterId, filterSubmissionList)).build();
	}
	
}

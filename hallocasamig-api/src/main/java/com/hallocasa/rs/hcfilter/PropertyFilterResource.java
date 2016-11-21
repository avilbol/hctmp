package com.hallocasa.rs.hcfilter;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Secured;
import com.hallocasa.rs.utils.ResourceUtils;
import com.hallocasa.services.hcfilters.PropertyFilterService;
import com.hallocasa.vo.hcfilter.properties.PropertyDropdownFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/property_filters")
@Api(value = "/property_filters", tags = "Filters, Property filters")
public class PropertyFilterResource {

	@EJB
	PropertyFilterService propertyFilterService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Secured
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
	@Secured
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
	public Response getPropertyFilters(List<PropertyDropdownFilterSubmission> filterList) {
		List<PropertyFilter> resultList = propertyFilterService.loadPropertyFilterList(filterList);
		return Response.status(HttpStatus.SC_OK).entity(resultList).build();
	}

}

package com.hallocasa.rs;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;
import com.hallocasa.rs.utils.ResourceUtils;
import com.hallocasa.services.generalities.location.StateService;
import com.hallocasa.vo.State;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/states")
@Api(value="/states", tags = "location")
public class StateResource {

	@EJB
    StateService stateService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of states within specified country id list")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
		@ApiImplicitParam(allowMultiple = true, name = "country_id", 
				value = "Some country id to filter", required = false, dataType = "string", paramType = "query")
	})
	public Response getStates(@Context UriInfo uriInfo) {
		List<Integer> intList = ResourceUtils.intList(uriInfo, "country_id");
		if(intList.isEmpty()){
			throw new BadRequestException("At least is required one country id");
		}
		boolean oneElement = intList.size() == 1;
		List<State> stateList = oneElement ? stateService.findByCountryId(intList.get(0)) 
				: stateService.findByCountriesId(intList);
		return Response.status(HttpStatus.SC_OK).entity(stateList).build();
	}
}

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
import com.hallocasa.services.generalities.location.CityService;
import com.hallocasa.vo.City;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/cities")
@Api(value="/cities", tags = "location")
public class CityResource {

	@EJB
    CityService cityService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of cities within specified city id list")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
		@ApiImplicitParam(allowMultiple = true, name = "state_id", 
				value = "Some state id to filter", required = false, dataType = "string", paramType = "query")
	})
	public Response getStates(@Context UriInfo uriInfo) {
		List<Integer> intList = ResourceUtils.intList(uriInfo, "state_id");
		if(intList.isEmpty()){
			throw new BadRequestException("At least is required one state id");
		}
		boolean oneElement = intList.size() == 1;
		List<City> cityList = oneElement ? cityService.findByStateId(intList.get(0)) 
				: cityService.findByStatesId(intList);
		return Response.status(HttpStatus.SC_OK).entity(cityList).build();
	}
}

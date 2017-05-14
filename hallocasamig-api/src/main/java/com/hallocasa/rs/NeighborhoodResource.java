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
import com.hallocasa.services.generalities.location.NeighborhoodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/neighborhoods")
@Api(value = "/neighborhoods", tags = "location")
public class NeighborhoodResource {

	@EJB
	NeighborhoodService neighborhoodService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of neighborhoods within specified city id")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
			@ApiImplicitParam(allowMultiple = true, name = "city_id", value = "Some city id to filter", required = true, dataType = "string", paramType = "query") })
	public Response getNeighborhoods(@Context UriInfo uriInfo) {
		List<Integer> intList = ResourceUtils.intList(uriInfo, "city_id");
		if (intList.isEmpty()) {
			throw new BadRequestException("It is required the city id");
		}
		return Response.status(HttpStatus.SC_OK)
				.entity(neighborhoodService.findByCityId(intList.get(0))).build();
	}

}

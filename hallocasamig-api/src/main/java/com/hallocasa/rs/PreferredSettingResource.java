package com.hallocasa.rs;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.services.generalities.PreferredSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/preferred_settings")
@Api(value="/preferred_settings", tags = "global")
public class PreferredSettingResource {

	@EJB
	PreferredSettingService preferredSettingService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Returns all country preferred settings")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response find() throws IllegalAccessException, InvocationTargetException {
		return Response.status(HttpStatus.SC_OK).entity(preferredSettingService.find()).build();
	}
}

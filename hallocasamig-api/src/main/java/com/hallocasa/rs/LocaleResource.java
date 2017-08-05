package com.hallocasa.rs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/locales")
@Api(value="/locales", tags = "locales")
public class LocaleResource {

	@GET
	@Path("/{locale}")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Returns locale language list")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getLanguages(@PathParam("locale") String locale) {
		Map<String, String> test = new HashMap<String, String>();
		test.put("hola", locale);
		return Response.status(HttpStatus.SC_OK).entity(test).build();
	}
	
}

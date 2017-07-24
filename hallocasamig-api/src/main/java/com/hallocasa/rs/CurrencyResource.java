package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;
import com.hallocasa.services.currencies.CurrencyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/currencies")
@Api(value="/currencies", tags = "currency management")
public class CurrencyResource {

	@EJB
    CurrencyService currencyService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of all exchange available in application")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getCurrencies() {
		CacheControl cacheControl = new CacheControl();
		cacheControl.setMaxAge(592000); // TODO: Define this in 1 week parameterized
		return Response.status(HttpStatus.SC_OK).entity(currencyService.find())
				.cacheControl(cacheControl).build();
	}
	
}


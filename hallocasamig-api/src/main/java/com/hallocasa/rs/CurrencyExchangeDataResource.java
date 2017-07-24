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
import com.hallocasa.services.currencies.CurrencyExchangeDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/currency_exchange_data")
@Api(value="/currency_exchange_data", tags = "currency management")
public class CurrencyExchangeDataResource {

	@EJB
    CurrencyExchangeDataService currencyExchangeDataService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Retrieves the list of all exchange rates between currencies")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getCurrencyExchangeDataList() {
		CacheControl cacheControl = new CacheControl();
		cacheControl.setMaxAge(604800); // TODO: Define this in 1 week parameterized
		return Response.status(HttpStatus.SC_OK).entity(currencyExchangeDataService.findExchangeRates())
				.cacheControl(cacheControl).build();
	}
	
}

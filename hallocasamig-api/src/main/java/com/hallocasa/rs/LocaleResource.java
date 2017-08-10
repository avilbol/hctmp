package com.hallocasa.rs;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Secured;
import com.hallocasa.services.generalities.LocalizationService;
import com.hallocasa.vo.dto.LocaleEntryDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/locales")
@Api(value="/locales", tags = "locales")
public class LocaleResource {

	@EJB
	LocalizationService localizationService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Returns all language list locale entries")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response find() throws IllegalAccessException, InvocationTargetException {
		return Response.status(HttpStatus.SC_OK).entity(localizationService.find()).build();
	}
	
	@GET
	@Path("/{locale}")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Returns locale language list")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getLanguages(@PathParam("locale") String locale) {
		return Response.status(HttpStatus.SC_OK).entity(localizationService.find(locale)).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML})
	@ApiOperation(value = "Create or update the locale translation list")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response saveLocaleEntries(
			@HeaderParam("security-key") String securityKey, List<LocaleEntryDTO> localeEntries) 
					throws IllegalArgumentException, IllegalAccessException, 
					InvocationTargetException, NoSuchMethodException {
		localizationService.save(localeEntries, securityKey);
		return Response.status(HttpStatus.SC_OK).entity("Locale entries saved succesfully").build();
	}
	
	@POST
	@Path("/key-value-translations/{locale}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML})
	@ApiOperation(value = "Create or update the key value translations")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response saveKeyValueTranslations(
			@HeaderParam("security-key") String securityKey, 
			@PathParam("locale") String locale, 
			Map<String, String> keyValueList) throws IllegalArgumentException, IllegalAccessException, 
			InvocationTargetException, NoSuchMethodException {
		localizationService.save(keyValueList, locale, securityKey);
		return Response.status(HttpStatus.SC_OK).entity("Key - values locale entries saved succesfully").build();
	}
	
	@DELETE
	@Path("/{pnemonic}")
	@Produces({ MediaType.TEXT_HTML})
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete a locale entry with all translations related")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response delete(
			@HeaderParam("security-key") String securityKey, 
			@PathParam("pnemonic") String pnemonic) {
		localizationService.delete(pnemonic, securityKey);
		return Response.status(HttpStatus.SC_OK).entity("Delete operation executed succesfully").build();
	}
}

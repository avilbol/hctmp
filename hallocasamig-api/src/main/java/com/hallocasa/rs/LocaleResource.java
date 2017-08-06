package com.hallocasa.rs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Secured;
import com.hallocasa.vo.dto.LocaleEntryDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
		test.put("hallocasa.propertytype.industrial_building", "Industrieobjekt");
		test.put("hallocasa.propertytype.mini_golf_course", "Minigolfplatz");
		test.put("hallocasa.propertytype.motel", "Motel");
		test.put("hallocasa.propertytype.nursing_home", "Pflegeheim");
		test.put("hallocasa.propertytype.sports_facility", "Sportanlage");
		return Response.status(HttpStatus.SC_OK).entity(test).build();
	}
	
	@GET
	@Path("/ui-internationalization/entries")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Returns locale language list for manipulation in api")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response getLocaleUiEntries() {
		List<LocaleEntryDTO> test = new LinkedList<LocaleEntryDTO>();
		LocaleEntryDTO dto = new LocaleEntryDTO();
		dto.setDe("hallo");
		dto.setEn("hello");
		dto.setEs("hola");
		dto.setPnemonic("salutation");
		dto.setLangKey("hallocasa.global.salutation");
		test.add(dto);
		dto = new LocaleEntryDTO();
		dto.setDe("Auf Wiedersehen");
		dto.setEn("Bye");
		dto.setEs("Adios");
		dto.setPnemonic("salutation2");
		dto.setLangKey("hallocasa.global.salutation2");
		test.add(dto);
		return Response.status(HttpStatus.SC_OK).entity(test).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML})
	@Secured
	@ApiOperation(value = "Create or update the property supplied", 
		notes = "Consider that only user can save its properties")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response saveLocaleEntry(@ApiParam("property to persist") LocaleEntryDTO localeEntry) {
		return Response.status(HttpStatus.SC_OK).entity("Locale entry saved succesfully").build();
	}
	
}

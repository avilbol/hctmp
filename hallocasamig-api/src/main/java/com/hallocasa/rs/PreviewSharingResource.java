package com.hallocasa.rs;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.hallocasa.services.generalities.PreviewSharingService;

/**
 * Resources for html-templates of preview sharing
 * @author Alexander Villamil
 */
@Path("/previews")
public class PreviewSharingResource {

	@EJB
	PreviewSharingService previewSharingService;
	
	/**
	 * Delivers a html template preview for a homesite. Used as default with any hallocasa URL
	 * @param locale
	 * 		The custom locale specified by user via QueryString. Optional
	 * @param browserLocale
	 * 		The Accept-Language header sent by browser 
	 * @return
	 * 		The html template
	 * @throws IOException
	 * 		If there is an error reading the html file sources
	 */
	@GET
	@Path("home")
	@Produces({ MediaType.TEXT_HTML + ";charset=utf-8"})
	public Response previewProperty(@QueryParam("lang") String locale,
			@HeaderParam("Accept-Language") String browserLocale) throws IOException {
		return Response.status(HttpStatus.SC_OK)
				.entity(previewSharingService.homePreview(locale, browserLocale)).build();
	}
	
	/**
	 * Delivers a html template preview for a property.
	 * @param locale
	 * 		The custom locale specified by user via QueryString. Optional
	 * @param browserLocale
	 * 		The Accept-Language header sent by browser 
	 * @return
	 * 		The html template
	 * @throws IOException
	 * 		If there is an error reading the html file sources
	 */
	@GET
	@Path("property")
	@Produces({ MediaType.TEXT_HTML + ";charset=utf-8"})
	public Response previewProperty(@QueryParam("id") String id, @QueryParam("lang") String locale,
			@HeaderParam("Accept-Language") String browserLang) throws IOException {
		return Response.status(HttpStatus.SC_OK)
				.entity(previewSharingService.previewPropertyById(id, locale, browserLang)).build();
	}
	
	/**
	 * Delivers a html template preview for a profile.
	 * @param locale
	 * 		The custom locale specified by user via QueryString. Optional
	 * @param browserLocale
	 * 		The Accept-Language header sent by browser 
	 * @return
	 * 		The html template
	 * @throws IOException
	 * 		If there is an error reading the html file sources
	 */
	@GET
	@Path("profile")
	@Produces({ MediaType.TEXT_HTML + ";charset=utf-8"})
	public Response previewProfile(@QueryParam("id") Long id, @QueryParam("lang") String locale,
			@HeaderParam("Accept-Language") String browserLang) throws IOException {
		return Response.status(HttpStatus.SC_OK)
				.entity(previewSharingService.previewProfileById(id, locale, browserLang)).build();
	}
}

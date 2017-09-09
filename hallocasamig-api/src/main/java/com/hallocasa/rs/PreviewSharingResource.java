package com.hallocasa.rs;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
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
	
	@GET
	@Path("home")
	@Produces({ MediaType.TEXT_HTML})
	public Response previewProperty(@QueryParam("lang") String locale) throws IOException {
		return Response.status(HttpStatus.SC_OK)
				.entity(previewSharingService.homePreview()).build();
	}
}

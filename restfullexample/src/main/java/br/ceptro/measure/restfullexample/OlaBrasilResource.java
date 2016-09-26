/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ceptro.measure.restfullexample;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author polianareis
 */
@Path("ola-brasil")
public class OlaBrasilResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OlaBrasilResource
     */
    public OlaBrasilResource() {
    }

    @GET
    public String getJson(String content) throws JSONException {
    	return "Hola :)";
    }
    
    /**
     * Retrieves representation of an instance of br.ceptro.measure.restfullexample.OlaBrasilResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/json")
    public String postJson(String content) throws JSONException {

        JSONObject jSONObject = new JSONObject(content);
        String value = jSONObject.getString("value");

        return "Your web service works so well !!! ValueIs="+ value;
    }

    /**
     * PUT method for updating or creating an instance of OlaBrasilResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public String putJson(String content) {
         return "Example PUT";
    }
}
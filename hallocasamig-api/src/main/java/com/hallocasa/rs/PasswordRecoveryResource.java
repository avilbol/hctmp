package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;
import com.hallocasa.services.user.PasswordRecoveryService;
import com.hallocasa.vo.PasswordRecoveryRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/password_recovery")
@Api(value = "/password_recovery", tags = "Password Recovery")
public class PasswordRecoveryResource {

	@EJB
	PasswordRecoveryService passwordRecoveryService;
	
	@GET
	@Produces({ MediaType.TEXT_HTML })
	@Path("send_email")
	@Auth
	@ApiOperation(value = "Send an email to user that has forgotted his/her password",
			notes= "In this email it will be contained all process information to change password "
					+ "and recover access")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "The email to send recovery password proces",
				required = true, dataType = "string", paramType = "query")
	})
	public Response sendEmail(@Context UriInfo uriInfo) {
		passwordRecoveryService.sendPasswordRecovery(
				uriInfo.getQueryParameters().getFirst("email"), uriInfo.getRequestUri().toString());
		return Response.status(HttpStatus.SC_OK).entity("Email sent succesfully").build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("validate_token")
	@Auth
	@ApiOperation(value = "Validate that password recovery token supplied was valid and current")
	@ApiResponses({ @ApiResponse(code = 401, message = "If token is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
		@ApiImplicitParam(name = "password_recovery_token", value = "Password recovery token sent in link email to recover password",
				required = true, dataType = "string", paramType = "query")
	})
	public Response validateToken(@Context UriInfo uriInfo) {
		PasswordRecoveryRequest passwordRecoveryRequest = passwordRecoveryService.validatePasswordRecoveryToken(
				uriInfo.getQueryParameters().getFirst("password_recovery_token"));
		return Response.status(HttpStatus.SC_OK).entity(passwordRecoveryRequest).build();
	}
	
	@POST
	@Path("update_password")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	@Auth
	@ApiOperation(value = "Update the password user, finishing the recovery password process")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized or token fails in validation"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response updatePassword(@ApiParam("password recovery request") PasswordRecoveryRequest passwordRecoveryRequest) {
		passwordRecoveryService.confirmPasswordRecovery(passwordRecoveryRequest);
		return Response.status(HttpStatus.SC_OK).entity("User password updated succesfully").build();
	}
}

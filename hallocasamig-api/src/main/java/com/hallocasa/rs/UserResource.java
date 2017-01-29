package com.hallocasa.rs;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpStatus;

import com.hallocasa.rs.security.Auth;
import com.hallocasa.rs.security.Secured;
import com.hallocasa.services.user.UserService;
import com.hallocasa.vo.User;
import com.hallocasa.vo.dto.UserListRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import static com.hallocasa.rs.security.constants.SecurityConstants.O_AUTH_TOKEN_HEADER;

@Path("/user")
@Api(value="/user", tags = "users")
public class UserResource extends BasicResource {

	@EJB
    UserService userService;
	
	@POST
	@Path("fetch_random")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Auth
	@ApiOperation(value = "Fetch random list of users, with basic data")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response fetchRandomUsers(
			@ApiParam(value = "user_list_request") UserListRequest userListRequest) {
		return Response.status(HttpStatus.SC_OK).entity(
				userService.addUsersToShowableList(userListRequest)).build();
	}
	
	@POST
	@Path("register")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	@Auth
	@ApiOperation(value = "Register new user in the system")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response register(
			@ApiParam(value = "user") User user) {
		userService.register(user);
		return Response.status(HttpStatus.SC_OK).entity(
				"User registered succesfully").build();
	}
	
	@GET
	@Path("activate_user")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	@Auth
	@ApiOperation(value = "Activate the account of specified user")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "The email to validate",
				required = true, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "activation_key", value = "The activation key, "
				+ "supplied in email sent to user",
				required = true, dataType = "string", paramType = "query")
	})
	public Response activateUser(@Context UriInfo uriInfo) {
		String activationKey = uriInfo.getQueryParameters().getFirst("activation_key");
		String email = uriInfo.getQueryParameters().getFirst("email");
		userService.activateUser(email, activationKey);
		return Response.status(HttpStatus.SC_OK).entity(
				"User activated succesfully").build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	@Secured
	@ApiOperation(value = "Save the data of user in the system")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response save(
			@ApiParam(value = "user") User user,
			@HeaderParam(O_AUTH_TOKEN_HEADER) String oAuthToken) {
		userService.save(user, oAuthToken);
		return Response.status(HttpStatus.SC_OK).entity(
				"User saved succesfully").build();
	}
	
	@GET
	@Path("validate_email")
	@Produces({ MediaType.TEXT_HTML })
	@Auth
	@ApiOperation(value = "Fetch random list of users, with basic data")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "The email to validate",
				required = true, dataType = "string", paramType = "query")
	})
	public Response validateEmail(@Context UriInfo uriInfo) {
		userService.validate(uriInfo.getQueryParameters().getFirst("email"));
		return Response.status(HttpStatus.SC_OK).entity(
				"Email ready to register").build();
	}

	@GET
	@Path("detail/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Secured
	@ApiOperation(value = "Fetch user detail acoording to its id")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response find(@ApiParam(value = "user id") @PathParam("id") Long id) {
		return Response.status(HttpStatus.SC_OK).entity(
				userService.find(id)).build();
	}
	
	@GET
	@Path("logout")
	@Produces({ MediaType.TEXT_HTML })
	@Secured
	@ApiOperation(value = "Fetch random list of users, with basic data")
	@ApiResponses({ @ApiResponse(code = 401, message = "If user is unauthorized"),
			@ApiResponse(code = 500, message = "If server internal error"),
			@ApiResponse(code = 200, message = "Ok. Generated resource") })
	public Response find(@HeaderParam(O_AUTH_TOKEN_HEADER) String oAuthToken) {
		userService.logout(oAuthToken);
		return Response.status(HttpStatus.SC_OK).entity("User logged out succesfully").build();
	}
}

package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UsersApiService;
import io.swagger.api.factories.UsersApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Observation;
import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.*;

@Path("/users")


@io.swagger.annotations.Api(description = "the users API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-09T12:54:48.614Z")
public class UsersApi  {
   private final UsersApiService delegate = UsersApiServiceFactory.getUsersApi();

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets some users", notes = "Returns a list of all users.", response = Observation.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "A list of users", response = Observation.class, responseContainer = "List") })
    public Response usersGet(@ApiParam(value = "Identifies the user that are logged on the app (service)" ,required=true)@HeaderParam("customuserid") String customuserid
,@ApiParam(value = "Identifies the system created the observation" )@HeaderParam("customsystemid") String customsystemid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersGet(customuserid,customsystemid,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates a user", notes = "Adds a new user to the user list.", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "User succesfully created", response = User.class) })
    public Response usersPost(@ApiParam(value = "" ,required=true) User body, @Context UriInfo uriInfo
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersPost(body,uriInfo, securityContext);
    }
    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update user", notes = "Update existing user by docid.", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "User succesfully updated", response = User.class) })
    public Response usersPut(@ApiParam(value = "The user to update" ,required=true) User user, @Context UriInfo uriInfo
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersPut(user,uriInfo,securityContext);
    }
    @GET
    @Path("/{userid}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get specified user", notes = "Returns one user that match userid.", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "A user", response = User.class) })
    public Response usersUseridGet(@ApiParam(value = "",required=true) @PathParam("userid") String userid
,@ApiParam(value = "Identifies the user that are logged on the app (service)" ,required=true)@HeaderParam("customuserid") String customuserid
,@ApiParam(value = "Identifies the system created the observation" )@HeaderParam("customsystemid") String customsystemid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersUseridGet(userid,customuserid,customsystemid,securityContext);
    }
    @GET
    @Path("/username/{username}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get user by username", notes = "Returns one user that match username.", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "A user", response = User.class) })
    public Response usersUsernameUsernameGet(@ApiParam(value = "The user's name",required=true) @PathParam("username") String username
,@ApiParam(value = "Identifies the user that are logged on the app (service)" ,required=true)@HeaderParam("customuserid") String customuserid
,@ApiParam(value = "Identifies the system created the observation" )@HeaderParam("customsystemid") String customsystemid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersUsernameUsernameGet(username,customuserid,customsystemid,securityContext);
    }
}

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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-26T08:28:50.378Z")
public class UsersApi  {
   private final UsersApiService delegate = UsersApiServiceFactory.getUsersApi();

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Observation.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Observation.class, responseContainer = "List") })
    public Response usersGet(@ApiParam(value = "" ,required=true)@HeaderParam("customsystemid") String customsystemid
,@ApiParam(value = "" ,required=true)@HeaderParam("customuserid") String customuserid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersGet(customsystemid,customuserid,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = User.class) })
    public Response usersPost(@ApiParam(value = "" ,required=true) User body, @Context UriInfo uriInfo
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersPost(body,uriInfo, securityContext);
    }
    @GET
    @Path("/{userid}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = User.class) })
    public Response usersUseridGet(@ApiParam(value = "",required=true) @PathParam("userid") String userid
,@ApiParam(value = "" ,required=true)@HeaderParam("customsystemid") String customsystemid
,@ApiParam(value = "" ,required=true)@HeaderParam("customuserid") String customuserid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersUseridGet(userid,customsystemid,customuserid,securityContext);
    }
    @GET
    @Path("/username/{username}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = User.class) })
    public Response usersUsernameUsernameGet(@ApiParam(value = "",required=true) @PathParam("username") String username
,@ApiParam(value = "" ,required=true)@HeaderParam("customsystemid") String customsystemid
,@ApiParam(value = "" ,required=true)@HeaderParam("customuserid") String customuserid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersUsernameUsernameGet(username,customsystemid,customuserid,securityContext);
    }
}

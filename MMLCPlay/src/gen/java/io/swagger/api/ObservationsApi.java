package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ObservationsApiService;
import io.swagger.api.factories.ObservationsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Observation;

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

@Path("/observations")


@io.swagger.annotations.Api(description = "the observations API")
//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-26T08:28:50.378Z")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-25T15:49:48.554Z")
public class ObservationsApi  {
   private final ObservationsApiService delegate = ObservationsApiServiceFactory.getObservationsApi();

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Observation.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Observation.class, responseContainer = "List") })
    public Response observationsGet(@ApiParam(value = "" ,required=true)@HeaderParam("customsystemid") String customsystemid
,@ApiParam(value = "" ,required=true)@HeaderParam("customuserid") String customuserid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsGet(customsystemid,customuserid,securityContext);
    }
    @GET
    @Path("/{observationid}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Observation.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Observation.class) })
    public Response observationsObservationidGet(@ApiParam(value = "",required=true) @PathParam("observationid") String observationid
,@ApiParam(value = "" ,required=true)@HeaderParam("customsystemid") String customsystemid
,@ApiParam(value = "" ,required=true)@HeaderParam("customuserid") String customuserid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsObservationidGet(observationid,customsystemid,customuserid,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Observation.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Observation.class) })
    public Response observationsPost(@ApiParam(value = "" ,required=true) Observation body, @Context UriInfo uriInfo
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsPost(body,uriInfo,securityContext);
    }
    @GET
    @Path("/type/{observationtype}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Observation.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Observation.class, responseContainer = "List") })
    public Response observationsTypeObservationtypeGet(@ApiParam(value = "",required=true) @PathParam("observationtype") String observationtype
,@ApiParam(value = "" ,required=true)@HeaderParam("customsystemid") String customsystemid
,@ApiParam(value = "" ,required=true)@HeaderParam("customuserid") String customuserid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsTypeObservationtypeGet(observationtype,customsystemid,customuserid,securityContext);
    }
}

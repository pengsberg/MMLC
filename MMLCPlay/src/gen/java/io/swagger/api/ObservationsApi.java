package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ObservationsApiService;
import io.swagger.api.factories.ObservationsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import java.util.Date;
import io.swagger.model.Observation;
import io.swagger.model.Observations;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-09T12:54:48.614Z")
public class ObservationsApi  {
   private final ObservationsApiService delegate = ObservationsApiServiceFactory.getObservationsApi();

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets some observations", notes = "Returns a list containing observations for the customerid", response = Observations.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "A list of observations", response = Observations.class) })
    public Response observationsGet(@ApiParam(value = "Identifies the user that are logged on the app (service)" ,required=true)@HeaderParam("customuserid") String customuserid
,@ApiParam(value = "Identifies the system created the observation" )@HeaderParam("customsystemid") String customsystemid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsGet(customuserid,customsystemid,securityContext);
    }
    @GET
    @Path("/{observationid}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Observation.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "An observation", response = Observation.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "The observation does not exist", response = Observation.class) })
    public Response observationsObservationidGet(@ApiParam(value = "The observation identificator",required=true) @PathParam("observationid") String observationid
,@ApiParam(value = "Identifies the user that are logged on the app (service)" ,required=true)@HeaderParam("customuserid") String customuserid
,@ApiParam(value = "Identifies the system created the observation" )@HeaderParam("customsystemid") String customsystemid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsObservationidGet(observationid,customuserid,customsystemid,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates an observation", notes = "Adds a new observation to the observations list", response = Observation.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Observation succesfully created", response = Observation.class) })
    public Response observationsPost(@ApiParam(value = "The observation to create" ,required=true) Observation observation, @Context UriInfo uriInfo
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsPost(observation, uriInfo, securityContext);
    }
    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updates an observation", notes = "Update existing observation by docid.", response = Observation.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Observation succesfully updated", response = Observation.class) })
    public Response observationsPut(@ApiParam(value = "The observation to update" ,required=true) Observation observation
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsPut(observation,securityContext);
    }
    @GET
    @Path("/type/{observationtype}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets some observations", notes = "Returns a list of observations for its observation type and customuserid", response = Observations.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "A list of observations", response = Observations.class) })
    public Response observationsTypeObservationtypeGet(@ApiParam(value = "The type of observation",required=true) @PathParam("observationtype") String observationtype
,@ApiParam(value = "Identifies the user that are logged on the app (service)" ,required=true)@HeaderParam("customuserid") String customuserid
,@ApiParam(value = "The start date of a date range") @QueryParam("startdate") Date startdate
,@ApiParam(value = "The end date of a date range") @QueryParam("enddate") Date enddate
,@ApiParam(value = "Sorting order of observation") @QueryParam("sort") String sort
,@ApiParam(value = "Limits the number of returned items", defaultValue="10") @DefaultValue("10") @QueryParam("limit") Integer limit
,@ApiParam(value = "Identifies the system created the observation" )@HeaderParam("customsystemid") String customsystemid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.observationsTypeObservationtypeGet(observationtype,customuserid,startdate,enddate,sort,limit,customsystemid,securityContext);
    }
}

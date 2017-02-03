package io.swagger.api.impl;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.ObservationsApiService;
import io.swagger.model.Observation;
import life.memy.data.DatabaseClass;
import life.memy.data.ObservationServiceDao;
import life.memy.exception.DataNotFoundException;

//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-07T18:24:01.292Z")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-25T15:49:48.554Z")
public class ObservationsApiServiceImpl extends ObservationsApiService {
	private DatabaseClass database = DatabaseClass.getDatabaseClass();
	ObservationServiceDao observationService = database.getObservationRepo();
	
    @Override
    public Response observationsGet(String customsystemid, String customuserid, SecurityContext securityContext) throws NotFoundException {
        List<Observation> observations = observationService.getAllObservations(customuserid);
        //List<Observation> observations = observationService.getAllObservations();
        return Response.ok().entity(observations).build();
    }
    @Override
    public Response observationsObservationidGet(String observationid, String customsystemid, String customuserid, SecurityContext securityContext) throws NotFoundException {
    	Observation observation = observationService.findById(observationid, Observation.class);
        return Response.ok().entity(observation).build();
    }
    @Override
    public Response observationsPost(Observation observationIn, UriInfo uriInfo, SecurityContext securityContext) throws NotFoundException {
    	Observation observationOut = observationService.create(observationIn);
    	String newId = String.valueOf(observationOut.getDocid());
    	URI uri = uriInfo
    			.getAbsolutePathBuilder()
    			.path(newId)
    			.build();
        return Response.created(uri)
        		.entity(observationOut)
        		.build();
    }
    @Override
    public Response observationsTypeObservationtypeGet(String observationtype,String customsystemid,String customuserid,SecurityContext securityContext) throws NotFoundException {
    	List<Observation> observations = observationService.findByObservationtype(observationtype,customuserid);
    	return Response.ok().entity(observations).build();
    }  
   
    
    //public Response observationsTypeObservationtypeGet(String observationtype,String customsystemid,String customuserid,SecurityContext securityContext) throws NotFoundException {
    //	Observation observationOut = observationService.findByObservationtype(observationtype,customuserid);
    //    return Response.ok().entity(observationOut).build();
    //}
}

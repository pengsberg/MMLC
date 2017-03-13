package io.swagger.api.impl;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.ObservationsApiService;
import io.swagger.model.Observation;
import life.memy.data.DatabaseClass;
import life.memy.data.ObservationServiceDao;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-09T12:54:48.614Z")
public class ObservationsApiServiceImpl extends ObservationsApiService {
	private DatabaseClass database = DatabaseClass.getDatabaseClass();
	ObservationServiceDao observationService = database.getObservationRepo();
	
    @Override
    public Response observationsGet(String customuserid, String customsystemid, SecurityContext securityContext) throws NotFoundException {
        List<Observation> observations = observationService.getAllObservations(customuserid);
        return Response.ok().entity(observations).build();
    }
    @Override
    public Response observationsObservationidGet(String observationid, String customsystemid, String customuserid, SecurityContext securityContext) throws NotFoundException {
    	Observation observation = observationService.findById(observationid);
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
    public Response observationsPut(Observation observationIn, SecurityContext securityContext) throws NotFoundException {
        Observation observation = observationService.update(observationIn);
        return Response.ok().entity(observation).build();
    }
    @Override
    public Response observationsTypeObservationtypeGet(String observationtype, String customuserid, String startdate, String enddate, String sort, Integer limit, String customsystemid, SecurityContext securityContext) throws NotFoundException {
    	Response response = observationService.findByObservationtype(observationtype,customuserid, startdate, enddate, sort, limit, customsystemid);
        return response;
    }
}

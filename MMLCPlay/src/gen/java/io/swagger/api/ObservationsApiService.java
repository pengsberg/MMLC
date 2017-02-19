package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.util.Date;
import io.swagger.model.Observation;
import io.swagger.model.Observations;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-09T12:54:48.614Z")
public abstract class ObservationsApiService {
    public abstract Response observationsGet(String customuserid,String customsystemid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response observationsObservationidGet(String observationid,String customuserid,String customsystemid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response observationsPost(Observation observation, UriInfo uriInfo, SecurityContext securityContext) throws NotFoundException;
    public abstract Response observationsPut(Observation observation, SecurityContext securityContext) throws NotFoundException;
    public abstract Response observationsTypeObservationtypeGet(String observationtype,String customuserid,Date startdate,Date enddate,String sort,Integer limit,String customsystemid,SecurityContext securityContext) throws NotFoundException;
}

package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Observation;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-17T17:56:44.743Z")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-25T15:49:48.554Z")
public abstract class ObservationsApiService {
    public abstract Response observationsGet(String customsystemid,String customuserid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response observationsObservationidGet(String observationid,String customsystemid,String customuserid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response observationsTypeObservationtypeGet(String observationtype,String customsystemid,String customuserid,SecurityContext securityContext) throws NotFoundException;
	public abstract Response observationsPost(Observation observationIn, UriInfo uriInfo, SecurityContext securityContext)throws NotFoundException;
}

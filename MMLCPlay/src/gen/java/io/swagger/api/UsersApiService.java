package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Observation;
import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-09T12:54:48.614Z")
public abstract class UsersApiService {
    public abstract Response usersGet(String customuserid,String customsystemid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response usersPost(User body,UriInfo uriInfo,SecurityContext securityContext) throws NotFoundException;
    public abstract Response usersPut(User user,UriInfo uriInfo,SecurityContext securityContext) throws NotFoundException;
    public abstract Response usersUseridGet(String userid,String customuserid,String customsystemid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response usersUsernameUsernameGet(String username,String customuserid,String customsystemid,SecurityContext securityContext) throws NotFoundException;
}

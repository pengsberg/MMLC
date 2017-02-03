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

//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-26T08:28:50.378Z")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-25T15:49:48.554Z")
public abstract class UsersApiService {
    public abstract Response usersGet(String customsystemid,String customuserid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response usersPost(User body,UriInfo uriInfo, SecurityContext securityContext) throws NotFoundException;
    public abstract Response usersPut(User body,UriInfo uriInfo, SecurityContext securityContext) throws NotFoundException;
    public abstract Response usersUseridGet(String userid,String customsystemid,String customuserid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response usersUsernameUsernameGet(String username,String customsystemid,String customuserid,SecurityContext securityContext) throws NotFoundException;
}

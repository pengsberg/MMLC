package io.swagger.api.impl;

import java.net.URI;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.swagger.api.NotFoundException;
import io.swagger.api.UsersApiService;
import io.swagger.model.User;
import life.memy.data.DatabaseClass;
import life.memy.data.UserServiceDao;

//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-07T18:24:01.292Z")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-25T15:49:48.554Z")
public class UsersApiServiceImpl extends UsersApiService {
	private DatabaseClass database = DatabaseClass.getDatabaseClass();
	UserServiceDao userService = database.getUserRepo();
	
    @Override
    public Response usersGet(String customsystemid, String customuserid, SecurityContext securityContext) throws NotFoundException {
        List<User> users = userService.getAllUsers();
        return Response.ok().entity(users).build();
    }
    @Override
    public Response usersPost(User userIn, UriInfo uriInfo, SecurityContext securityContext) throws NotFoundException {
    	User userOut = userService.create(userIn);
    	String newId = String.valueOf(userOut.getDocid());
    	URI uri = uriInfo
    			.getAbsolutePathBuilder()
    			.path(newId)
    			.build();
        return Response.created(uri)
        		.entity(userOut)
        		.build();
    }
    @Override
    public Response usersPut(User userIn, UriInfo uriInfo, SecurityContext securityContext) throws NotFoundException {
    	User userOut = userService.update(userIn);
    	String newId = String.valueOf(userOut.getDocid());
    	URI uri = uriInfo
    			.getAbsolutePathBuilder()
    			.path(newId)
    			.build();
        return Response.created(uri)
        		.entity(userOut)
        		.build();
    }
    @Override
    public Response usersUseridGet(String userid, String customsystemid, String customuserid, SecurityContext securityContext) throws NotFoundException {
    	User user = userService.findById(userid, User.class);
        return Response.ok().entity(user).build();
    }
    @Override
    public Response usersUsernameUsernameGet(String username, String customsystemid, String customuserid, SecurityContext securityContext) throws NotFoundException {
        User user = userService.findByUsername(username);
        return Response.ok().entity(user).build();
    }
}

package io.swagger.api.factories;

import io.swagger.api.UsersApiService;
import io.swagger.api.impl.UsersApiServiceImpl;

//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-26T08:28:50.378Z")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-25T15:49:48.554Z")
public class UsersApiServiceFactory {
    private final static UsersApiService service = new UsersApiServiceImpl();

    public static UsersApiService getUsersApi() {
        return service;
    }
}

package io.swagger.api.factories;

import io.swagger.api.ObservationsApiService;
import io.swagger.api.impl.ObservationsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-26T08:28:50.378Z")
public class ObservationsApiServiceFactory {
    private final static ObservationsApiService service = new ObservationsApiServiceImpl();

    public static ObservationsApiService getObservationsApi() {
        return service;
    }
}

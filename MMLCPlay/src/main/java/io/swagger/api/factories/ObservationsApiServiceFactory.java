package io.swagger.api.factories;

import io.swagger.api.ObservationsApiService;
import io.swagger.api.impl.ObservationsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-09T12:54:48.614Z")
public class ObservationsApiServiceFactory {
    private final static ObservationsApiService service = new ObservationsApiServiceImpl();

    public static ObservationsApiService getObservationsApi() {
        return service;
    }
}

package com.evyatark.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/flask")
@RegisterRestClient(configKey = "flask-api")
//@RegisterRestClient
public interface FlaskService {
    @GET
    @Path("/start-page")
    @Produces("application/json")
    List<String> getUrlsInStartPage();
        //@Path("/name/{name}")
        //Set<Country> getUrlsInStartPage(@PathParam String urlOfStartPage);


}

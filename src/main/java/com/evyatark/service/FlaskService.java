package com.evyatark.service;

import com.evyatark.entity.ArticleDetails;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.util.List;

@Path("/flask")
@RegisterRestClient(configKey = "flask-api")    // indirection, url is value of 'flask-api/mp-rest/url' from application.properties
public interface FlaskService {
    @GET
    @Path("/start-page")
    @Produces("application/json")
    List<String> getUrlsInStartPage();

    /*
    This endpoint, when called from quarkus, sending request to Python Flask,
    and receiving response with a JSON representation of ArticleDetails,
    causes exception
    javax.ws.rs.ProcessingException: RESTEASY008200: JSON Binding deserialization error:
        javax.json.bind.JsonbException: Internal error: Unexpected char 39 at (line no=1, column no=2, offset=1)

        It happens I think when Quarkus tries to deserialize the JSON to a Java object.
     */
    @GET
    @Path("/scrape-single-page/{url}")
    @Produces("application/json")
    ArticleDetails getArticleDetails(@PathParam("url") String url);

    @GET
    @Path("/scrape-single-page2/{siteId}")
    @Produces("plain/text")
    String getArticleDetails2(@PathParam("siteId") String siteId);

    @GET
    @Path("/strip-single-page/{siteId}")
    @Produces("text/html")
    String stripHaaretzArticle(@PathParam("siteId") String siteId) ;




}

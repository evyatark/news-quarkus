package com.evyatark.controller;

import com.evyatark.Repository.ArticleDetailsStorage;
import com.evyatark.entity.ArticleDetails;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import io.smallrye.reactive.messaging.annotations.Channel;

/**
 * A simple resource retrieving the "in-memory" "my-data-stream" and sending the items to a server sent event.
 */
@Path("/article/details")
public class ArticleDetailsResource {

    @Inject
    ArticleDetailsStorage articleDetailsStorage;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String all() {
        System.out.println("hello! counting articleDetails...");    // only for debugging
        long count = articleDetailsStorage.count();
        String result = "count=" + count;
        return result;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{url}")
    public String findOne(@PathParam("url") String url) {
        System.out.println("hello! searching by url " + url + " ...");    // only for debugging
        ArticleDetails result = articleDetailsStorage.getByOriginalURL(url);
        if (result == null) {
            System.out.println("not found!");
        }
        else {
            System.out.println("found: " + result);
        }
        return result.toString();
    }

}

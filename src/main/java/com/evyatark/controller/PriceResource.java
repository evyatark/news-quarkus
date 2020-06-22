package com.evyatark.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.reactivestreams.Publisher;

//import io.smallrye.reactive.messaging.annotations.Channel;
import org.eclipse.microprofile.reactive.messaging.Channel;

/**
 * A simple resource retrieving the "in-memory" "my-data-stream" and sending the items to a server sent event.
 */
@Path("/prices")
public class PriceResource {

    @Inject
    @Channel("my-data-stream")
    Publisher<Double> prices;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("hello");    // only for debugging
        return "hello";
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<Double> stream() {
        System.out.println("stream");   // only for debugging
        return prices;
    }
}

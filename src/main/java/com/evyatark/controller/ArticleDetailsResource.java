package com.evyatark.controller;

import com.evyatark.Repository.ArticleDetailsStorage;
import com.evyatark.entity.ArticleDetails;
import com.evyatark.service.ArticlesScraper;
import com.evyatark.service.ReadStartPages;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//import io.smallrye.reactive.messaging.annotations.Channel;

/**
 * A simple resource retrieving the "in-memory" "my-data-stream" and sending the items to a server sent event.
 */
@Path("/article/details")
public class ArticleDetailsResource {

    @Inject
    ArticleDetailsStorage articleDetailsStorage;

    @Inject
    ReadStartPages readStartPages;

    @Inject
    ArticlesScraper articlesScraper;


    /**
     * Calling this endpoints starts a "flow":
     * 1. call flask API that reads several Haaretz start pages,
     * (the flask Python implementation returns a list of URLs of articles of today).
     * 2. send all these URLs to stream (quarkus/Vertx event-bus) "generated-urls"
     * 3. ConsumeEvent on stream "generated-urls" (ArticleScraper) takes each URL and retrieve article details from that URL (by scraping the HTML of the article)
     * 4. send article details on stream "article-details-stream"
     * 5. ConsumeEvent on stream "article-details-stream" persists the details to PersistentStore (currently in-memory mock)
     * 6. receive the stripped HTML of the article can be done directly by calling "/read/haaretz/{siteId}" (readHaaretzSingleArticle() below)
     * @return
     *      dummy string. The actual operation is to insert urls of articles to the stream named "generated-urls"!
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/send")
    public String sendUrls() {
        System.out.println("hello! sending urls...");    // only for debugging

        readStartPages.startFromPage("dummy"); // currently argument is ignored. eventually calls flask API, that uses its own list of Haaretz start pages
        String result = "sent. call /count to see how many in storage";
        return result;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/scrapeDetails")
    public String findArticleDetails(@QueryParam("url") String url) {
        ArticleDetails ad = articlesScraper.process(url);
        return ad.toString();
    }


//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/count")
//    public String all() {
//        System.out.println("hello! counting articleDetails...");    // only for debugging
//        long count = articleDetailsStorage.count();
//        String result = "count=" + count;
//        return result;
//    }
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/display")
//    public String displayAll() {
//        System.out.println("hello! displaying articleDetails...");    // only for debugging
//        List<ArticleDetails> all = articleDetailsStorage.findAll();
//        String result = "all=" + all;
//        return result;
//    }
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/fromDB/{url}")
//    public String findOne(@PathParam("url") String url) {
//        System.out.println("hello! searching by url " + url + " ...");    // only for debugging
//        ArticleDetails result = articleDetailsStorage.getByOriginalURL(url);
//        if (result == null) {
//            System.out.println("not found!");
//        }
//        else {
//            System.out.println("found: " + result);
//        }
//        return result.toString();
//    }
//
//
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    @Path("/read/haaretz/{siteId}")
//    public String readHaaretzSingleArticle(@PathParam("siteId") String siteId) {
//        String html = articlesScraper.stripHtmlContentHaaretz(siteId);
//        return html;
//    }



}

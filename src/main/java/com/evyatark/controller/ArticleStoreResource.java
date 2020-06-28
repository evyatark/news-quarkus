package com.evyatark.controller;

import com.evyatark.Repository.ArticleDetailsStorage;
import com.evyatark.entity.ArticleDetails;
import com.evyatark.service.ArticlesScraper;
import com.evyatark.service.ReadStartPages;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//import io.smallrye.reactive.messaging.annotations.Channel;

/**
 * A simple resource retrieving the "in-memory" "my-data-stream" and sending the items to a server sent event.
 */
@Path("/article/content")
public class ArticleStoreResource {

    @Inject
    ArticleDetailsStorage articleDetailsStorage;

    @Inject
    ArticlesScraper articlesScraper;


    /**
     * articlesDetails are stored in PersistentStore (ArticleDetailsStorage).
     * article content is retrieved JIT by calling "" with siteId of article.
     */



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public String all() {
        System.out.println("hello! counting articleDetails...");    // only for debugging
        long count = articleDetailsStorage.count();
        String result = "count=" + count;
        return result;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/display")
    public String displayAll() {
        System.out.println("hello! displaying articleDetails...");    // only for debugging
        List<ArticleDetails> all = articleDetailsStorage.findAll();
        String result = "all=" + all;
        return result;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/fromdb/{url}")
    public String findOne(@PathParam("url") String url) {
        System.out.println("hello! searching by url " + url + " ...");    // only for debugging
        ArticleDetails result = articleDetailsStorage.getByOriginalURL(url);
        if (result == null) {
            System.out.println("not found!");
            return "not found!";
        }
        else {
            System.out.println("found: " + result);
        }
        return result.toString();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/fromdb1/{siteId}")
    public String findBySiteId(@PathParam("siteId") String siteId) {
        System.out.println("hello! searching by siteId " + siteId + " ...");    // only for debugging
        ArticleDetails result = articleDetailsStorage.getById(siteId);
        if (result == null) {
            System.out.println("not found!");
            return "not found!";
        }
        else {
            System.out.println("found: " + result);
        }
        return result.toString();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/read/haaretz/{siteId}")
    public String readHaaretzSingleArticle(@PathParam("siteId") String siteId) {
        String html = articlesScraper.stripHtmlContentHaaretz(siteId);
        return html;
    }



}

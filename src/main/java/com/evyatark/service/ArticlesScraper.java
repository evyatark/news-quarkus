package com.evyatark.service;

import com.evyatark.entity.ArticleDetails;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.vertx.mutiny.core.eventbus.EventBus;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class ArticlesScraper {

    @Inject
    EventBus bus;


//    @Incoming("generated-urls")
//    @Outgoing("article-details-stream")
//    @Broadcast
//    public ArticleDetails process(String url) {
//        System.out.println("converting " + url);     // only for debugging
//        ArticleDetails ad = scrapePage(url);
//        return ad;
//    }

    @ConsumeEvent("generated-urls")
    public ArticleDetails process(String url) {
        System.out.println("consumed event from 'generated-urls':  " + url);     // only for debugging
        ArticleDetails ad = scrapePage(url);
        bus.sendAndForget("article-details-stream", ad);
        System.out.println("sent " + ad.originalUrl + " to 'article-details-stream'");
        return ad;
    }

    // temporary implementation
    private ArticleDetails scrapePage(String url) {
        ArticleDetails ad = new ArticleDetails();
        ad.createdAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ad.updatedAt = ad.createdAt;
        ad.header = "A";
        ad.subHeader = "a";
        ad.site = "H";
        ad.url = url;
        ad.originalUrl = url;
        return ad;
    }
}

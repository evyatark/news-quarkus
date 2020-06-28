package com.evyatark.service;

import com.evyatark.entity.ArticleDetails;
import com.evyatark.util.Utils;
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

    @Inject
    FlaskClientService flaskClientService;

//    @Incoming("generated-urls")
//    @Outgoing("article-details-stream")
//    @Broadcast
//    public ArticleDetails process(String url) {
//        System.out.println("converting " + url);     // only for debugging
//        ArticleDetails ad = scrapePage(url);
//        return ad;
//    }

    @ConsumeEvent("generated-urls")
    public ArticleDetails process(final String url) {
        System.out.println("consumed event from 'generated-urls':  " + url);     // only for debugging
        String siteId = findSiteIdFromUrl(url); // this is currently specific to Haaretz site
        if (!Utils.isEmpty(siteId)) {
            System.out.println("1");
            ArticleDetails ad = scrapePage(siteId);
            System.out.println("10");
            System.out.println("scraped " + ad);
            bus.sendAndForget("article-details-stream", ad);
            System.out.println("sent " + ad.originalUrl + " to 'article-details-stream'");
            return ad;
        }
        else {
            System.out.println("Error: site id not found in url string " + url);
            return null;
        }
    }

    private String findSiteIdFromUrl(final String url) {
        // url:  https://www.haaretz.co.il/health/corona/.premium-MAGAZINE-1.8730699
        // or /gallery/music/.premium-1.8952956
        String siteId = Utils.findLastDigits(url);
        // siteId: 1.8730699
        return siteId;
    }

    private ArticleDetails scrapePage(final String siteId) {
        System.out.println("2");
        ArticleDetails ad = flaskClientService.scrapePage(siteId);
        System.out.println("20, ad=" + ad.iid);
        return ad;
    }

    public String stripHtmlContentHaaretz(final String siteId) {
        return flaskClientService.stripHaaretzArticle(siteId);
    }
}

package com.evyatark.service;

import io.reactivex.Flowable;
import io.vertx.mutiny.core.eventbus.EventBus;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class ReadStartPages {

    @Inject
    EventBus bus;

    @Inject
    FlaskClientService flaskClientService;

    //List<String> urls = scrapeStartPage("abc");

    public void startFromPage(String url) {
        System.out.println("startFromPage, url=" + url);
        List<String> urls = scrapeStartPage(url);
        System.out.println("startFromPage, sending list=" + urls);
        sendToStream(urls);
    }

    // happens automatically!
//    @Outgoing("generated-urls")
//    public Flowable<String> sendTemporary() {
//        return Flowable.fromIterable(urls);
//    }

    public void sendToStream(List<String> urls) {
        System.out.println("sendToStream, urls=" + urls);
        // send to "generated-urls" channel. See ArticleScraper that reads from that channel
        urls.stream().forEach(url -> {
            System.out.println("send to 'generated-urls': " + url);
            bus.sendAndForget("generated-urls", url);
            System.out.println("sent");
        });
        //bus.sendAndForget("generated-urls", urls.get(0));
    }

    // temporary implementation
    private List<String> scrapeStartPage(String url) {
        //return Arrays.asList(url + "1", url + "2", url + "3");
        return flaskClientService.startPage();  // calls rest endpoint in the Flask (Python) web server
    }
}




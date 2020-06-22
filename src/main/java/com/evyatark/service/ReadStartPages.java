package com.evyatark.service;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class ReadStartPages {

    List<String> urls = scrapeStartPage("abc");
//    public void startFromPage(String url) {
//        List<String> urls = scrapeStartPage(url);
//        sendToStream(urls);
//    }

    @Outgoing("generated-urls")
    public Flowable<String> sendToStream() {

        return Flowable.fromIterable(urls);
    }


    // temporary implementation
    private List<String> scrapeStartPage(String url) {
        return Arrays.asList(url + "1", url + "2", url + "3");
    }
}

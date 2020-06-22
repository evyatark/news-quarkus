package com.evyatark.service;

import com.evyatark.Repository.ArticleDetailsStorage;
import com.evyatark.entity.ArticleDetails;
import io.quarkus.vertx.ConsumeEvent;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StoreArticleDetails {

    @Inject
    ArticleDetailsStorage adStorage;

    //@Incoming("article-details-stream")
    @ConsumeEvent("article-details-stream")
    public  boolean storeArticleDetails(ArticleDetails articleDetails) {
        System.out.println("storing " + articleDetails.url);     // only for debugging
        boolean result = adStorage.add(articleDetails);
        return result;
    }

    /*
    it appears that there is no link between @Incoming("article-details-stream") and @ConsumeEvent("article-details-stream").
    calling bus.sendAndForget("generated-urls", url) inserts to channel "article-details-stream", that must be retrieved with @ConsumeEvent.
    @Incoming("article-details-stream") can only receive events from some @outgoing

    and the only way I know to send to @Outgoing is:
        @Outgoing("generated-urls")
        public Flowable<String> sendTemporary() {
            return Flowable.fromIterable(urls);
        }
    which happens automatically (immediately)
     */
}

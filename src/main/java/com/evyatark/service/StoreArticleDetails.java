package com.evyatark.service;

import com.evyatark.Repository.ArticleDetailsStorage;
import com.evyatark.entity.ArticleDetails;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StoreArticleDetails {

    @Inject
    ArticleDetailsStorage adStorage;

    @Incoming("article-details-stream")
    public  boolean storeArticleDetails(ArticleDetails articleDetails) {
        System.out.println("storing " + articleDetails.url);     // only for debugging
        boolean result = adStorage.add(articleDetails);
        return result;
    }

}

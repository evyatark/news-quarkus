package com.evyatark.service;

import com.evyatark.entity.ArticleDetails;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FlaskClientService {

    @Inject
    @RestClient
    FlaskService flaskService;


    public List<String> startPage() {
        List<String> result = flaskService.getUrlsInStartPage();
        return result;
    }


    public ArticleDetails scrapePage(String siteId) {
        System.out.println("3");
        ArticleDetails result = flaskService.getArticleDetails(siteId);
        //String result = flaskService.getArticleDetails(siteId);
        System.out.println("30");
        return result;
    }


    // :5000/flask/strip-single-page/1.8944244

    public String stripHaaretzArticle(String siteId) {
        String html = flaskService.stripHaaretzArticle(siteId);
        return html;
    }
}

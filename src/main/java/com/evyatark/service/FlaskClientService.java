package com.evyatark.service;

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
}

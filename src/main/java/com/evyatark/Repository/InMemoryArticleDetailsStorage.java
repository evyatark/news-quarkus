package com.evyatark.Repository;

import com.evyatark.entity.ArticleDetails;
import com.evyatark.util.Utils;

import java.util.ArrayList;
import java.util.List;

//@ApplicationScoped (commented because otherwise 2 beans implement ArticleDetailsStorage
// and for some unknown reason quarkus can't find the @Default one
public class InMemoryArticleDetailsStorage implements ArticleDetailsStorage {

    private static List<ArticleDetails> storage = new ArrayList<>();

    @Override
    public long count() {
        if (storage != null) {
           long result = storage.size() ;
           return result;
        }
        return 0;
    }

    @Override
    public boolean add(ArticleDetails ad) {
        if (ad != null) {
            boolean result = storage.add(ad);
            return result;
        }
        else {
            return false;
        }
    }

    @Override
    public List<ArticleDetails> findAll() {
        // TODO clone instead
        return storage;
    }

    @Override
    public ArticleDetails getById(String id) {
        if (Utils.isEmpty(id)) {
            return null;
        }
        if ((storage != null) && (!storage.isEmpty())) {
            ArticleDetails result = storage.stream().filter(ad -> ad.iid.equals(id)).findFirst().orElse(null);
            return result;
        }
        return null;
    }

    @Override
    public ArticleDetails getByOriginalURL(String originalUrl) {
        System.out.println(originalUrl);
        if (Utils.isEmpty(originalUrl)) {
            return null;
        }
        if ((storage != null) && (!storage.isEmpty())) {
            ArticleDetails result = storage.stream().filter(ad -> ad.originalUrl.equals(originalUrl)).findFirst().orElse(null);
            return result;
        }
        return null;
    }
}

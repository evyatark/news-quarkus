package com.evyatark.Repository;

import com.evyatark.entity.ArticleContent;
import com.evyatark.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class InMemoryArticleContentStorage implements ArticleContentStorage {
    private static List<ArticleContent> storage = new ArrayList<>();

    @Override
    public boolean add(ArticleContent ad) {
        if (ad != null) {
            boolean result = storage.add(ad);
            return result;
        }
        else {
            return false;
        }
    }

    @Override
    public ArticleContent getById(String id) {
        if (Utils.isEmpty(id)) {
            return null;
        }
        if ((storage != null) && (!storage.isEmpty())) {
            ArticleContent result = storage.stream().filter(ad -> ad.id.equals(id)).findFirst().orElse(null);
            return result;
        }
        return null;
    }

    @Override
    public ArticleContent getByURL(String url) {
        if (Utils.isEmpty(url)) {
            return null;
        }
        if ((storage != null) && (!storage.isEmpty())) {
            ArticleContent result = storage.stream().filter(ad -> ad.url.equals(url)).findFirst().orElse(null);
            return result;
        }
        return null;
    }
}

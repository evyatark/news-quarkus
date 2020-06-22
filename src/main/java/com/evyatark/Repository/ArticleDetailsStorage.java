package com.evyatark.Repository;

import com.evyatark.entity.ArticleDetails;

public interface ArticleDetailsStorage {

    long count();
    boolean add(ArticleDetails ad);
    ArticleDetails getById(String id) ;
    ArticleDetails getByOriginalURL(String originalUrl) ;
}

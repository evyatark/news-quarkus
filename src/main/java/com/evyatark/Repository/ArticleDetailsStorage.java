package com.evyatark.Repository;

import com.evyatark.entity.ArticleDetails;

import java.util.List;

public interface ArticleDetailsStorage {

    long count();
    boolean add(ArticleDetails ad);
    List<ArticleDetails> findAll() ;
    ArticleDetails getById(String id) ;
    ArticleDetails getByOriginalURL(String originalUrl) ;
}

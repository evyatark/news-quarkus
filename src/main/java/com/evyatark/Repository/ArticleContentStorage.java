package com.evyatark.Repository;

import com.evyatark.entity.ArticleContent;
import com.evyatark.entity.ArticleDetails;

public interface ArticleContentStorage {
    boolean add(ArticleContent ad);
    ArticleContent getById(String id) ;
    ArticleContent getByURL(String originalUrl) ;

}

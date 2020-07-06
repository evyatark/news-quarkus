package com.evyatark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.List;

@Entity
public class ArticleDetails extends PanacheEntity {

    @Column(length = 100, unique = true)
    public String iid;
    @Column(length = 20, unique = true)
    public String siteId;
    @Column(length = 20, unique = false)
    public String createdAt;
    @Column(length = 20, unique = false)
    public String updatedAt;
    @Column(length = 20, unique = false)
    public String site;
    @Column(length = 100, unique = false)
    public String author;
    @Column(length = 1000, unique = false)
    public String header;
    @Column(length = 1000, unique = false)
    public String subHeader;
    @Column(length = 4000, unique = false)
    public String description;
    @Column(length = 100, unique = false)
    public String subject;
    @Column(length = 100, unique = false)
    public String subSubject;
    @Column(length = 4000, unique = false)
    public String url;
    @Column(length = 4000, unique = false)
    public String originalUrl;
    @Column(length = 4000, unique = false)
    public String image1;
    @Column(length = 4000, unique = false)
    public String thumbnail;
    @Column(length = 4000, unique = false)
    public String image2;
    @Column(length = 40, unique = false)
    public String type; // HTML, Text, ...



    public static ArticleDetails findById(String value){
        return find("iid", value).firstResult();
    }

    public static ArticleDetails findBySiteId(String value){
        return find("siteId", value).firstResult();
    }

    public static ArticleDetails findByOriginalUrl(String value){
        return find("originalUrl", value).firstResult();
    }

    public static List<ArticleDetails> findByDate(String value){     // like 20200630
        return list("createdAt", value);
    }

    // see https://quarkus.io/guides/hibernate-orm-panache




    @Override
    public String toString() {
        return "ArticleDetails{" +
                "iid='" + iid + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", site='" + site + '\'' +
                ", author='" + author + '\'' +
                ", header='" + header + '\'' +
                ", subHeader='" + subHeader + '\'' +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                ", subSubject='" + subSubject + '\'' +
                ", url='" + url + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", image1='" + image1 + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", image2='" + image2 + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

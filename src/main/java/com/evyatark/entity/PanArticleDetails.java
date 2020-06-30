package com.evyatark.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.transaction.Status;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.List;

@Entity
public class PanArticleDetails extends PanacheEntity {

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



    public static PanArticleDetails findById(String value){
        return find("iid", value).firstResult();
    }

    public static PanArticleDetails findBySiteId(String value){
        return find("siteId", value).firstResult();
    }

    public static List<PanArticleDetails> findByDate(String value){     // like 20200630
        return list("createdAt", value);
    }

    // see https://quarkus.io/guides/hibernate-orm-panache
}

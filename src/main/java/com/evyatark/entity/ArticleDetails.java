package com.evyatark.entity;

import com.evyatark.util.Utils;

import java.util.Objects;

public class ArticleDetails {
    public String iid;
    public String siteId;
    public String createdAt;
    public String updatedAt;
    public String site;
    public String author;
    public String header;
    public String subHeader;
    public String description;
    public String subject;
    public String subSubject;
    public String url;
    public String originalUrl;
    public String image1;
    public String thumbnail;
    public String image2;
    public String type; // HTML, Text, ...



    public ArticleDetails() {
        //this.iid = Utils.createUUID();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleDetails)) return false;
        ArticleDetails that = (ArticleDetails) o;
        return Objects.equals(iid, that.iid) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(site, that.site) &&
                Objects.equals(author, that.author) &&
                Objects.equals(header, that.header) &&
                Objects.equals(subHeader, that.subHeader) &&
                Objects.equals(url, that.url) &&
                Objects.equals(originalUrl, that.originalUrl) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iid, createdAt, updatedAt, site, author, header, subHeader, url, originalUrl, type);
    }

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

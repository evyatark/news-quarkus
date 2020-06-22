package com.evyatark.entity;

import com.evyatark.util.Utils;

import java.util.Objects;

public class Article {

    public Article() {
        this.id = Utils.createUUID();
    }

    public Article(String id) {
        this.id = id;
    }

    public Article(String id, ArticleDetails details, ArticleContent content) {
        this(id);
        this.details = details;
        this.content = content;
    }

    public String id;
    public ArticleDetails details;
    public ArticleContent content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(details, article.details) &&
                Objects.equals(content, article.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, details, content);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", details=" + details +
                ", content=" + content +
                '}';
    }
}

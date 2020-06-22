package com.evyatark.entity;

import com.evyatark.util.Utils;

import java.util.Objects;

public class ArticleContent {
    public String id;
    public String url;
    public String originalContent;
    public String strippedContent;



    public ArticleContent() {
        this.id = Utils.createUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleContent)) return false;
        ArticleContent that = (ArticleContent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(url, that.url) &&
                Objects.equals(originalContent, that.originalContent) &&
                Objects.equals(strippedContent, that.strippedContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, originalContent, strippedContent);
    }

    @Override
    public String toString() {
        return "ArticleContent{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", originalContent='" + originalContent + '\'' +
                ", strippedContent='" + strippedContent + '\'' +
                '}';
    }
}

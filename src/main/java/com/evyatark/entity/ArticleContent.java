package com.evyatark.entity;

import com.evyatark.util.Utils;

import java.util.Objects;



/*
The original design was to read HTML of each article and store it in database as an instance of ArticleContent.

However, at least in case of Haaretz news site, we don't need this
because Haaretz site itself stores all articles. All you need to retrieve
an article is to know its siteId (which is part of the URL)

Retrieving a stripped HTML of the article (an HTML that bypasses the paywall and contains all the content of the article)
is done "online" (Just In Time) by invoking the Python in the flask web server.
The response time is a bit slow (several seconds), we'll see if user experience is good enough.

So, the "flow" that should happen periodically (once an hour?) is:
1. start from several start pages, scrape URLs of articles.
2. for each article URL, scrape its web page to retrieve article details (but not full content)
3. for each article Store articleDetails entity in PersistentStore

and, independently:
4. use all articleDetails entities in the PersistentStore to generate an HTML "index" page with
articles (typically from today, or any specified date). The generated index page contains headers
(and some other details such as date-time, subject, author etc), and a link to the content.

5. The link to the content is actually a lint to /read/haaretz/{siteId} endpoint served by this server.
It does a JIT retrieve and strip of the content, to serve a stripped version of the content.

 */


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

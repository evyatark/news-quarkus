package com.evyatark.Repository;

import com.evyatark.entity.ArticleDetails;
import com.evyatark.util.Utils;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class PostgresArticleDetailsStorage implements ArticleDetailsStorage {

    /**
     * for this to work, you need a PostgreSQL database.
     * Using docker:
docker run -d --rm --name=postgresdb  -p5432:5432 --env POSTGRES_PASSWORD=mypassword --volume /home/evyatar/work/quarkus/news-quarkus/src/main/resources/init:/docker-entrypoint-initdb.d/ postgres:11-alpine

     NOTE that:
     1. /home/evyatar/work/quarkus/news-quarkus/src/main/resources/init must NOT contain sub-dirs!
     (because Postgres looks also into sub-dirs of the init dir)
     it should contain only the setup.sql, with rw permission to all.
     (Postgres will execute any sql file in the init dir)
     2. --volume requires the absolute path of the init dir (hence /home/evyatar/work/quarkus/news-quarkus/src/main/resources/init and not ~/work/quarkus/news-quarkus/src/main/resources/init)
     3. if you wish to see the logs after the container is down, you should remove the --rm from the command
     * and then to open a psql client to that database (not mandatory):
     * docker exec -it postgresdb psql -U postgres
     */

    public void samples() {
        // creating a person
        ArticleDetails person = new ArticleDetails();
        person.siteId = "1.8900000";
        person.iid = person.siteId;
        person.url = "/sport/1.8900000";
        person.originalUrl = "https://www.haaretz.co.il/news/law/1.8958363";
        person.header = "I am a Header";
        person.subject = "subject1";
        person.createdAt = "20200630";

// persist it
        person.persist();

// note that once persisted, you don't need to explicitly save your entity: all
// modifications are automatically persisted on transaction commit.

// check if it's persistent
        if(person.isPersistent()){
            // delete it
            //person.delete();
        }


        // getting a list of all Person entities
        List<ArticleDetails> allPersons = ArticleDetails.listAll();

        // finding a specific person by ID via an Optional
        Optional<ArticleDetails> optional = ArticleDetails.findByIdOptional("1.8900000");
        person = optional.orElseThrow(() -> new NotFoundException());


// finding a specific person by ID
        person = ArticleDetails.findById("1.8900000");

// counting all persons
        long countAll = ArticleDetails.count();

// counting all living persons
        long countAlive = ArticleDetails.count("createdAt", "20200630");

// delete all living persons
        //PanArticleDetails.delete("createdAt", "20200630");

// delete all persons
        //PanArticleDetails.deleteAll();

// delete by id
        //boolean deleted = PanArticleDetails.deleteById("1.8900000");

// set the name of all living persons to 'Mortal'
        ArticleDetails.update("subject = 'subject2' where siteId = ?1", "1.8900000");
    }

    private void streamSample() {
        try (Stream<ArticleDetails> persons = ArticleDetails.streamAll()) {
            List<String> namesButEmmanuels = persons
                    .map(p -> p.createdAt.toLowerCase() )
                    .filter( n -> ! "20200630".equals(n) )
                    .collect(Collectors.toList());
        }
    }

    @Override
    public long count() {
        return ArticleDetails.count();
    }

    @Override
    public boolean add(ArticleDetails ad) {
        if (ad != null) {
            //ArticleDetails pad = fromArticleDetails(ad);
            ad.persist();
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public List<ArticleDetails> findAll() {
        List<ArticleDetails> allArticles = ArticleDetails.listAll();
        return allArticles; // toArticleDetailsAll(allArticles);
    }

//    private List<ArticleDetails> toArticleDetailsAll(List<ArticleDetails> allArticles) {
//        List<ArticleDetails> list = allArticles.stream().map(pad -> toArticleDetails(pad)).collect(Collectors.toList());
//        return list;
//    }

    @Override
    public ArticleDetails getById(String id) {
        if (Utils.isEmpty(id)) {
            return null;
        }
        ArticleDetails pad = ArticleDetails.findById(id);
        return pad;// toArticleDetails(pad);
    }

//    private ArticleDetails fromArticleDetails(ArticleDetailsIm x) {
//        ArticleDetails pad = new ArticleDetails();
//        pad.author = x.author;
//        pad.iid = x.iid;
//        pad.site = x.site;
//        pad.originalUrl = x.originalUrl;
//        pad.subHeader = x.subHeader;
//        pad.header = x.header;
//        pad.updatedAt = x.updatedAt;
//        pad.createdAt = x.createdAt;
//        pad.description = x.description;
//        pad.image1 = x.image1;
//        pad.image2 = x.image2;
//        pad.siteId = x.siteId;
//        pad.subject = x.subject;
//        pad.subSubject = x.subSubject;
//        pad.thumbnail = x.thumbnail;
//        pad.type = x.type;
//        pad.url = x.url;
//
//        return pad;
//    }
    
//    private ArticleDetailsIm toArticleDetails(ArticleDetails pad) {
//        ArticleDetailsIm ad = new ArticleDetailsIm();
//        ad.author = pad.author;
//        ad.iid = pad.iid;
//        ad.site = pad.site;
//        ad.originalUrl = pad.originalUrl;
//        ad.subHeader = pad.subHeader;
//        ad.header = pad.header;
//        ad.updatedAt = pad.updatedAt;
//        ad.createdAt = pad.createdAt;
//        ad.description = pad.description;
//        ad.image1 = pad.image1;
//        ad.image2 = pad.image2;
//        ad.siteId = pad.siteId;
//        ad.subject = pad.subject;
//        ad.subSubject = pad.subSubject;
//        ad.thumbnail = pad.thumbnail;
//        ad.type = pad.type;
//        ad.url = pad.url;
//
//        return ad;
//    }

    @Override
    public ArticleDetails getByOriginalURL(String originalUrl) {
        System.out.println(originalUrl);
        if (Utils.isEmpty(originalUrl)) {
            return null;
        }
        ArticleDetails pad = ArticleDetails.findByOriginalUrl(originalUrl);
        return pad;// toArticleDetails(pad);
    }
}

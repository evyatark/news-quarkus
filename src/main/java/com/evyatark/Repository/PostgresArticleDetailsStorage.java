package com.evyatark.Repository;

import com.evyatark.entity.ArticleDetails;
import com.evyatark.entity.PanArticleDetails;
import com.evyatark.util.Utils;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class PostgresArticleDetailsStorage implements ArticleDetailsStorage {

    public void samples() {
        // creating a person
        PanArticleDetails person = new PanArticleDetails();
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
        List<PanArticleDetails> allPersons = PanArticleDetails.listAll();

        // finding a specific person by ID via an Optional
        Optional<PanArticleDetails> optional = PanArticleDetails.findByIdOptional("1.8900000");
        person = optional.orElseThrow(() -> new NotFoundException());


// finding a specific person by ID
        person = PanArticleDetails.findById("1.8900000");

// counting all persons
        long countAll = PanArticleDetails.count();

// counting all living persons
        long countAlive = PanArticleDetails.count("createdAt", "20200630");

// delete all living persons
        //PanArticleDetails.delete("createdAt", "20200630");

// delete all persons
        //PanArticleDetails.deleteAll();

// delete by id
        //boolean deleted = PanArticleDetails.deleteById("1.8900000");

// set the name of all living persons to 'Mortal'
        PanArticleDetails.update("subject = 'subject2' where siteId = ?1", "1.8900000");
    }

    private void streamSample() {
        try (Stream<PanArticleDetails> persons = PanArticleDetails.streamAll()) {
            List<String> namesButEmmanuels = persons
                    .map(p -> p.createdAt.toLowerCase() )
                    .filter( n -> ! "20200630".equals(n) )
                    .collect(Collectors.toList());
        }
    }

    @Override
    public long count() {
        return PanArticleDetails.count();
    }

    @Override
    public boolean add(ArticleDetails ad) {
        if (ad != null) {
            boolean result = storage.add(ad);
            return result;
        }
        else {
            return false;
        }
    }

    @Override
    public List<ArticleDetails> findAll() {
        // TODO clone instead
        return storage;
    }

    @Override
    public ArticleDetails getById(String id) {
        if (Utils.isEmpty(id)) {
            return null;
        }
        if ((storage != null) && (!storage.isEmpty())) {
            ArticleDetails result = storage.stream().filter(ad -> ad.iid.equals(id)).findFirst().orElse(null);
            return result;
        }
        return null;
    }

    @Override
    public ArticleDetails getByOriginalURL(String originalUrl) {
        System.out.println(originalUrl);
        if (Utils.isEmpty(originalUrl)) {
            return null;
        }
        if ((storage != null) && (!storage.isEmpty())) {
            ArticleDetails result = storage.stream().filter(ad -> ad.originalUrl.equals(originalUrl)).findFirst().orElse(null);
            return result;
        }
        return null;
    }
}

package com.nosql.mongolib.service.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.nosql.mongolib.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@DataMongoTest
class BookServiceImplTest extends MongoLibApplicationTest {


    @Autowired
    private BookService bookService;

    @Configuration
    public static class MongoConfig{

        public @Bean
        MongoClient mongoclient() {
            return MongoClients.create("mongodb://localhost:27017");
        }

        public @Bean
        MongoTemplate mongoTemplate() {
            return new MongoTemplate(mongoclient(), "nolibrary");
        }
    }

    @Test
    void findAllBooks() {
    }

}
package com.nosql.mongolib.service.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan({"com.nosql.mongolib.repository", "com.nosql.mongolib.model"})
public abstract class MongoLibApplicationTest {
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
}
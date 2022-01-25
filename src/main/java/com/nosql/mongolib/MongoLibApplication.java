package com.nosql.mongolib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class MongoLibApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoLibApplication.class, args);
    }
}

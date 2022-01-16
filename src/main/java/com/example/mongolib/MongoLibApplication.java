package com.example.mongolib;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//@EnableMapRepositories
@EnableMongoRepositories
@SpringBootApplication
public class MongoLibApplication {

    public static void main(String[] args) {
        val cfg = SpringApplication.run(MongoLibApplication.class, args).getBean(BookRepository.class);
        cfg.save(Book.builder().id("4").title("new saved book").build());
        cfg.findAll().forEach(System.out::println);
    }
}

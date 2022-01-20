package com.example.mongolib;

import com.example.mongolib.service.LibraryService;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class MongoLibApplication {

    public static void main(String[] args) {
        val cfg = SpringApplication.run(MongoLibApplication.class, args).getBean(LibraryService.class);
        cfg.findAll().forEach(System.out::println);
    }
}

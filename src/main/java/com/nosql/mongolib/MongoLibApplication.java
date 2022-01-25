package com.nosql.mongolib;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.GenreRepository;
import com.nosql.mongolib.service.AuthorService;
import com.nosql.mongolib.service.BookService;
import lombok.val;
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

package com.example.mongolib;

import com.example.mongolib.model.Author;
import com.example.mongolib.model.Book;
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

//        cfg.findCommentsByBook("55401fc7-e819-4fb8-819b-5abbdf185581").forEach(System.out::println);
        Author author = Author.builder().name("John Thompson").build();
        Book book =  Book.builder()
                .author(author)
                .title("Spring Framework 5: Beginner to Guru")
                .build();

        cfg.save(book);
        cfg.findAllBooks().forEach(System.out::println);
    }
}

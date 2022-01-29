package com.nosql.mongolib;

//import com.github.cloudyrock.spring.v5.EnableMongock;

import com.github.cloudyrock.spring.v5.EnableMongock;
import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.service.AuthorService;
import com.nosql.mongolib.service.BookService;
import com.nosql.mongolib.service.CommentService;
import com.nosql.mongolib.service.GenreService;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties
public class MongoLibApplication {
    public static void main(String[] args) {
        val x = SpringApplication.run(MongoLibApplication.class, args);
        BookService b = x.getBean(BookService.class);
        AuthorService a = x.getBean(AuthorService.class);
        GenreService g = x.getBean(GenreService.class);
        CommentService c = x.getBean(CommentService.class);

        b.saveNewBook(Book.builder()
                .title("nn")
                .author(Author.builder()
                        .name("Lavina Mall")
                        .build())
                .genre(Genre.builder()
                        .genre("comedy")
                        .build())
                .build());
        b.findAllBooks().forEach(System.out::println);
        a.findAllAuthors().forEach(System.out::println);
        g.findAllGenres().forEach(System.out::println);
        System.out.println(c.findCommentsByBook("2"));

    }
}

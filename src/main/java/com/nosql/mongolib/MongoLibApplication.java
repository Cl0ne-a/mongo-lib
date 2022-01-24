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
        val cfg = SpringApplication.run(MongoLibApplication.class, args);

        val bookService = cfg.getBean(BookService.class);
        val authorService = cfg.getBean(AuthorService.class);
        val genreRepo = cfg.getBean(GenreRepository.class);
        Author author = Author.builder().name("John Thompson").build();
        Genre genre = genreRepo.findByGenre("science");
        Book book = Book.builder()
                .author(authorService.findById("1"))
                .genre(genre)
                .title("Spring Framework 5: Beginner to Guru")
                .build();
        bookService.save(book);
        val sth = bookService.byTitleAndAuthor("n", authorService.findById("1"));
        bookService.getAllByGenre(genre).forEach(System.out::println);
        val b = bookService.findById("1");
        System.out.println(b);
        bookService.findAllBooks().forEach(System.out::println);
    }
}

package com.nosql.mongolib.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.AuthorRepository;
import com.nosql.mongolib.repository.GenreRepository;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.service.CommentService;

import java.util.List;

@ChangeLog(order = "000")
public class InitMongoDBDataChangeLog {

    @ChangeSet(order = "001",id = "dropDB", author = "me", runAlways = true)
    public void dropDB(GenreRepository genreRepository,
                       AuthorRepository authorRepository,
                       BookRepository bookRepository) {
        genreRepository.deleteAll();
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @ChangeSet(order = "002", id = "initAuthor", author = "me", runAlways = true)
    public void initAuthor(AuthorRepository authorRepository) {
        Author aditya = Author.builder().id("1").name("Aditya Bhargava").build();
        Author randall = Author.builder().id("2").name("Randall Munroe").build();

        authorRepository.saveAll(List.of(aditya, randall));
    }

    @ChangeSet(order = "003", id = "initGenre", author = "me", runAlways = true)
    public void initGenre(GenreRepository genreRepository) {
        Genre science = Genre.builder()
                .id("1")
                .genre("science")
                .build();
        Genre comedy = Genre.builder()
                .id("2")
                .genre("comedy")
                .build();
        genreRepository.saveAll(List.of(comedy, science));
    }

    @ChangeSet(order = "004", id = "initBook", author = "me", runAlways = true)
    public void initBook(BookRepository bookRepository,
                         AuthorRepository authorRepository,
                         GenreRepository genreRepository,
                         CommentService commentService) {
        Book it = Book.builder()
                .id("1")
                .title("Grokking Algorithms")
                .author(authorRepository.findById("1").orElseThrow())
                .genre(genreRepository.findByGenre("science"))
                .build();

        Book comic = Book.builder()
                .id("2")
                .title("What If?")
                .author(authorRepository.findById("2").orElseThrow())
                .genre(genreRepository.findByGenre("comedy"))
                .build();

        bookRepository.saveAll(List.of(it, comic));
        commentService.addComment(
                bookRepository.findById("2").orElseThrow(() -> new RuntimeException("no book found by id 2")),
                "this book was hilarious");
    }
}

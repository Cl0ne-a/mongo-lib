package com.nosql.mongolib.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.service.AuthorService;
import com.nosql.mongolib.service.BookService;
import com.nosql.mongolib.service.CommentService;
import com.nosql.mongolib.service.GenreService;

import java.util.List;

@ChangeLog(order = "000")
public class InitMongoDBDataChangeLog {

    @ChangeSet(order = "001",id = "dropDB", author = "me", runAlways = true)
    public void dropDB(GenreService genreService,
                       AuthorService authorService,
                       BookService bookService) {
        genreService.deleteAll();
        authorService.deleteAll();
        bookService.deleteAll();
    }

    @ChangeSet(order = "002", id = "initAuthor", author = "me", runAlways = true)
    public void initAuthor(AuthorService authorService) {
        Author aditya = Author.builder().id("1").name("Aditya Bhargava").build();
        Author randall = Author.builder().id("2").name("Randall Munroe").build();

        authorService.saveAll(List.of(aditya, randall));
    }

    @ChangeSet(order = "003", id = "initGenre", author = "me", runAlways = true)
    public void initGenre(GenreService genreService) {
        Genre science = Genre.builder()
                .id("1")
                .genre("science")
                .build();
        Genre comedy = Genre.builder()
                .id("2")
                .genre("comedy")
                .build();
        genreService.saveAll(List.of(comedy, science));
    }

    @ChangeSet(order = "004", id = "initBook", author = "me", runAlways = true)
    public void initBook(BookService bookService,
                         AuthorService authorService,
                         GenreService genreService,
                         CommentService commentService) {
        Book it = Book.builder()
                .id("1")
                .title("Grokking Algorithms")
                .author(authorService.findById("1"))
                .genre(genreService.findByGenre("science"))
                .build();

        Book comic = Book.builder()
                .id("2")
                .title("What If?")
                .author(authorService.findById("2"))
                .genre(genreService.findByGenre("comedy"))
                .build();

        bookService.saveAll(List.of(it, comic));
        commentService.addComment(bookService.findById("2"), "this book was hilarious");
    }
}

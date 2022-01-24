package com.nosql.mongolib;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Comment;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.AuthorRepository;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.repository.CommentRepository;
import com.nosql.mongolib.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Loader implements CommandLineRunner {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    public Loader(GenreRepository genreRepository, BookRepository bookRepository, AuthorRepository authorRepository, CommentRepository commentRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) {
        commentRepository.deleteAll();
        genreRepository.deleteAll();
        authorRepository.deleteAll();
        bookRepository.deleteAll();

        Genre science = Genre.builder()
                .id("1")
                .genre("science")
                .build();
        Genre comedy = Genre.builder()
                .id("2")
                .genre("comedy")
                .build();
        genreRepository.saveAll(List.of(comedy, science));

        Author aditya = Author.builder().id("1").name("Aditya Bhargava").build();
        Author randall = Author.builder().id("2").name("Randall Munroe").build();

        authorRepository.saveAll(List.of(aditya, randall));
        Book it = Book.builder()
                .id("1")
                .title("Grokking Algorithms")
                .author(authorRepository.findById(aditya.getId())
                        .orElseThrow(() -> new RuntimeException("no such author")))
                .genre(genreRepository.findById(String.valueOf(science.getId())).orElseThrow())
                .build();

        Book comic = Book.builder()
                .id("2")
                .title("What If?")
                .author(randall)
                .genre(genreRepository.findById(String.valueOf(comedy.getId())).orElseThrow())
                .build();

        bookRepository.save(it);
        bookRepository.save(comic);

        Comment onlyComment = new Comment(comic, "this book was hilarious");
        commentRepository.save(onlyComment);
    }
}

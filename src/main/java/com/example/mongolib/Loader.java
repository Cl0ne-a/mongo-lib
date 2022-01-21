package com.example.mongolib;

import com.example.mongolib.model.Author;
import com.example.mongolib.model.Book;
import com.example.mongolib.repository.AuthorRepository;
import com.example.mongolib.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class Loader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Loader(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) {
        authorRepository.deleteAll();
        bookRepository.deleteAll();

        Author aditya = Author.builder().id(UUID.randomUUID().toString()).name("Aditya Bhargava").build();
        Author randall = Author.builder().id(UUID.randomUUID().toString()).name("Randall Munroe").build();

        authorRepository.saveAll(List.of(aditya, randall));
        Book it = Book.builder()
                .id(UUID.randomUUID().toString())
                .title("Grokking Algorithms")
                .author(authorRepository.findById(aditya.getId())
                        .orElseThrow(() -> new RuntimeException("no such author")))
                .build();

        Book comic = Book.builder()
                .id(UUID.randomUUID().toString())
                .title("What If?")
                .author(randall)
                .build();

        bookRepository.save(it);
        bookRepository.save(comic);
    }
}

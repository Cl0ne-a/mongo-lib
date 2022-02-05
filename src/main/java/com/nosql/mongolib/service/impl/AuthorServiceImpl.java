package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.repository.AuthorRepository;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.service.AuthorService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(String id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Author> list) {
        authorRepository.saveAll(list);
    }

    @Override
    public Author updateAuthor(String oldName, String newName) {
        val author = authorRepository.findByName(oldName);
        List<Book> allAuthorBooks = bookRepository.findByAuthor(author);

        author.setName(newName);
        allAuthorBooks.stream().forEach(book -> {
            book.setAuthor(author);
            bookRepository.save(book);
        });

        return authorRepository.save(author);
    }
}

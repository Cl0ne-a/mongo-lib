package com.nosql.mongolib.service;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;

import java.util.List;

public interface BookService {
    Book findById(String id);
    void deleteBook(Book book);
    Book save(Book newBook);
    List<Book> findAllBooks();
    List<Book> byTitleAndAuthor(String titleMatcher, Author author);
    List<Book> getAllByGenre(Genre genre);
    void deleteAll();
    void saveAll(List<Book> list);
}

package com.nosql.mongolib.service;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.rest.domain.BookDto;

import java.util.List;

public interface BookService {
    BookDto updateBook(String id, String newTitle);
    BookDto findById(String id);
    void deleteBook(String bookId);
    BookDto saveNewBook(BookDto newBook);
    List<BookDto> findAllBooks();
    List<Book> byTitleAndAuthor(String titleMatcher, Author author);
    List<Book> getAllByGenre(Genre genre);
    void deleteAll();
    List<Book> saveAll(List<Book> list);
}

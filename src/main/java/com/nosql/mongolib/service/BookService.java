package com.nosql.mongolib.service;

import com.nosql.mongolib.domain.BookDto;
import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;

import java.util.List;

public interface BookService {
    void updateBook(String id, String newTitle);
    BookDto findById(String id);
    void deleteBook(String bookId);
    BookDto saveNewBook(BookDto newBook);
    List<BookDto> findAllBooks();
    @Deprecated
    List<Book> byTitleAndAuthor(String titleMatcher, Author author);
    @Deprecated
    List<Book> getAllByGenre(Genre genre);
    @Deprecated
    void deleteAll();
    @Deprecated
    List<Book> saveAll(List<Book> list);
}

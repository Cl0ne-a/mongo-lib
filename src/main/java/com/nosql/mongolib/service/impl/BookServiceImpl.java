package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.service.BookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllByGenre(Genre genre) {
        return bookRepository.findAllByGenre(genre);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Book> list) {
        bookRepository.saveAll(list);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).orElseThrow(()
                -> new RuntimeException("no book by that id"));
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book saveNewBook(Book newBook) {
        val newBookTitle = newBook.getTitle();
        val newBookAuthor = newBook.getAuthor();
        val newBookGenre = newBook.getGenre();
        val bookToSave = bookRepository.findByGenreAndAuthor(newBookGenre, newBookAuthor);
        return bookToSave.isPresent() && newBookTitle.equals(bookToSave.get().getTitle())
                ? null
                : bookRepository.save(newBook);
    }

    @Override
    public List<Book> byTitleAndAuthor(String titleMatcher, Author author) {
        return bookRepository.findAllByTitleLikeAndAuthor(titleMatcher, author);
    }
}

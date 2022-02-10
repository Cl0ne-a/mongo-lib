package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.AuthorRepository;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.repository.GenreRepository;
import com.nosql.mongolib.service.BookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
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
    public List<Book> saveAll(List<Book> list) {
        return bookRepository.saveAll(list);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(String id, String newTitle) {
        bookRepository.findById(id)
                .ifPresent(book -> {
                    book.setTitle(newTitle);
                    bookRepository.save(book);
                });
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).orElseThrow(()
                -> new RuntimeException("no book by that id"));
    }

    @Override
    public void deleteBook(String bookId) {
        if(bookRepository.findById(bookId).isPresent()) {
            bookRepository.deleteById(bookId);
        } else {
            throw new RuntimeException("no such book in the library");
        }
    }

    @Override
    public Book saveNewBook(Book newBook) {
        val newBookTitle = newBook.getTitle();
        val newBookAuthor = newBook.getAuthor().getName();
        val newBookGenre = newBook.getGenre().getGenre();
        val bookToSave = bookRepository
                .findByTitleAndGenreAndAuthor(
                        newBookTitle,
                        genreRepository.findByGenre(newBookGenre),
                        authorRepository.findByName(newBookAuthor));
        if (bookToSave == null) {
            return bookRepository.save(newBook);
        } else {
            throw new RuntimeException("Book is already present");
        }
    }

    @Override
    public List<Book> byTitleAndAuthor(String titleMatcher, Author author) {
        return bookRepository.findAllByTitleLikeAndAuthor(titleMatcher, author);
    }
}

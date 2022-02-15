package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.domain.BookDto;
import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.AuthorRepository;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.repository.GenreRepository;
import com.nosql.mongolib.service.BookService;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<BookDto> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDto::bookToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateBook(String id, String newTitle) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setTitle(newTitle);
            bookRepository.save(book);
        });
    }

    @Override
    public BookDto findById(String id) {
        val book = bookRepository.findById(id).orElseThrow(()
                -> new RuntimeException("no book by that id"));
        return BookDto.bookToDto(book);
    }

    @Override
    public BookDto saveNewBook(BookDto newBook) {
        val newBookTitle = newBook.getTitle();
        val newBookAuthor = newBook.getAuthor();
        val newBookGenre = newBook.getGenre();
        val bookToSave = bookRepository
                .findByTitleAndGenreAndAuthor(
                        newBookTitle,
                        genreRepository.findByGenre(newBookGenre),
                        authorRepository.findByName(newBookAuthor));
        if (bookToSave == null) {
            val book = bookRepository.save(BookDto.toDomainObject(newBook));
            return BookDto.bookToDto(book);
        } else {
            throw new RuntimeException("Book is already present");
        }
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
    public void deleteBook(String bookId) {
        if(bookRepository.findById(bookId).isPresent()) {
            bookRepository.deleteById(bookId);
        } else {
            throw new RuntimeException("no such book in the library");
        }
    }

    @Override
    public List<Book> byTitleAndAuthor(String titleMatcher, Author author) {
        return bookRepository.findAllByTitleLikeAndAuthor(titleMatcher, author);
    }
}

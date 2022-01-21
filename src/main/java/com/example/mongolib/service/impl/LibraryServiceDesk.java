package com.example.mongolib.service.impl;

import com.example.mongolib.model.Author;
import com.example.mongolib.model.Book;
import com.example.mongolib.model.Comment;
import com.example.mongolib.repository.AuthorRepository;
import com.example.mongolib.repository.BookRepository;
import com.example.mongolib.repository.CommentRepository;
import com.example.mongolib.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceDesk implements LibraryService {
    
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public LibraryServiceDesk(BookRepository bookRepository, CommentRepository commentRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book save(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    public List<Book> findAllBooks() {
        Iterable<Book> iterable = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        iterable.forEach(books::add);
        return books;
    }

    @Override
    public List<Author> findAllAuthors() {
        Iterable<Author> iterable = authorRepository.findAll();
        List<Author> authors = new ArrayList<>();
        iterable.forEach(authors::add);
        return authors;
    }

    @Override
    public List<Comment> findCommentsByBook(String bookTitle) {
        Book book = bookRepository.findBookByTitle(bookTitle);
        return commentRepository.findAllByBook(book);
    }


}

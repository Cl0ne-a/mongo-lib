package com.example.mongolib.service;

import com.example.mongolib.model.Book;
import com.example.mongolib.model.Comment;

import java.util.List;

public interface LibraryService {
    Book save(Book newBook);
    List<Book> findAll();
    List<Comment> findCommentsByBook(String bookTitle);
}

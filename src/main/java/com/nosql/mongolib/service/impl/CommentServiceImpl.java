package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Comment;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    public CommentServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Comment> addComment(Book book, String commentLine) {
        Comment comment = Comment.builder()
                        .id(UUID.randomUUID().toString())
                        .commentLine(commentLine)
                .build();
        if(book.getComments() == null) {
            List<Comment> comments = new ArrayList<>();
            comments.add(comment);
            book.setComments(comments);

        } else {
            book.getComments().add(comment);
        }

        book = bookRepository.save(book);
        return book.getComments();
    }

    @Override
    public List<Comment> findCommentsByBook(String bookId) {
        return bookRepository.findById(bookId).isPresent()
                ? bookRepository.findById(bookId).get().getComments()
                : null;
    }
}

package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Comment;
import com.nosql.mongolib.repository.CommentRepository;
import com.nosql.mongolib.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(Book book, String commentLine) {
        Comment comment = new Comment(book, commentLine);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentsByBook(Book book) {
        return commentRepository.findAllByBook(book);
    }
}

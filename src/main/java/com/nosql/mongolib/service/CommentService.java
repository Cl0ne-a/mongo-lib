package com.nosql.mongolib.service;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> addComment(Book book, String commentLine);
    List<Comment> findCommentsByBook(String id);
}

package com.nosql.mongolib.service;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Book book, String comment);
    List<Comment> findCommentsByBook(Book book);
}

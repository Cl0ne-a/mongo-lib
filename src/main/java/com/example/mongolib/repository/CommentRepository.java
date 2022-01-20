package com.example.mongolib.repository;

import com.example.mongolib.model.Book;
import com.example.mongolib.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByBook(Book book);
}

package com.nosql.mongolib.repository;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByBook(Book book);
    void deleteAllByBook_Id(String bookId);
}

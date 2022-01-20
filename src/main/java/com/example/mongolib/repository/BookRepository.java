package com.example.mongolib.repository;

import com.example.mongolib.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findBookByTitle(String title);
}

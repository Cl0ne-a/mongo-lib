package com.nosql.mongolib.repository;

import com.nosql.mongolib.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
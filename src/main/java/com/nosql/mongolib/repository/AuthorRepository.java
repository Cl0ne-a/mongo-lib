package com.nosql.mongolib.repository;

import com.nosql.mongolib.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}

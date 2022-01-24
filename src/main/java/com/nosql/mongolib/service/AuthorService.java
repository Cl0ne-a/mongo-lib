package com.nosql.mongolib.service;

import com.nosql.mongolib.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();
    Author findById(String id);
}

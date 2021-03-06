package com.nosql.mongolib.service;

import com.nosql.mongolib.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();
    Author findById(String id);
    Author findByName (String name);
    void saveAuthor(Author author);
    void deleteAll();
    void saveAll(List<Author> list);
    Author updateAuthor(String oldName, String newName);
}

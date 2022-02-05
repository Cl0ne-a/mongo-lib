package com.nosql.mongolib.repository;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAllByTitleLikeAndAuthor(String titleMatcher, Author author);
    List<Book> findAllByGenre(Genre genre);
    List<Book> findByAuthor(Author author);
    Book findByTitleAndGenreAndAuthor(String title, Genre genre, Author author);
}

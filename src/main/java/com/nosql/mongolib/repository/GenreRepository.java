package com.nosql.mongolib.repository;

import com.nosql.mongolib.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByGenre(String genreName);
}

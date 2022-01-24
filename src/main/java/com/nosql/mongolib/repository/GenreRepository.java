package com.nosql.mongolib.repository;

import com.nosql.mongolib.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByGenre(String genreName);
}

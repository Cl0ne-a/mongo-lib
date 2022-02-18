package com.nosql.mongolib.service;

import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.rest.domain.GenreDto;

import java.util.List;

public interface GenreService {
    Genre save(Genre genre);
    Genre findByGenre(String genre);
    List<GenreDto> findAllGenres();
    void deleteAll();
    void saveAll(List<Genre> genres);
}

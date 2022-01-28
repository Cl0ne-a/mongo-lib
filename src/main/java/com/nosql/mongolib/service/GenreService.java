package com.nosql.mongolib.service;

import com.nosql.mongolib.model.Genre;

import java.util.List;

public interface GenreService {
    Genre findById(String id);
    List<Genre> listAllGenres();
    void deleteAll();
    void saveAll(List<Genre> genres);
}

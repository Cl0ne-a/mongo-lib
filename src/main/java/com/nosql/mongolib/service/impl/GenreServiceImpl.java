package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.GenreRepository;
import com.nosql.mongolib.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre findById(String id) {
        return genreRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Genre> listAllGenres() {
        return genreRepository.findAll();
    }
}

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
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre findByGenre(String genre) {
        return genreRepository.findByGenre(genre).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteAll() {
        genreRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Genre> genres) {
        genreRepository.saveAll(genres);
    }
}

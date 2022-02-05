package com.nosql.mongolib.listeners;

import com.mongodb.lang.Nullable;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.service.GenreService;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Allows adding new genre to DB in case it does not exist.
 * This is an independent instance, so we are not removing it after the book is deleted.
 */

@Component
public class MongoGenreBookSaveEventListenner extends AbstractMongoEventListener<Book> {

    private final GenreService genreService;

    public MongoGenreBookSaveEventListenner(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public void onBeforeConvert(@Nullable BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(Objects.requireNonNull(event));
        val book = event.getSource();
        Genre newGenre = book.getGenre();
        val genreFromDb = genreService
                .findAllGenres()
                .stream().filter(genre ->
                        genre.getGenre().equals(newGenre.getGenre()))
                .findFirst();
        genreFromDb.ifPresent(genre -> book.setGenre(genreService.findByGenre(genre.getGenre())));
        if (genreFromDb.isPresent()) {
            book.setGenre(genreService.findByGenre(genreFromDb.get().getGenre()));
        } else {
            genreService.save(newGenre);
            book.setGenre(genreService.findByGenre(newGenre.getGenre()));
        }
    }
}

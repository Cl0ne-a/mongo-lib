package com.nosql.mongolib.listeners;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.repository.GenreRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoGenreBookSaveEventListenner extends AbstractMongoEventListener<Book> {

    private final GenreRepository genreRepository;

    @Autowired
    public MongoGenreBookSaveEventListenner(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        val book = event.getSource();
        if(book.getGenre()!=null
                && book.getGenre().getId()==null) {
            genreRepository.save(book.getGenre());
        }
    }
}

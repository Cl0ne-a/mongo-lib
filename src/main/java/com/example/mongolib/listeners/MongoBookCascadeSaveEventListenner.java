package com.example.mongolib.listeners;

import com.example.mongolib.model.Author;
import com.example.mongolib.model.Book;
import com.example.mongolib.repository.AuthorRepository;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoBookCascadeSaveEventListenner extends AbstractMongoEventListener<Book> {

    private final AuthorRepository authorRepository;

    public MongoBookCascadeSaveEventListenner(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        val book = event.getSource();
        if(book.getAuthor()!=null
                && book.getAuthor().getId()==null) {
            authorRepository.save(book.getAuthor());
        }
    }
}

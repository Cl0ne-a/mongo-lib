package com.nosql.mongolib.listeners;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.repository.AuthorRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoAuthorBookSaveEventListenner extends AbstractMongoEventListener<Book> {

    private final AuthorRepository authorRepository;

    @Autowired
    public MongoAuthorBookSaveEventListenner(AuthorRepository authorRepository) {
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

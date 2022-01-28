package com.nosql.mongolib.listeners;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.service.AuthorService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoAuthorBookSaveEventListenner extends AbstractMongoEventListener<Book> {

    private final AuthorService authorService;

    @Autowired
    public MongoAuthorBookSaveEventListenner(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        val book = event.getSource();
//        todo check authors in repo correctly
        Author newAuthor = book.getAuthor();
        if(!newAuthor.getName().equals(authorService.findByName(newAuthor.getName()).getName())) {
            authorService.saveAuthor(book.getAuthor());
        }
    }
}

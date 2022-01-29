package com.nosql.mongolib.listeners;

import com.mongodb.lang.Nullable;
import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.service.AuthorService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Allows adding new author to DB in case it does not exist.
 *  * This is an independent instance, so we are not removing it after the book is deleted.
 */
@Component
public class MongoAuthorBookSaveEventListenner extends AbstractMongoEventListener<Book> {

    private final AuthorService authorService;

    @Autowired
    public MongoAuthorBookSaveEventListenner(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void onBeforeConvert(@Nullable BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(Objects.requireNonNull(event));
        val book = event.getSource();
        Author newAuthor = book.getAuthor();
        val authorFromDb = authorService
                .findAllAuthors()
                .stream().filter(author
                        -> author.getName().equals(newAuthor.getName()))
                .findFirst();
        authorFromDb.ifPresent(author -> book.setAuthor(authorService.findByName(author.getName())));
        if (authorFromDb.isPresent()) {
            book.setAuthor(authorService.findByName(authorFromDb.get().getName()));
        } else {
            authorService.saveAuthor(newAuthor);
            book.setAuthor(authorService.findByName(newAuthor.getName()));
        }
    }
}

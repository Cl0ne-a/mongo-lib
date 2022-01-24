package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class BookServiceImpl implements BookService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Book> getAllByGenre(Genre genre) {

        return mongoTemplate.query(Book.class)
                .matching(Query.query(where("genre").is(genre)))
                .all();
    }

    @Override
    public List<Book> findAllBooks() {
        return mongoTemplate.findAll(Book.class);
    }

    @Override
    public Book findById(String id) {
        return mongoTemplate.findById(id, Book.class);
    }

    @Override
    public void deleteBook(Book book) {
        mongoTemplate.remove(book);
    }

    @Override
    public Book save(Book newBook) {
        return mongoTemplate.save(newBook);
    }

    @Override
    public List<Book> byTitleAndAuthor(String titleMatcher, Author author) {

        TypedAggregation<Book> agg =
                newAggregation(
                        Book.class,
                        match(Criteria.where("title").regex(titleMatcher)
                                .and("author").is(author)));
        var aggregationResult = mongoTemplate.aggregate(agg, Book.class);

        return aggregationResult.getMappedResults();
    }
}

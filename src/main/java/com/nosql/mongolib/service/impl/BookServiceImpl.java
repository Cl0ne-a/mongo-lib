package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final MongoTemplate mongoTemplate;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(MongoTemplate mongoTemplate, BookRepository bookRepository) {
        this.mongoTemplate = mongoTemplate;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllByGenre(Genre genre) {

//        return mongoTemplate.query(Book.class)
//                .matching(Query.query(where("genre").is(genre)))
//                .all();

        return bookRepository.findAllByGenre(genre);
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
        bookRepository.delete(book);
    }

    @Override
    public Book save(Book newBook) {
        return mongoTemplate.save(newBook);
    }

    @Override
    public List<Book> byTitleAndAuthor(String titleMatcher, Author author) {

//        todo: is that a nice way ?
//        TypedAggregation<Book> agg = aggregationHelper.getAggregation(titleMatcher, author);
//        var aggregationResult = mongoTemplate.aggregate(agg, Book.class);
//        TypedAggregation<Book> aggregation = newAggregation(
//                    Book.class,
//                    match(Criteria.where("title").regex(titleMatcher)
//                            .and("author").is(author)));
//        return aggregationResult.getMappedResults();

        return bookRepository.findAllByTitleLikeAndAuthor(titleMatcher, author);
    }


}

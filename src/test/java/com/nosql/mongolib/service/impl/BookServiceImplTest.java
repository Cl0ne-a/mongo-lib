package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.service.BookService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataMongoTest
class BookServiceImplTest extends MongoLibApplicationTest {

    private final MongoTemplate mongoTemplate;
    private final BookRepository bookRepository;
    private final BookService bookService;


    @Captor
    ArgumentCaptor<Book> captor;

    @Autowired
    BookServiceImplTest() {
        this.bookRepository = mock(BookRepository.class);
        this.mongoTemplate = mock(MongoTemplate.class);
        this.bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void findById() {
        Author author = Author.builder().build();
        Genre genre = Genre.builder().build();

        Book book = Book.builder().id("1").title("book").author(author).genre(genre).build();
        when(mongoTemplate.findById("1", Book.class)).thenReturn(book);

        assertThat(bookService.findById("1")).isEqualTo(book);
    }

    @Test
    void findAllBooks() {
        Author author = Author.builder().build();
        Genre genre = Genre.builder().build();

        Book book = Book.builder().id("1").title("book").author(author).genre(genre).build();
        List<Book> expected = List.of(book);
        when(mongoTemplate.findAll(Book.class)).thenReturn(expected);
        val actual = bookService.findAllBooks();

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void save() {
        Author author = Author.builder().build();
        Genre genre = Genre.builder().build();
        Book book = Book.builder().id("1").title("book").author(author).genre(genre).build();
        when(mongoTemplate.save(book)).thenReturn(book);

        assertThat(bookService.save(book)).usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    void byTitleAndAuthor() {
        Author author = Author.builder().id("1").name("A").build();
        Genre genre = Genre.builder().build();
        Book book = Book.builder().id("1").title("book").author(author).genre(genre).build();
        String titleMatcher = "a";
        List<Book> expected = List.of(book);

        when(bookRepository.findAllByTitleLikeAndAuthor(titleMatcher, author)).thenReturn(expected);
        val actual = bookService.byTitleAndAuthor(titleMatcher, author);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void deleteBook() {
        Author author = Author.builder().id("1").name("A").build();
        Genre genre = Genre.builder().id("1").genre("G").build();
        Book book = Book.builder().id("1").title("book").author(author).genre(genre).build();

        bookService.deleteBook(book);
        Mockito.verify(bookRepository).delete(captor.capture());

        Book actual = captor.getValue();
        Assertions.assertEquals(actual, book);
    }

    @Test
    void getAllByGenre() {
        Author author = Author.builder().id("1").name("A").build();
        Genre genre = Genre.builder().id("1").genre("G").build();
        Book book = Book.builder().id("1").title("book").author(author).genre(genre).build();
        List<Book> expected = List.of(book);
        when(bookRepository.findAllByGenre(genre)).thenReturn(expected);

        val actual = bookService.getAllByGenre(genre);
        assertThat(actual).isEqualTo(expected);
    }

}
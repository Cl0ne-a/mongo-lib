package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.repository.AuthorRepository;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.service.AuthorService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorServiceImplTest {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private AuthorService authorService;

    @Autowired
    public AuthorServiceImplTest() {
        bookRepository = mock(BookRepository.class);
        authorRepository = mock(AuthorRepository.class);
        authorService = new AuthorServiceImpl(authorRepository, bookRepository);
    }

    @Test
    void updateAuthor() {
        String oldName = "oldName";
        String newName = "newName";
        Author author1 = Author.builder()
                .id("id")
                .name(oldName)
                .build();
        Author author2 = Author.builder()
                .id("id")
                .name(newName)
                .build();
        Book book1 = Book.builder()
                .id("id")
                .title("title")
                .author(author1)
                .build();
        Book book2 = Book.builder()
                .id("id")
                .title("title")
                .author(author2)
                .build();
        List<Book> books1 = List.of(book1);

        when(authorRepository.findByName(oldName)).thenReturn(author1);
        when(authorRepository.findByName(newName)).thenReturn(author2);
        when(bookRepository.findByAuthor(author1)).thenReturn(books1);
        when(authorRepository.save(author2)).thenReturn(author2);
        when(bookRepository.save(book2)).thenReturn(book2);

        val actualAuthor = authorService.updateAuthor(oldName, newName);
        Assertions.assertEquals(actualAuthor.getName(), newName);
        bookRepository.findAll().forEach(book
                -> Assertions.assertTrue(
                        book.getAuthor().getName().equals(newName)));
    }
}
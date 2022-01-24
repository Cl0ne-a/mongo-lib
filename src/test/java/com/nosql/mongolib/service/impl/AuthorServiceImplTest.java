package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.repository.AuthorRepository;
import com.nosql.mongolib.service.AuthorService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataMongoTest
class AuthorServiceImplTest extends MongoLibApplicationTest{

    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Autowired
    AuthorServiceImplTest(AuthorRepository authorRepository) {
        this.authorRepository = mock(AuthorRepository.class);
        this.authorService = new AuthorServiceImpl(authorRepository);
    }

    @Test
    void findAllAuthors() {
        Author author = Author.builder().build();
        List<Author> expectedAuthorList = List.of(author);
        Mockito.when(authorRepository.findAll()).thenReturn(expectedAuthorList);

        val actual = authorService.findAllAuthors();
        assertEquals(expectedAuthorList, actual);
    }

    @Test
    void findById() {
        String id  = "1";
        String name = "N";
        Author author = Author.builder().id(id).name(name).build();
        when(authorRepository.findById(id)).thenReturn(Optional.ofNullable(author));
        val actual = authorService.findById(id);
        assertThat(actual).isEqualTo(author);
    }
}
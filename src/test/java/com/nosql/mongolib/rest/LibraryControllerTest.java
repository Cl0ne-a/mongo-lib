package com.nosql.mongolib.rest;

import com.nosql.mongolib.rest.domain.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveNewBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        BookDto book1 = BookDto.builder().title("Grokking").author("Aditya").genre("science").comments(Collections.emptyList()).build();
        HttpEntity<BookDto> httpEntity = new HttpEntity<>(book1, headers);
        assertThat(restTemplate
                .postForObject("http://localhost:" + port +"/api/newbook", httpEntity, BookDto.class))
                .usingRecursiveComparison()
                .comparingOnlyFields("title", "genre", "author", "comments").isEqualTo(book1);
    }

    @Test
    public void getBookById() {
        BookDto book1 = BookDto.builder().id("1").title("Grokking Algorithms").author("Aditya Bhargava").genre("science").comments(Collections.emptyList()).build();

        assertThat(restTemplate
                .getForObject("http://localhost:" + port +"/api/books/1", String.class))
                .contains(book1.toString());
    }

    @Test
    public void getAllBooks() {
        BookDto book1 = BookDto.builder().id("1").title("Grokking Algorithms").author("Aditya Bhargava").genre("science").comments(Collections.emptyList()).build();
        BookDto book2 = BookDto.builder().id("2").title("What If?").author("Randall Munroe").genre("comedy").comments(List.of("\"this book was hilarious\"")).build();
        List<BookDto> list = List.of(book1, book2);

        assertThat(restTemplate
                .getForObject("http://localhost:" + port +"/api/books", String.class))
                .contains("[{\"id\":\"1\",\"title\":\"Grokking Algorithms\",\"author\":\"Aditya Bhargava\",\"genre\":\"science\",\"comments\":[]},{\"id\":\"2\",\"title\":\"What If?\",\"author\":\"Randall Munroe\",\"genre\":\"comedy\",\"comments\":[\"this book was hilarious\"]}]");
    }
}
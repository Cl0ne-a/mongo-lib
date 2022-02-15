package com.nosql.mongolib.rest;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
@RestController
public class LibraryController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    private ResponseEntity<List<Book>> findAll() {
        List<Book>books = bookService.findAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/books/{id}")
    private ResponseEntity<Book> findById(@PathVariable("id") String id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/book")
    private ResponseEntity<Book> create(@RequestBody Book book) {
        Book created = bookService.saveNewBook(book);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(loc).body(created);
    }

    @PutMapping("/books/{id}/{title}")
    private ResponseEntity<Book> update(
            @PathVariable("id") String id,
            @PathVariable("title") String newTitle) {
        Book updated = bookService.update(id, newTitle);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/books/{id}")
    private ResponseEntity<Book> delete(@PathVariable("id") String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

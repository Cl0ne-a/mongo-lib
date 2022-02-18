package com.nosql.mongolib.rest;

import com.nosql.mongolib.rest.domain.BookDto;
import com.nosql.mongolib.rest.domain.GenreDto;
import com.nosql.mongolib.service.BookService;
import com.nosql.mongolib.service.GenreService;
import lombok.val;
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
    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    private ResponseEntity<List<GenreDto>> findAllGenres() {
        val genres = genreService.findAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @GetMapping("/books")
    private ResponseEntity<List<BookDto>> findAll() {
        val books = bookService.findAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/books/{id}")
    private ResponseEntity<BookDto> findById(@PathVariable("id") String id) {
        val book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/newbook")
    private ResponseEntity<BookDto> create(@RequestBody BookDto book) {
        val created = bookService.saveNewBook(book);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(loc).body(created);
    }

    @PutMapping("/books/{id}/{title}")
    private ResponseEntity<BookDto> update(
            @PathVariable("id") String id,
            @PathVariable("title") String newTitle) {
        val updated = bookService.updateBook(id, newTitle);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/books/{id}")
    private ResponseEntity<Void> delete(@PathVariable("id") String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

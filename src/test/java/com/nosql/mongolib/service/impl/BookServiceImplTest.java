package com.nosql.mongolib.service.impl;

import com.nosql.mongolib.domain.BookDto;
import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.repository.AuthorRepository;
import com.nosql.mongolib.repository.BookRepository;
import com.nosql.mongolib.repository.GenreRepository;
import com.nosql.mongolib.service.BookService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class BookServiceImplTest {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookService bookService;

    @Autowired
    BookServiceImplTest() {
        bookRepository = mock(BookRepository.class);
        authorRepository = mock(AuthorRepository.class);
        genreRepository = mock(GenreRepository.class);
        this.bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository);
    }

    @Test
    void saveAll() {
        List<Book> mockListExpected = List.of(
                Book.builder()
                        .id("1")
                        .title("title")
                        .author(Author.builder().id("1").build())
                        .genre(Genre.builder().id("1").build())
                        .build()
        );

        Mockito.when(bookRepository.saveAll(mockListExpected)).thenReturn(mockListExpected);
        val actual = bookService.saveAll(mockListExpected);

        verify(bookRepository).saveAll(mockListExpected);
        assertEquals(mockListExpected, actual);
    }

//    Book findById(String id);
    @Test
    void findById() {
        String id = "id";
        Optional<Book> book = Optional.ofNullable(Book.builder()
                .id("1")
                .title("title")
                .author(Author.builder().id("1").build())
                .genre(Genre.builder().id("1").build())
                .build());
        when(bookRepository.findById(id)).thenReturn(book);
        BookDto expected = BookDto.bookToDto(book.get());

        val actual = bookService.findById(id);
        assertEquals(expected, actual);
    }

//    void deleteBook(Book book);
    @Test
    void deleteBook() {
        String id = anyString();
        Optional<Book> expected = Optional.ofNullable(Book.builder()
                .id(id)
                .title("title")
                .author(Author.builder().id("1").build())
                .genre(Genre.builder().id("1").build())
                .build());

//        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        when(bookRepository.findById(id)).thenReturn(expected);
        doNothing().when(bookRepository).deleteById(id);
        bookService.deleteBook(id);

        verify(bookRepository, times(1)).deleteById(id);
    }

//    Book saveNewBook(Book newBook);
    @Test
    void saveNewBook() {
        String id = anyString();
        String title = "title";

        Book expected = Book.builder()
                .id(id)
                .title(title)
                .author(Author.builder().id("1").name("authorName").build())
                .genre(Genre.builder().id("1").genre("genreName").build())
                .build();

        when(genreRepository.findByGenre("genreName")).thenReturn(Genre.builder().id("1").genre("genreName").build());
        when(authorRepository.findByName("authorName")).thenReturn(Author.builder().id("1").name("authorName").build());
        when(bookRepository.findByTitleAndGenreAndAuthor(
                title,
                Genre.builder().id("1").genre("genreName").build(),
                Author.builder().id("1").name("authorName").build()))
                .thenReturn(null);

        when(bookRepository.save(expected)).thenReturn(expected);

        val actual = bookService.saveNewBook(BookDto.bookToDto(expected));
        Assertions.assertEquals(BookDto.bookToDto(expected), actual);
    }

//    List<Book> findAllBooks();
    @Test
    void findAllBooks() {
        val bookDto = BookDto.bookToDto(Book.builder()
                .title("title")
                .author(Author.builder().id("1").name("author").build())
                .genre(Genre.builder().id("1").genre("genre").build())
                .build());
        List<BookDto> mockListExpected = List.of(bookDto);
        when(bookRepository.findAll())
                .thenReturn(mockListExpected.stream()
                        .map(BookDto::toDomainObject)
                        .collect(Collectors.toList()));

        val actual = bookService.findAllBooks();
        Assertions.assertEquals(mockListExpected, actual);
    }

//    List<Book> byTitleAndAuthor(String titleMatcher, Author author);
    @Test
    void byTitleAndAuthor() {
        String id = "id";
        String title = "title";
        Author author = Author.builder().id(id).build();

        Book expected = Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .genre(Genre.builder().id("1").build())
                .build();

        String titleMatcher = "it";

        when(bookRepository.findAllByTitleLikeAndAuthor(titleMatcher, author))
                .thenReturn(List.of(expected));
        val actual = bookService.byTitleAndAuthor(titleMatcher, author);

        assertEquals(List.of(expected), actual);
    }

//    List<Book> getAllByGenre(Genre genre);
    @Test
    void getAllByGenre() {
        String id = "id";
        String title = "title";
        Genre genre = Genre.builder().id("1").build();
        Book book = Book.builder()
                .id(id)
                .title(title)
                .author(Author.builder().id(id).build())
                .genre(genre)
                .build();
        List<Book> expected = List.of(book);

        when(bookRepository.findAllByGenre(genre)).thenReturn(expected);

        val actual = bookService.getAllByGenre(genre);
        assertEquals(expected, actual);
    }

//    void deleteAll();
    @Test
    void deleteAll() {
        doNothing().when(bookRepository).deleteAll();
        bookService.deleteAll();
        verify(bookRepository, times(1)).deleteAll();
    }
}
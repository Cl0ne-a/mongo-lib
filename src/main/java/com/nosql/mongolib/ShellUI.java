package com.nosql.mongolib;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Comment;
import com.nosql.mongolib.model.Genre;
import com.nosql.mongolib.service.AuthorService;
import com.nosql.mongolib.service.BookService;
import com.nosql.mongolib.service.CommentService;
import com.nosql.mongolib.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class ShellUI {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CommentService commentService;
    private final GenreService genreService;

    @Autowired
    public ShellUI(BookService bookService, AuthorService authorService, CommentService commentService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.commentService = commentService;
        this.genreService = genreService;
    }

    @ShellMethod(key = "books", value = "list books")
    List<Book> listBooks() {
        return bookService.findAllBooks();
    }

    @ShellMethod(key = "authors", value = "list authors")
    List<Author> listAuthors() {
        return authorService.findAllAuthors();
    }

    @ShellMethod(key = "genres", value = "list genres")
    List<Genre> listAllGenres() {
        return genreService.listAllGenres();
    }

    @ShellMethod(key = "comments-by-book-id", value = "load comments by book id")
    List<Comment> listComments(String id) {
        Book book = bookService.findById(id);
        return commentService.findCommentsByBook(book);
    }

    @ShellMethod(key = "give-feedback", value = "allows adding comment to the book")
    Comment addComment(String bookId, String commentLine) {
        Book book = bookService.findById(bookId);
        return commentService.addComment(book, commentLine);
    }

    @ShellMethod(key = "book", value = "get book from db by id")
    Book findById(String id) {
        return bookService.findById(id);
    }

    @ShellMethod(key = "del-book", value = "delete book from list")
    void deleteBook(String bookId) {
        Book book = bookService.findById(bookId);
        bookService.deleteBook(book);
    }

    @ShellMethod(key = "add", value = "Save book to DB")
    Book save(String bookName, String authorName, String genreName) {
        Author author = Author.builder().name(authorName).build();
        Genre genre = Genre.builder().genre(genreName).build();
        Book newBook = Book.builder()
                .title(bookName)
                .author(author)
                .genre(genre)
                .build();
        return bookService.save(newBook);
    }

    @ShellMethod(key = "book-by-name", value = "of all existing books retrieve those of certain author and match naming criteria ")
    List<Book> byTitleAndAuthor(String titleMatcher, String authorId) {
        Author author = authorService.findById(authorId);
        return bookService.byTitleAndAuthor(titleMatcher, author);
    }
    
    @ShellMethod(key = "book-by-genre", value = "finds books by certain genre")
    List<Book> getAllByGenre(String genreId) {
        Genre genre = genreService.findById(genreId);
        return bookService.getAllByGenre(genre);
    }
}

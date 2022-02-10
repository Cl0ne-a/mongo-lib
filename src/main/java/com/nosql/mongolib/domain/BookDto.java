package com.nosql.mongolib.domain;

import com.nosql.mongolib.model.Author;
import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class BookDto {
    String id;
    String title;
    String author;
    String genre;
    List<String> comments;

    public static BookDto bookToDto(Book book) {
        List<String> thisCommentsList = new ArrayList<>();
        if(book.getComments()!=null) {
            book.getComments()
                    .stream()
                    .forEach(comment -> thisCommentsList.add(comment.getCommentLine()));
        }
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getGenre().getGenre(),
                thisCommentsList);
    }

    public static Book toDomainObject(BookDto newBook) {
        Author author = Author.builder().name(newBook.getAuthor()).build();
        Genre genre = Genre.builder().genre(newBook.getGenre()).build();
        return Book.builder()
                .title(newBook.getTitle())
                .author(author)
                .genre(genre)
                .build();
    }
}

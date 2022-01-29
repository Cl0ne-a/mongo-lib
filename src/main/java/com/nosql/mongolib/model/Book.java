package com.nosql.mongolib.model;

import com.github.cloudyrock.mongock.utils.field.Field;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
@Data
@Builder
@Document
public class Book {
    @MongoId
    private String id;

    @Field(value = "title")
    private String title;

    @Field(value = "author")
    private Author author;

    @Field(value = "genre")
    private Genre genre;

    @Field("comments")
    private List<Comment> comments;

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", comments=" + comments +
                '}';
    }
}

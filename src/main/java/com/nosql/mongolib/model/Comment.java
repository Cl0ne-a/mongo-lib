package com.nosql.mongolib.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document
public class Comment {
    @NotNull
    @Id
    private String id;
    @NotBlank
    @NotNull
    private String commentLine;

    @NotBlank
    @NotNull
    private Book book;

    public Comment(Book book, String commentLine) {
        this.book = book;
        this.commentLine = commentLine;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentLine='" + commentLine + '\'' +
                ", book=" + book +
                '}';
    }
}

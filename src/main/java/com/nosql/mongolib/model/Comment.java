package com.nosql.mongolib.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {
    @Id
    private String id;

    private String commentLine;

    private Book book;

    public Comment(Book book, String commentLine) {
        this.book = book;
        this.commentLine = commentLine;
    }

    public String getCommentLine() {
        return commentLine;
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

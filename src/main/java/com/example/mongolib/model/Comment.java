package com.example.mongolib.model;

import com.example.mongolib.model.Book;
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
}

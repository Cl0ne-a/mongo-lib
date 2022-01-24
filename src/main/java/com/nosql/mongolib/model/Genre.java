package com.nosql.mongolib.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
@Builder
@Document
public class Genre {
    @NotNull
    @Id
    private String id;

    private String genre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id='" + id + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
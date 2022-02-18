package com.nosql.mongolib.rest.domain;

import com.nosql.mongolib.model.Book;
import com.nosql.mongolib.model.Genre;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class GenreDto {
    String id;
    String genre;

    public static GenreDto genreToDto(Genre genre) {

        return new GenreDto(
                genre.getId(),
                genre.getGenre());
    }
}

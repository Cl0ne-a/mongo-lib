package com.nosql.mongolib.model;

import com.github.cloudyrock.mongock.utils.field.Field;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document
public class Genre {
    @Id
    private String id;

    @Field(value = "genre")
    private String genre;

    @Override
    public String toString() {
        return "Genre{" +
                "id='" + id + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}

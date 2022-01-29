package com.nosql.mongolib.model;

import com.github.cloudyrock.mongock.utils.field.Field;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Author {
    @Id
    private String id;

    @Field(value = "name")
    private String name;

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

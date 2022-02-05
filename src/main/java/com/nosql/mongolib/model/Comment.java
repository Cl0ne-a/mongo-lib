package com.nosql.mongolib.model;

import com.github.cloudyrock.mongock.utils.field.Field;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document
public class Comment {
    @Id
    private String id;

    @Field(value = "commentLine")
    private String commentLine;

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentLine='" + commentLine + '\'' +
                '}';
    }
}

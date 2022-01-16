package com.example.mongolib;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Document
@Data
public class Book {
    @NotNull
    @Id
    private String id;
    @NotNull
    @NotBlank
    private String title;

}

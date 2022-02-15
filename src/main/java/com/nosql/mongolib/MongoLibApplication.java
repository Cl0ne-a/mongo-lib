package com.nosql.mongolib;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties
public class MongoLibApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoLibApplication.class, args);
    }
}

package com.nosql.mongolib;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
//@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200", "http://localhost:3000"})
@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties
public class MongoLibApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoLibApplication.class, args);
    }
}

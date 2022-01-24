package com.nosql.mongolib.service.impl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan("com.nosql.mongolib.repository")
public abstract class MongoLibApplicationTest {

}
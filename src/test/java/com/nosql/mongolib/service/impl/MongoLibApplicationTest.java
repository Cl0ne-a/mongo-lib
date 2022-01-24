package com.nosql.mongolib.service.impl;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("com.nosql.mongolib.repository")
public abstract class MongoLibApplicationTest {

}
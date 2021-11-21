package com.notion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@SpringBootApplication(exclude = {EmbeddedMongoAutoConfiguration.class, DataSourceAutoConfiguration.class, MongoAutoConfiguration.class, MongoAutoConfiguration.class})
@SpringBootApplication
@EnableMongoAuditing
public class NotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotionApplication.class, args);
	}

}

package com.notion.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("user")
public class User {

    private String id;
    private String name;
    private List<Page> pages;

}

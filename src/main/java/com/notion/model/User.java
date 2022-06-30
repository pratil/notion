package com.notion.model;

import com.notion.AOP.Email;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@Document("user")
public class User implements Serializable {

    private static final long serialVersionUID = -2676231597475865950L;

    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("middle_name")
    private String middleName;

    @Field("last_name")
    private String lastName;

    @Field("preferred_name")
    private String preferredName;

    @Indexed(unique = true)
    @Email
    private String email;

    private String password;

    @Field("active")
    private Boolean isActive;

    @Field("created_at")
    private Long createdAt;

    @Field("updated_at")
    private Long updatedAt;

    public User(String firstName, String middleName, String lastName, String preferredName, String email, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.preferredName = preferredName;
        this.email = email;
        this.password = password;
        this.isActive = true;
    }
}

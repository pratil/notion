package com.notion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("pages")
public class Page implements Serializable {

    private static final long serialVersionUID = 4676062777493954650L;

    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    @Indexed
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotNull
    @Field("favourite")
    private Boolean isFavourite;

    @Field("parent_id")
    private String parentPageId;

    @Field("created_at")
    private Long createdAt;

    @Field("updated_at")
    private Long updatedAt;

    public Page(String title, Boolean isFavourite, String parentPageId) {
        this.title = title;
        this.isFavourite = isFavourite;
        this.parentPageId = parentPageId;
    }

}

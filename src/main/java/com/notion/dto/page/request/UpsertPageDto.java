package com.notion.dto.page.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notion.model.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpsertPageDto {

    private String title;

    @JsonProperty("favourite")
    private Boolean isFavourite;

    @JsonProperty("parent_id")
    private String parentId;

    public Page getPage() {
        return new Page(title, isFavourite, parentId);
    }

}

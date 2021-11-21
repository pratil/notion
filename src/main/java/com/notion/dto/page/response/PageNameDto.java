package com.notion.dto.page.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notion.model.Page;
import lombok.Data;

@Data
public class PageNameDto {

    private String id;

    private String title;

    private Boolean isFavourite;

    public PageNameDto(Page page) {
        this.id = page.getId();
        this.title = page.getTitle();
        this.isFavourite = page.getIsFavourite();
    }

}
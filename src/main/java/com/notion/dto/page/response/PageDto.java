package com.notion.dto.page.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notion.dto.block.response.BlockDto;
import com.notion.model.Page;
import lombok.Data;

import java.util.Date;
import java.util.List;

import static com.notion.constants.Constants.DEFAULT_DATE_FORMAT;

@Data
public class PageDto {

    private String id;

    private String title;

    @JsonProperty("favourite")
    private Boolean isFavourite;

    @JsonProperty("children")
    private List<PageNameDto> nestedPages;

    private List<BlockDto> blocks;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public PageDto(Page page) {
        this(page, null, null);
    }

    public PageDto(Page page, List<PageNameDto> nestedPages, List<BlockDto> blocks) {
        this.id = page.getId();
        this.title = page.getTitle();
        this.isFavourite = page.getIsFavourite();
        this.nestedPages = nestedPages;
        this.createdAt = DEFAULT_DATE_FORMAT.format(new Date(page.getCreatedAt()));
        this.updatedAt = DEFAULT_DATE_FORMAT.format(new Date(page.getUpdatedAt()));
        this.blocks = blocks;
    }
}

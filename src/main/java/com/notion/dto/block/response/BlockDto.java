package com.notion.dto.block.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notion.model.block.Block;
import lombok.Data;

import java.util.Date;

import static com.notion.constants.Constants.DEFAULT_DATE_FORMAT;

@Data
public class BlockDto {

    private String id;

    private Long order;

    private String value;

    private String type;

    @JsonProperty("sub_type")
    private Object subType;

    @JsonProperty("page_id")
    private String pageId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public BlockDto(Block block) {
        id = block.getId();
        order = block.getOrder();
        value = block.getValue();
        type = block.getType().getValue();
        subType = block.getSubType().getValue();
        pageId = block.getPageId();
        this.createdAt = DEFAULT_DATE_FORMAT.format(new Date(block.getCreatedAt()));
        this.updatedAt = DEFAULT_DATE_FORMAT.format(new Date(block.getUpdatedAt()));

    }
}

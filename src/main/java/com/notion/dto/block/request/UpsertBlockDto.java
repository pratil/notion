package com.notion.dto.block.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notion.model.block.Block;
import lombok.Data;

@Data
public class UpsertBlockDto {

    private String id;

    private Long order;

    private String value;

    private String type;

    @JsonProperty("sub_type")
    private Object subType;

    @JsonProperty("page_id")
    private String pageId;

    public Block getBlock() {
        return new Block(order, value, type, subType, pageId);
    }

}

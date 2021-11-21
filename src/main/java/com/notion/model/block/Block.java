package com.notion.model.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("block")
public class Block implements Serializable {

    private static final long serialVersionUID = -3588470413069859759L;

    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    private Long order;

    private String value;

    private BlockType type;

    @Field("sub_type")
    private BlockSubType subType;

    @Field("page_id")
    private String pageId;

    @Field("created_at")
    private Long createdAt;

    @Field("updated_at")
    private Long updatedAt;

    public Block(Long order, String value, String type, Object subType, String pageId) {
        this.order = order;
        this.value = value;
        this.type = BlockType.of(type);
        if(this.type == BlockType.TEXT && subType == null)
            this.subType = BlockSubType.TEXT;
        else
            this.subType = BlockSubType.of(subType);
        this.pageId = pageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block block = (Block) o;
        return Objects.equals(order, block.order) && value.equals(block.value) && type == block.type && subType == block.subType && pageId.equals(block.pageId);
    }

}

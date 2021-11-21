package com.notion.model.block;

import com.notion.exceptions.block.InvalidBlockException;
import lombok.AllArgsConstructor;

import static com.notion.model.block.BlockType.*;
import static com.notion.constants.Constants.BlockException.INVALID_BLOCK_SUB_TYPE;

@AllArgsConstructor
public enum BlockSubType {

    H1(HEADING),
    H2(HEADING),
    H3(HEADING),

    DISC(UNORDERED_LIST, "Disc"),
    CIRCLE(UNORDERED_LIST, "Circle"),
    SQUARE(UNORDERED_LIST, "Square"),

    NUMBER(ORDERED_LIST, "Numbers"),
    LETTER(ORDERED_LIST, "Letters"),
    ROMAN_NUMBER(ORDERED_LIST, "Roman Numbers"),

    TEXT(BlockType.TEXT, "Plain Text"),

    LINK(BlockType.LINK, "Link"),
    BOOKMARK(BlockType.LINK, "Bookmark"),
    EMBEDDED(BlockType.LINK, "Embedded"),

    CHECKED(CHECKLIST, true),
    UNCHECKED(CHECKLIST, false);

    private final BlockType parent;
    private final Object value;

    BlockSubType(BlockType parent) {
        this.parent = parent;
        this.value = this.toString();
    }

    public Object getValue(){
        return value;
    }

    public static BlockSubType of(Object object) {
        if(object instanceof Boolean)
            return (Boolean) object ? CHECKED : UNCHECKED;
        if(object instanceof String)
            return BlockSubType.valueOf(((String) object).toUpperCase());
        throw new InvalidBlockException(String.format(INVALID_BLOCK_SUB_TYPE, object));
    }

}

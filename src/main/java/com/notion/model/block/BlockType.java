package com.notion.model.block;

import com.notion.exceptions.block.InvalidBlockException;

import static com.notion.constants.Constants.BlockException.INVALID_BLOCK_SUB_TYPE;

public enum BlockType {
    TEXT("Text"),
    HEADING("Heading"),
    CHECKLIST("Check List"),
    UNORDERED_LIST("Unordered List"),
    ORDERED_LIST("Ordered List"),
    LINK("Link");

    private final String value;

    BlockType(String value) {
        this.value = value;
    }

    public static BlockType of(Object object) {
        if(object instanceof String)
            return BlockType.valueOf(((String) object).toUpperCase());
        throw new InvalidBlockException(String.format(INVALID_BLOCK_SUB_TYPE, object));
    }

    public String getValue() {
        return value;
    }
}

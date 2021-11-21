package com.notion.exceptions.block;

import com.notion.exceptions.base.EntityNotFound;

public class BlockNotFound extends EntityNotFound {

    private static final String DEFAULT_MESSAGE = "Block Not found";

    public BlockNotFound() {
        super(DEFAULT_MESSAGE);
    }

    public BlockNotFound(String message) {
        super(message);
    }

    public BlockNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

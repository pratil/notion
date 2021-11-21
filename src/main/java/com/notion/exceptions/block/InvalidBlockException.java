package com.notion.exceptions.block;

import com.notion.exceptions.base.InvalidDataException;

public class InvalidBlockException extends InvalidDataException {

    private static final String DEFAULT_MESSAGE = "Invalid Block Error";

    public InvalidBlockException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidBlockException(String message) {
        super(message);
    }

    public InvalidBlockException(String message, Throwable cause) {
        super(message, cause);
    }

}

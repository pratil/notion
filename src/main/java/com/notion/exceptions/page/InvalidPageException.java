package com.notion.exceptions.page;

import com.notion.exceptions.base.InvalidDataException;

public class InvalidPageException extends InvalidDataException {

    private static final String DEFAULT_MESSAGE = "Invalid Page Error";

    public InvalidPageException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidPageException(String message) {
        super(message);
    }

    public InvalidPageException(String message, Throwable cause) {
        super(message, cause);
    }
}

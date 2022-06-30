package com.notion.exceptions.user;

import com.notion.exceptions.base.InvalidDataException;

public class InvalidUserException extends InvalidDataException {

    private static final String DEFAULT_MESSAGE = "Invalid User Error";

    public InvalidUserException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}


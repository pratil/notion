package com.notion.exceptions.user;

import com.notion.exceptions.base.EntityNotFound;

public class UserNotFoundException extends EntityNotFound {

    private static final String DEFAULT_MESSAGE = "User Not found";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


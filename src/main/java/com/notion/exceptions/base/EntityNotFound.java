package com.notion.exceptions.base;

import org.springframework.http.HttpStatus;

public class EntityNotFound extends BaseException {

    private static final HttpStatus HTTP_NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;

    public EntityNotFound(String message) {
        super(HTTP_NOT_FOUND_STATUS, message);
    }

    public EntityNotFound(String message, Throwable cause) {
        super(HTTP_NOT_FOUND_STATUS, message, cause);
    }
}

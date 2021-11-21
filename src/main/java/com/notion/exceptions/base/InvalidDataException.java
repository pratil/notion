package com.notion.exceptions.base;

import com.notion.exceptions.base.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidDataException extends BaseException {

    private static final HttpStatus HTTP_BAD_REQUEST_STATUS = HttpStatus.NOT_FOUND;

    public InvalidDataException(String message) {
        super(HTTP_BAD_REQUEST_STATUS, message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(HTTP_BAD_REQUEST_STATUS, message, cause);
    }
}

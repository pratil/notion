package com.notion.exceptions.page;

import com.notion.exceptions.base.EntityNotFound;

public class PageNotFound extends EntityNotFound {

    private static final String DEFAULT_MESSAGE = "Page Not found";

    public PageNotFound() {
        super(DEFAULT_MESSAGE);
    }

    public PageNotFound(String message) {
        super(message);
    }

    public PageNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.notion.exceptions.base;

import lombok.Data;

import java.util.Date;

import static com.notion.constants.Constants.DEFAULT_DATE_FORMAT;

@Data
public class ExceptionResponse {

    private Integer status;
    private String error;
    private String message;
    private String timestamp;

    public ExceptionResponse(BaseException baseException) {
        this.status = baseException.getHttpStatus().value();
        this.error = baseException.getHttpStatus().getReasonPhrase();
        this.message = baseException.getMessage();
        this.timestamp = DEFAULT_DATE_FORMAT.format(new Date());
    }
}

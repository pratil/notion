package com.notion.service;

import com.notion.dto.BaseResponse;
import com.notion.exceptions.base.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public final BaseResponse<Void> handleException(BaseException exception, WebRequest request) {
        return new BaseResponse<>(exception.getHttpStatus(), exception.getMessage(), false, null, null);
    }

}

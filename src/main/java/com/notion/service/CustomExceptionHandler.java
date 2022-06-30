package com.notion.service;

import com.notion.dto.BaseResponse;
import com.notion.exceptions.base.BaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.notion.constants.Constants.*;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public final BaseResponse<Void> handleException(BaseException exception, WebRequest request) {
        return new BaseResponse<>(exception.getHttpStatus(), exception.getMessage(), false, null, null);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<Map<String, Object>> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Map<String, Object> rejectionMap = new HashMap<>();
            rejectionMap.put(REJECTION_KEY, fieldError.getField());
            rejectionMap.put(REJECTED_VALUE, fieldError.getRejectedValue());
            rejectionMap.put(REJECTION_REASON, fieldError.getDefaultMessage());
            errors.add(rejectionMap);
        }
//        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
//            error.put(objectError.getObjectName(), objectError.getDefaultMessage());
//        }
        BaseResponse<Void> baseResponse = new BaseResponse<>(HttpStatus.BAD_REQUEST, "", false, null, errors);

        return ResponseEntity.status(baseResponse.getStatus()).body(baseResponse);
    }


}

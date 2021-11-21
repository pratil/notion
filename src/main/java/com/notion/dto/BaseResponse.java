package com.notion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import static com.notion.constants.Constants.DEFAULT_SUCCESS_MESSAGE;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<Body> {

    private final int status;
    @JsonProperty("http_message")
    private final String httpMessage;
    private final Boolean success;
    private final String message;
    private final Body body;
    private final Object error;

    public BaseResponse(HttpStatus httpStatus, String message, Boolean success, Body body, Object error) {
        this.status = httpStatus.value();
        this.httpMessage = httpStatus.getReasonPhrase();
        this.message = message;
        this.success = httpStatus.is2xxSuccessful();
        this.body = body;
        this.error = error;
    }

    public static <Body> BaseResponse<Body> ok() {
        return ok(null, null);
    }

    public static <Body> BaseResponse<Body> ok(Body data) {
        return ok(data, null);
    }

    public static <Body> BaseResponse<Body> ok(Body data, Object error) {
        return new BaseResponse<>(HttpStatus.OK, DEFAULT_SUCCESS_MESSAGE, true, data, error);
    }

    public static <Body> BaseResponse<Body> created() {
        return created(null, null);
    }

    public static <Body> BaseResponse<Body> created(Body data) {
        return created(data, null);
    }

    public static <Body> BaseResponse<Body> created(Body data, Object error) {
        return new BaseResponse<>(HttpStatus.CREATED, DEFAULT_SUCCESS_MESSAGE, true, data, error);
    }

}

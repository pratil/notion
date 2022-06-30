package com.notion.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notion.AOP.Email;
import com.notion.model.User;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

import static com.notion.constants.Constants.Regex.PASSWORD_REGEX;
import static com.notion.constants.Constants.UserException.INVALID_PASSWORD_EXCEPTION;

@Data
@Validated
public class UpsertUserDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("preferred_name")
    private String preferredName;

    @Email
    private String email;

    @Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD_EXCEPTION)
    private String password;

    public User getUser() {
        return new User(firstName, middleName, lastName, preferredName, email, password);
    }

}

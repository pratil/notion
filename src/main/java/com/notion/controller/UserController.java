package com.notion.controller;

import com.notion.dto.BaseResponse;
import com.notion.dto.user.request.UpsertUserDto;
import com.notion.model.User;
import com.notion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.notion.constants.Constants.Path.ID;
import static com.notion.constants.Constants.Path.USER_ENDPOINT;

@RestController
@RequestMapping(USER_ENDPOINT)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = ID)
    public BaseResponse<User> getUser(@PathVariable String id) {
        return BaseResponse.ok(userService.getUserDtoById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse<User> saveUser(@Validated @RequestBody UpsertUserDto upsertUserDto) {
        return BaseResponse.ok(userService.saveUser(upsertUserDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = ID)
    public BaseResponse<User> updateUser(@PathVariable String id, @RequestBody UpsertUserDto upsertUserDto) {
        return BaseResponse.ok(userService.updateUser(id, upsertUserDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = ID)
    public BaseResponse<User> updateUser(@PathVariable String id) {
        return BaseResponse.ok(userService.deleteUser(id));
    }

}

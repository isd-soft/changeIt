package com.internship.changeit.controller;


import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.User;
import com.internship.changeit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody final User user) {
        if(userService.isEmailUnique(user.getEmail())) {
            userService.saveOrUpdateUser(user);
            return user;
        } else throw new ApplicationException(ExceptionType.USER_ALREADY_EXIST);
    }
}

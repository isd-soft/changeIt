package com.internship.changeit.controller;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.User;
import com.internship.changeit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
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

    @GetMapping("/login")
    public String loginUser() {
        return "Succes Login";
    }
}

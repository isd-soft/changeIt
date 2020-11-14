package com.internship.changeit.controller;

import com.internship.changeit.dto.ProblemDto;
import com.internship.changeit.dto.UserDto;
import com.internship.changeit.mapper.UserMapper;
import com.internship.changeit.model.Problem;
import com.internship.changeit.model.Status;
import com.internship.changeit.model.User;
import com.internship.changeit.model.UserStatus;
import com.internship.changeit.service.ProblemService;
import com.internship.changeit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;
    private final ProblemService problemService;

    @PutMapping("/user/{id}")
    public UserDto changeUserStatus(@PathVariable final Long id, @RequestParam final UserStatus userStatus){
       User user =  userService.updateUserStatus(id, userStatus);

       return UserMapper.INSTANCE.toDto(user);
    }


}

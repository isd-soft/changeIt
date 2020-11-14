package com.internship.changeit.controller;


import com.internship.changeit.dto.UserDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.mapper.UserMapper;
import com.internship.changeit.model.User;
import com.internship.changeit.model.UserStatus;
import com.internship.changeit.model.VerificationToken;
import com.internship.changeit.repository.VerificationTokenRepo;
import com.internship.changeit.service.UserService;
import com.internship.changeit.service.impl.registrationService.OnRegistrationCompleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final VerificationTokenRepo verificationTokenRepo;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody final UserDto userDto, final HttpServletRequest request) {
        final User user = UserMapper.INSTANCE.fromDto(userDto);
        if(userService.isEmailUnique(user.getEmail())) {
            user.setUserStatus(UserStatus.INACTIVE);
            userService.registerNewUser(user);

            final String appUrl = "http://" +  request.getServerName() + request.getServerPort() + request.getContextPath();

            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appUrl, user));
            return user;
        } else throw new ApplicationException(ExceptionType.USER_ALREADY_EXIST);
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") final String token){
        final VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        final User user = verificationToken.getUser();

        user.setUserStatus(UserStatus.ACTIVE);
        userService.saveUser(user);

        return new ResponseEntity<>("You account is confirmed", HttpStatus.OK);
    }
}

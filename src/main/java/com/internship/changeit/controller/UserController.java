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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final VerificationTokenRepo verificationTokenRepo;
    private final ApplicationEventPublisher eventPublisher;
    private final JavaMailSender mailSender;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody final UserDto userDto, final HttpServletRequest request) {
        final User user = UserMapper.INSTANCE.fromDto(userDto);
        if (userService.isEmailUnique(user.getEmail())) {
            user.setUserStatus(UserStatus.INACTIVE);
            userService.registerNewUser(user);
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appUrl, user));
            return user;
        } else throw new ApplicationException(ExceptionType.USER_ALREADY_EXIST);
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") final String token) {
        final VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        final User user = verificationToken.getUser();
        user.setUserStatus(UserStatus.ACTIVE);
        userService.saveUser(user);
        return new ResponseEntity<>("You account is confirmed", HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam final String userEmail, final HttpServletRequest request) {
        final User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createVerificationToken(user, token);
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            final SimpleMailMessage email = userService.constructResetPasswordEmail(appUrl, token, user);
            mailSender.send(email);
            Map<Object, Object> response = new HashMap<>();
            response.put("Message", "You should receive an Password Reset Email shortly");
            return ResponseEntity.ok(response);
        } else throw new ApplicationException(ExceptionType.EMAIL_NOT_VALID);
    }


    @PostMapping("/savePassword")
    public ResponseEntity<?> resetPassword(@RequestParam("password") final String password,
                                           @RequestParam("token") final String token,
                                           @RequestParam("id") final long id,
                                           @RequestParam("passwordConfirmation") final String passwordConfirmation) {
        if (!password.equals(passwordConfirmation))
            throw new ApplicationException(ExceptionType.INVALID_ARGUMENTS);
        final VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        final User user = verificationToken.getUser();

        if(user == null || user.getUser_id() != id) {
            throw new ApplicationException(ExceptionType.USER_NOT_FOUND);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.saveUser(user);
        return ResponseEntity.ok("Your password has been changed successful");
    }
}

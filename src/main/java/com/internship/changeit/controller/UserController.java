package com.internship.changeit.controller;


import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.dto.ResetPasswordDetailsDTO;
import com.internship.changeit.dto.UserDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.mapper.CommentMapper;
import com.internship.changeit.mapper.UserMapper;
import com.internship.changeit.model.User;
import com.internship.changeit.model.UserStatus;
import com.internship.changeit.model.VerificationToken;
import com.internship.changeit.repository.VerificationTokenRepo;
import com.internship.changeit.service.UserService;
import com.internship.changeit.service.impl.CommentServiceImpl;
import com.internship.changeit.service.impl.registrationService.OnRegistrationCompleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final VerificationTokenRepo verificationTokenRepo;
    private final ApplicationEventPublisher eventPublisher;
    private final JavaMailSender mailSender;
    private final CommentServiceImpl commentService;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('DEVELOPER:READ')")
    public UserDto getUser(@RequestBody final UserDto userDto) {
        User user = UserMapper.INSTANCE.fromDto(userDto);
        return UserMapper.INSTANCE.toDto(userService.getUserByEmail(user.getEmail()));
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody final UserDto userDto, final HttpServletRequest request) {
        final User user = UserMapper.INSTANCE.fromDto(userDto);
        if (userService.isEmailUnique(user.getEmail())) {
            user.setUserStatus(UserStatus.INACTIVE);
            userService.registerNewUser(user);
            final String appUrl = "http://" + request.getServerName() + ":" + "4200" + request.getContextPath();
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

    @GetMapping("/verificationToken")
    public ResponseEntity<?> getVerificationToken(@RequestParam final String email) {
      final VerificationToken verificationToken = this.userService.getVerificationToken(email);
      Map<Object, Object> map = new HashMap<>();
      map.put("token", verificationToken.getToken());
      return ResponseEntity.ok(map);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam final String userEmail, final HttpServletRequest request) {
        final User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createVerificationToken(user, token);
            final String appUrl = "http://" + request.getServerName() + ":" + "4200" + request.getContextPath();
            final SimpleMailMessage email = userService.constructResetPasswordEmail(appUrl, token, user);
            mailSender.send(email);
            Map<Object, Object> response = new HashMap<>();
            response.put("Message", "You should receive an Password Reset Email shortly");
            return ResponseEntity.ok(response);
        } else throw new ApplicationException(ExceptionType.EMAIL_NOT_VALID);
    }


    @PostMapping("/savePassword")
    public ResponseEntity<?> resetPassword(@RequestBody final ResetPasswordDetailsDTO resetDetails) {
        if (!resetDetails.getPassword().equals(resetDetails.getPasswordConfirmation()))
            throw new ApplicationException(ExceptionType.INVALID_ARGUMENTS);
        final VerificationToken verificationToken = verificationTokenRepo.findByToken(resetDetails.getToken());
        final User user = verificationToken.getUser();

        if(user == null || user.getUser_id() != resetDetails.getId()) {
            throw new ApplicationException(ExceptionType.USER_NOT_FOUND);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(resetDetails.getPassword()));
        userService.saveUser(user);
        return ResponseEntity.ok("Your password has been changed successful");
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
       return userService.getAllUsers().stream()
               .map(UserMapper.INSTANCE::toDto)
               .collect(Collectors.toList());
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getCommentsByUser(@PathVariable Long id) {
        return commentService.getByUser(id)
                .stream()
                .map(CommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}

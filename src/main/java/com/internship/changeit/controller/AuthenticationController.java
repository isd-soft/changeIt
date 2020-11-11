package com.internship.changeit.controller;

import com.internship.changeit.security.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request){
        return null;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextHolder = new SecurityContextLogoutHandler();
        securityContextHolder.logout(request, response, null);
    }
}

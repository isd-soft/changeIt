package com.internship.changeit.controller;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.service.UserLogoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserLogoController {

    private final UserLogoService userLogoService;

    @PostMapping("/{userId}/user_logo")
    public ResponseEntity<?> uploadUserLogo(@PathVariable final String userId, @RequestParam("imageFile") final MultipartFile file){
        if(file.isEmpty()) {
            throw new ApplicationException(ExceptionType.FILE_NOT_FOUND);
        }

        userLogoService.saveUserLogo(Long.valueOf(userId), file);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("{userId}/user_logo")
    public void renderUserLogo(
            @PathVariable final String userId, final HttpServletResponse response) throws IOException {
        userLogoService.renderUserLogoFromDb(Long.valueOf(userId), response);
    }

    @DeleteMapping("{userId}/user_logo")
    public ResponseEntity<?> deleteUserLogo(@PathVariable final String userId){
        userLogoService.deleteUserLogo(Long.valueOf(userId));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

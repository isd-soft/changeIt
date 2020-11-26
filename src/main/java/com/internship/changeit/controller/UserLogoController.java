package com.internship.changeit.controller;

import com.internship.changeit.dto.ImageDto;
import com.internship.changeit.dto.UserDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Problem;
import com.internship.changeit.model.User;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.UserLogoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserLogoController {

    private final UserLogoService userLogoService;
    private final UserRepository userRepository;

    /*@PostMapping("/{userId}/user_logo")
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
    }*/

    @PostMapping("/{userId}/user_logo")
    public ResponseEntity<?> uploadUserLogo(
            @PathVariable final String userId, @RequestParam("imageFile") final MultipartFile file){
        final User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        userLogoService.uploadUserLogo(user, file);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}/user_logo")
    public UserDto getUserLogo(@PathVariable final String userId) throws IOException {
        return userLogoService.getUserLogo(Long.valueOf(userId));
    }

    @DeleteMapping("/{userId}/user_logo")
    public ResponseEntity<?> deleteUserLogo(@PathVariable final String userId){
        userLogoService.deleteUserLogo(Long.valueOf(userId));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}/user_logo")
    public ResponseEntity<?> updateUserLogo(@PathVariable final String userId, @RequestParam final MultipartFile file) {
        this.userLogoService.updateUserLogo(Long.valueOf(userId), file);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

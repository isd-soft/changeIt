package com.internship.changeit.controller;

import com.internship.changeit.dto.ImageDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Problem;
import com.internship.changeit.model.User;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.ImageService;
import com.internship.changeit.service.UserLogoService;
import lombok.AllArgsConstructor;
import org.flywaydb.core.internal.util.IOUtils;
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
@RequestMapping("/api/v1")
public class ImageController {

    private final ImageService imageService;
    private final ProblemRepository problemRepository;
    private final UserLogoService userLogoService;
    private final UserRepository userRepository;

    @PostMapping("/problem/{problemId}/image")
    public ResponseEntity<?> uploadProblemImages(
            @PathVariable final String problemId, @RequestParam("imageFile") final MultipartFile[] files){
        final Problem problem = problemRepository.findById(Long.valueOf(problemId))
                .orElseThrow(() -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));
        if(imageService.isImageNumberExceeded(problem, files.length)){
            throw new ApplicationException(ExceptionType.IMAGE_NUMBER_EXCEEDED);
        }
        Arrays.stream(files)
                .forEach(file -> imageService.uploadImage(problem, file));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /*@PostMapping("/user/{userId}/user_logo")
    public ResponseEntity<?> uploadUserLogo(
            @PathVariable final String userId, @RequestParam("imageFile") final MultipartFile[] files){
        final User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        Arrays.stream(files)
                .forEach(file -> userLogoService.uploadUserLogo(user, file));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }*/

    @GetMapping(value = "/problem/{problemId}/image")
    public List<ImageDto> getAllImages(@PathVariable final String problemId) throws IOException {
       return imageService.getImages(Long.valueOf(problemId));
    }

    /*@GetMapping(value = "/user/{userId}/user_logo")
    public UserLogoDto getUserLogo(@PathVariable final String userId) throws IOException {
        return userLogoService.getUserLogo(Long.valueOf(userId));
    }*/

    @DeleteMapping("/problem/{problemId}/image/{imageId}")
    public ResponseEntity<?> deleteProblemImage(@PathVariable final String problemId, @PathVariable final String imageId){
        imageService.deleteImage(Long.valueOf(problemId), Long.valueOf(imageId));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    /*@DeleteMapping("/user/{userId}/user_logo")
    public ResponseEntity<?> deleteUserLogo(@PathVariable final String userId, @PathVariable final String userLogoId){
        userLogoService.deleteUserLogo(Long.valueOf(userId), Long.valueOf(userLogoId));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }*/

    @PutMapping("/problem/image/{imageId}")
    public ResponseEntity<?> updateProblemImage(@PathVariable final String imageId, @RequestParam final MultipartFile file) {
        this.imageService.updateImage(Long.valueOf(imageId), file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /*@PutMapping("/user/user_logo/{user_logoId}")
    public ResponseEntity<?> updateUserLogo(@PathVariable final String userLogoId, @RequestParam final MultipartFile file) {
        this.userLogoService.updateUserLogo(Long.valueOf(userLogoId), file);
        return ResponseEntity.ok(HttpStatus.OK);
    }*/

    @PostMapping("user/{userId}/user_logo")
    public ResponseEntity<?> uploadUserLogo(@PathVariable final String userId, @RequestParam("userLogo") final MultipartFile file){
        if(file.isEmpty()){
            throw new ApplicationException(ExceptionType.FILE_NOT_FOUND);
        }

        userLogoService.saveUserLogo(Long.valueOf(userId), file);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}/user_logo")
    public void renderUserLogo(@PathVariable final String userId, final HttpServletResponse response) throws IOException{
        userLogoService.renderImageFromDb(Long.valueOf(userId), response);
    }

}

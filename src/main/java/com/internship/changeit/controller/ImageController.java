package com.internship.changeit.controller;

import com.internship.changeit.dto.ImageDto;
import com.internship.changeit.dto.UserDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Problem;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.service.ImageService;
import com.internship.changeit.service.UserLogoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = "/problem/{problemId}/image")
    public List<ImageDto> getAllImages(@PathVariable final String problemId) throws IOException {
       return imageService.getImages(Long.valueOf(problemId));
    }

    @DeleteMapping("/problem/{problemId}/image/{imageId}")
    public ResponseEntity<?> deleteProblemImage(@PathVariable final String problemId, @PathVariable final String imageId){
        imageService.deleteImage(Long.valueOf(problemId), Long.valueOf(imageId));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/problem/image/{imageId}")
    public ResponseEntity<?> updateProblemImage(@PathVariable final String imageId, @RequestParam final MultipartFile file) {
        this.imageService.updateImage(Long.valueOf(imageId), file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("user/{userId}/user_logo")
    public ResponseEntity<?> uploadUserLogo(@PathVariable final String userId, @RequestParam("imageFile") final MultipartFile file){
        if(file.isEmpty()){
            throw new ApplicationException(ExceptionType.FILE_NOT_FOUND);
        }

        userLogoService.saveUserLogo(Long.valueOf(userId), file);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value = "user/{userId}/user_logo")
    public UserDto getUserLogo(@PathVariable final String userId) throws IOException {
        return userLogoService.getUserLogo(Long.valueOf(userId));
    }

    @DeleteMapping("/user/{userId}/user_logo")
    public ResponseEntity<?> deleteUserLogo(@PathVariable final String userId){
        userLogoService.deleteUserLogo(Long.valueOf(userId));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

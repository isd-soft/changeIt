package com.internship.changeit.controller;

import com.internship.changeit.dto.ImageDto;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Problem;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.service.ImageService;
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
@RequestMapping("/api/v1/problem")
public class ImageController {

    private final ImageService imageService;
    private final ProblemRepository problemRepository;

    @PostMapping("/{problemId}/image")
    public ResponseEntity<?> uploadProblemImages(@PathVariable final String problemId, @RequestParam("imageFile") final MultipartFile[] files){
        final Problem problem = problemRepository.findById(Long.valueOf(problemId))
                .orElseThrow(() -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));
        if(imageService.isImageNumberExceeded(problem, files.length)){
            throw new ApplicationException(ExceptionType.IMAGE_NUMBER_EXCEEDED);
        }
        Arrays.stream(files)
                .forEach(file -> imageService.uploadImage(problem, file));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{problemId}/image")
    public List<ImageDto> getAllImages(@PathVariable final String problemId) throws IOException {
       return imageService.getImages(Long.valueOf(problemId));
    }

    @DeleteMapping("/{problemId}/image/{imageId}")
    public ResponseEntity<?> deleteProblemImage(@PathVariable final String problemId, @PathVariable final String imageId){
        imageService.deleteImage(Long.valueOf(problemId), Long.valueOf(imageId));
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/image/{imageId}")
    public ResponseEntity<?> updateProblemImage(@PathVariable final String imageId, @RequestParam final MultipartFile file) {
        this.imageService.updateImage(Long.valueOf(imageId), file);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
